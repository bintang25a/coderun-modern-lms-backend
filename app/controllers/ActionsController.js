import fs from "fs";
import path from "path";
import Parser from "tree-sitter";
import C from "tree-sitter-c";
import CPP from "tree-sitter-cpp";
import Java from "tree-sitter-java";
import Python from "tree-sitter-python";
import * as pty from "node-pty";
import { exec } from "child_process";
import { promisify } from "util";
import { Assignment } from "../../database/models/Model.js";
import { buildDataset } from "../utils/javascript/dataset.js";
import { buildAnswer } from "../utils/javascript/answer.js";
import { finalizeHeader } from "../utils/javascript/schema.js";
import { writeCSV } from "../utils/javascript/csv.js";

const BASE_DIR = process.cwd();
const HOST_BASE_DIR = process.env.HOST_PROJECT_PATH;

const asyncExec = promisify(exec);

export const autoGrade = async (req, res) => {
  const { assignment_number, language } = req.body;
  const { test_cases, concurrency = 1 } = req.body;
  const { uid } = req;

  if (!assignment_number || !language) {
    return res.status(400).json({
      success: false,
      message: "Automatic grading failed, Field cannot empty",
    });
  }

  let testCases = test_cases;
  if (!test_cases) {
    testCases = [
      {
        name: "TC",
        weight: 1,
      },
    ];
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
    testCases,
    uid,
    CONCURRENCY: concurrency,
  });

  const answerRows = await buildAnswer({
    assignment,
    parser,
    schemaSet,
    language,
    testCases,
    uid,
  });

  const header = finalizeHeader(schemaSet);

  const outputDir = path.join(BASE_DIR, "temp", req.uid);
  fs.mkdirSync(outputDir, { recursive: true });

  const toCSVRow = (r) => [
    r.row_id,
    r.score,
    r.scale,
    ...header.slice(3).map((k) => r.counter[k] || 0),
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

  const tempDir = path.resolve(BASE_DIR, "temp", uid);
  fs.readdirSync(tempDir).forEach((file) => {
    const filePath = path.join(tempDir, file);
    const ext = path.extname(file).toLowerCase();

    if (ext !== ".csv" && ext !== ".json") {
      fs.rmSync(filePath, { recursive: true, force: true });
    }
  });

  // Path
  const datasetPath = path.join(outputDir, `DATASET_${assignment_number}.csv`);
  const answerPath = path.join(outputDir, `ANSWER_${assignment_number}.csv`);
  const modelPyPath = path.join(BASE_DIR, "app", "utils", "python", "model.py");

  if (!fs.existsSync(modelPyPath)) {
    return res.status(400).json({
      success: false,
      message: "Automatic grading failed, Model AI not found",
    });
  }

  // Baca isinya untuk diletakkan di tempDir agar bisa diakses Docker
  const codeContent = fs.readFileSync(modelPyPath, "utf8");
  const filename = "model.py";
  const compileRunCmd = "python3 -u model.py";

  return res.status(200).json({
    success: true,
    message: `Automatic grading successfully`,
    dataset: datasetPath,
    answer: answerPath,
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
  const tempDir = path.resolve(BASE_DIR, "temp", uid);

  if (!fs.existsSync(absoluteCodePath)) {
    return res.status(404).json({
      success: false,
      message: "Running code failed, Source code not found",
      path: absoluteCodePath,
    });
  }

  const codeContent = fs.readFileSync(absoluteCodePath, "utf8");
  if (!codeContent.trim()) {
    return res.status(400).json({
      success: false,
      message: "Running code failed, Source code is empty",
      path: codeContent,
    });
  }

  fs.mkdirSync(tempDir, { recursive: true });

  let filename, command;

  switch (language) {
    case "c":
      filename = "main.c";
      command = "gcc main.c -o app && stdbuf -i0 -o0 -e0 ./app";
      break;

    case "cpp":
      filename = "main.cpp";
      command = "g++ main.cpp -o app && stdbuf -i0 -o0 -e0 ./app";
      break;

    case "java": {
      const match = codeContent.match(/public\s+class\s+(\w+)/);
      const className = match ? match[1] : "Main";
      filename = `${className}.java`;
      command = `javac ${filename} && java -Dsun.stdout.buffered=false ${className}`;
      break;
    }

    case "python":
      filename = "main.py";
      command = "python3 -u main.py";
      break;

    default:
      return res.status(400).json({
        success: false,
        message: "Running code failed, Unsupported language",
      });
  }

  const sourcePath = path.join(tempDir, filename);
  fs.writeFileSync(sourcePath, codeContent);

  const ptyProcess = pty.spawn("bash", ["-c", command], {
    cwd: tempDir,
    env: process.env,
    name: "xterm-color",
    cols: 80,
    rows: 30,
  });

  let output = "";
  let finished = false;

  const clean = (s) =>
    s
      .replace(/\x1B\[[0-9;]*m/g, "")
      .replace(/\r\n/g, "\n")
      .trim();

  const inputQueue = input ? input.trim().split(/\s+/) : [];
  let idx = 0;

  const inputDelay =
    language !== "java" ? 0 : Math.min(Math.max(timeLimit / 100, 50), 150);

  ptyProcess.onData((data) => {
    output += data;

    setTimeout(() => {
      const condition =
        inputQueue.length > 0 && idx < inputQueue.length && !finished;

      if (condition) {
        ptyProcess.write(`${inputQueue[idx]}\r`);
        idx++;
      }
    }, Number(inputDelay));
  });

  const timer = setTimeout(() => {
    if (!finished) {
      finished = true;
      ptyProcess.kill();

      return res.status(408).json({
        success: false,
        message: "Running code successfully, Execution timed out",
        output: clean(output),
      });
    }
  }, Math.min(timeLimit, 20000));

  ptyProcess.onExit(() => {
    if (finished) return;

    finished = true;
    clearTimeout(timer);

    return res.status(200).json({
      success: true,
      message: "Running code successfully",
      output: clean(output),
    });
  });
};

export const run1 = async (req, res) => {
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
  const tempDir = path.resolve(BASE_DIR, "temp", uid);
  const hostTempDir = path.join(HOST_BASE_DIR, "temp", uid).replace(/\\/g, "/");

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
      compileRunCmd = `javac ${filename} && java -Djdk.console=java.base -Dsun.stdout.buffered=false ${className}`;
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

  const sourcePath = path.join(tempDir, filename);

  if (fs.existsSync(sourcePath)) {
    fs.rmSync(sourcePath, { recursive: true, force: true });
  }

  fs.mkdirSync(tempDir, { recursive: true });
  fs.writeFileSync(sourcePath, codeContent);

  const containerName = `sandbox_${uid}`;

  try {
    await asyncExec(`docker rm -f ${containerName}`);
  } catch (error) {
    console.log(`${containerName} not found, Continue`);
  }

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

  const inputDelay =
    language !== "java" ? 0 : Math.min(Math.max(timeLimit / 100, 50), 150);

  ptyProcess.onData((data) => {
    output += data;

    setTimeout(() => {
      const condition =
        inputQueue.length > 0 && currentIdx < inputQueue.length && !isFinished;

      if (condition) {
        ptyProcess.write(`${inputQueue[currentIdx]}\r`);
        currentIdx++;
      }
    }, Number(inputDelay));
  });

  const timeout = setTimeout(() => {
    try {
      ptyProcess.kill();
      exec(`docker rm -f ${containerName}`);
    } catch (e) {
      console.warn(`Container delete failed, ${e.message}`);
    }

    const currentOutput = formatOutput(output);

    return res.status(400).json({
      success: false,
      message: "Running code failed, Execution Timed Out",
      output: currentOutput,
    });
  }, Math.min(Number(timeLimit) * 100, 20000));

  const terminate = setTimeout(() => {
    if (currentIdx == inputQueue.length) {
      isFinished = true;

      clearTimeout(timeout);

      const currentOutput = formatOutput(output);

      try {
        ptyProcess.kill();
        exec(`docker rm -f ${containerName}`);
      } catch (e) {
        console.warn(`Container delete failed, ${e.message}`);
      }

      return res.status(200).json({
        success: true,
        message: "Running code successfully",
        output: currentOutput,
      });
    }
  }, Number(timeLimit));

  ptyProcess.onExit(({ exitCode }) => {
    if (isFinished) return;

    isFinished = true;

    const hasError = exitCode !== 0 && output.toLowerCase().includes("error");

    const cleanOutput = formatOutput(output);

    clearTimeout(terminate);
    clearTimeout(timeout);

    return res.status(200).json({
      success: true,
      message: hasError
        ? "Execution/Compile Error"
        : "Running code successfully",
      output: cleanOutput,
    });
  });
};
