import fs from "fs";
import path from "path";
import Parser from "tree-sitter";
import C from "tree-sitter-c";
import CPP from "tree-sitter-cpp";
import Java from "tree-sitter-java";
import Python from "tree-sitter-python";
import * as pty from "node-pty";
import { exec } from "child_process";
import { Assignment } from "../../database/models/Model.js";
import { buildDataset } from "../utils/javascript/dataset.js";
import { buildAnswer } from "../utils/javascript/answer.js";
import { finalizeHeader } from "../utils/javascript/schema.js";
import { writeCSV } from "../utils/javascript/csv.js";

const BASE_DIR = process.cwd();
const HOST_BASE_DIR = process.env.HOST_PROJECT_PATH;

export const autoGrade = async (req, res) => {
  const { assignment_number, language, test_cases } = req.body;

  if (!assignment_number || !language || !test_cases) {
    return res.status(400).json({
      success: false,
      message: "Automatic grading failed, Field cannot empty",
    });
  }

  const assignment = await Assignment.findOne({
    where: { assignment_number },
    include: [
      {
        association: Assignment.associations.submissions,
        as: "submissions",
        attributes: ["submission_number", "student_uid", "grade", "answer"],
      },
    ],
  });

  if (!assignment) {
    return res.status(404).json({
      success: false,
      message: "Automatic grading failed, Assignment not found",
    });
  }

  const parser = new Parser();
  if (language === "c") {
    parser.setLanguage(C);
  } else if (language === "cpp") {
    parser.setLanguage(CPP);
  } else if (language === "java") {
    parser.setLanguage(Java);
  } else if (language === "python") {
    parser.setLanguage(Python);
  } else {
    return res.status(400).json({
      success: false,
      message: "Automatic grading failed, Unsupported language",
    });
  }

  const schemaSet = new Set();

  const datasetRows = await buildDataset({
    assignment,
    parser,
    schemaSet,
    language,
    testCases: test_cases,
    uid: req.uid,
  });

  const answerRows = await buildAnswer({
    assignment,
    parser,
    schemaSet,
  });

  const header = finalizeHeader(schemaSet);

  const outputDir = path.join(BASE_DIR, "temp", req.uid);
  fs.mkdirSync(outputDir, { recursive: true });

  const toCSVRow = (r) => [
    r.row_id,
    r.score,
    ...header.slice(2).map((k) => r.counter[k] || 0),
  ];

  writeCSV(
    path.join(outputDir, `DATASET_${assignment_number}.csv`),
    header,
    datasetRows.map(toCSVRow)
  );

  writeCSV(
    path.join(outputDir, `ANSWER_${assignment_number}.csv`),
    header,
    answerRows.map(toCSVRow)
  );

  return res.status(200).json({
    success: true,
    message: `Automatic grading successfully`,
    dataset: path.join(outputDir, `DATASET_${assignment_number}.csv`),
    answer: path.join(outputDir, `ANSWER_${assignment_number}.csv`),
  });
};

export const run = async (req, res) => {
  const { language, codePath, input = "", timeLimit = 5000 } = req.body;
  const uid = req.uid;

  if (!language || !codePath || !uid) {
    return res.status(400).json({
      success: false,
      message: "Running code failed, field cannot be empty",
    });
  }

  const normalizedPath = codePath.replace(/\\/g, "/");
  const absoluteCodePath = path.resolve(BASE_DIR, normalizedPath);

  if (!fs.existsSync(absoluteCodePath)) {
    return res.status(404).json({
      success: false,
      message: `Running code failed, Source code not found`,
    });
  }

  const codeContent = fs.readFileSync(absoluteCodePath, "utf8");
  if (!codeContent.trim()) {
    return res.status(400).json({
      success: false,
      message: "Running code failed, Source code is empty",
    });
  }

  let filename, compileRunCmd;

  switch (language) {
    case "c":
      filename = "main.c";
      compileRunCmd = "gcc main.c -o app && stdbuf -i0 -o0 -e0 ./app";
      break;
    case "cpp":
      filename = "main.cpp";
      compileRunCmd = "g++ main.cpp -o app && stdbuf -i0 -o0 -e0 ./app";
      break;
    case "java":
      const classMatch = codeContent.match(
        /public\s+class\s+([a-zA-Z_$][a-zA-Z\d_$]*)/
      );
      const className = classMatch ? classMatch[1] : "Main";

      filename = `${className}.java`;
      compileRunCmd = `javac ${filename} && exec java ${className}`;
      break;
    case "python":
      filename = "main.py";
      compileRunCmd = "python3 -u main.py";
      break;
    default:
      return res.status(400).json({
        success: false,
        message: "Running code failed, Language unsupported",
      });
  }

  const tempDir = path.resolve(BASE_DIR, "temp", uid);
  const hostTempDir = path.join(HOST_BASE_DIR, "temp", uid).replace(/\\/g, "/");
  const sourcePath = path.join(tempDir, filename);

  if (fs.existsSync(sourcePath)) {
    fs.rmSync(sourcePath, { recursive: true, force: true });
  }

  fs.mkdirSync(tempDir, { recursive: true });
  fs.writeFileSync(sourcePath, codeContent);

  const containerName = `sandbox_${uid}`;

  const dockerArgs = [
    "run",
    "--rm",
    "--name",
    `${containerName}`,
    "-i",
    "-t",
    "--user",
    "root",
    "--cpus=1",
    "--memory=512m",
    "--network=none",
    "-v",
    `${hostTempDir}:/app`,
    "-w",
    "/app",
    "coderun-modern-lms-sandbox",
    "bash",
    "-c",
    compileRunCmd,
  ];

  const ptyProcess = pty.spawn("docker", dockerArgs, {
    name: "xterm-color",
    cols: 80,
    rows: 30,
    cwd: process.cwd(),
    env: process.env,
  });

  let output = "";
  let isFinished = false;

  const formatOutput = (raw) => {
    return raw
      .replace(/\x1B(?:[@-Z\\-_]|\[[0-?]*[ -/]*[@-~])/g, "")
      .replace(/\r\n/g, "\n")
      .trim();
  };

  const inputQueue = input ? input.trim().split(/\s+/) : [];
  let currentIdx = 0;

  ptyProcess.onData((data) => {
    output += data;

    if (inputQueue.length > 0 && currentIdx < inputQueue.length) {
      ptyProcess.write(`${inputQueue[currentIdx]}\r`);

      currentIdx++;
    }
  });

  const timeout = setTimeout(() => {
    if (!isFinished) {
      isFinished = true;

      const currentOutput = formatOutput(output);

      try {
        exec(`docker rm -f ${containerName}`);
        ptyProcess.kill();
      } catch (e) {}

      return res.status(200).json({
        success: true,
        message: "Running code successfully, Execution Timed Out",
        output: currentOutput,
        isTimeout: true,
      });
    }
  }, Number(timeLimit));

  ptyProcess.onExit(({ exitCode }) => {
    if (isFinished) return;

    isFinished = true;
    clearTimeout(timeout);

    const cleanOutput = formatOutput(output);

    return res.status(200).json({
      success: true,
      message:
        exitCode !== 0 && output.toLowerCase().includes("error")
          ? "Execution/Compile Error"
          : "",
      output: cleanOutput,
    });
  });
};
