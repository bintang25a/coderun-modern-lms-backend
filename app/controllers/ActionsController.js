import fs from "fs";
import path from "path";
import archiver from "archiver";
import Parser from "tree-sitter";
import C from "tree-sitter-c";
import CPP from "tree-sitter-cpp";
import Java from "tree-sitter-java";
import Python from "tree-sitter-python";
import * as pty from "node-pty";
import { Assignment, Submission } from "../../database/models/Model.js";
import {
  buildDataset,
  buildAnswer,
  buildCSV,
  buildHeader,
  buildAnswerKey,
  buildModel,
} from "../utils/javascript/builder.js";

const BASE_DIR = process.cwd();

export const grade = async (req, res) => {
  const { submission_number, assignment_number } = req?.body;

  if (!submission_number || !assignment_number) {
    return res.status(400).json({
      success: false,
      message: "Grading submission failed, Request body empty",
    });
  }

  const submission = await Submission.findOne({
    where: {
      submission_number,
      assignment_number,
    },
  });

  if (!submission) {
    return res.status(404).json({
      success: false,
      message: "Grading submission failed, Submission not found",
    });
  }

  const { grade } = req.body;

  if (!grade) {
    return res.status(400).json({
      success: false,
      message: "Grading submission failed, Field cannot empty",
    });
  }

  if (!req.uid) {
    return res.status(400).json({
      success: false,
      message: "Grading submission failed, User unknown",
    });
  }

  try {
    await Submission.update(
      {
        grade,
        assistant_uid: req.uid,
      },
      {
        where: {
          submission_number,
        },
      }
    );

    res.status(200).json({
      success: true,
      message: "Grading submission successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Grading submission failed",
    });
  }
};

export const autoGrade = async (req, res) => {
  const { uid } = req;
  const {
    assignment_number,
    language,
    test_cases,
    timeLimit,
    concurrency = 1,
    regrade = false,
  } = req.body;
  const io = req.app.get("socketio");

  if (!assignment_number || !language || !timeLimit) {
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

  io.emit(`autoGrade-${uid}`, {
    message: `Starting auto grade`,
  });

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

  const answerKey = await buildAnswerKey({
    io,
    parser,
    language,
    testCases,
    uid,
    timeLimit,
    assignment,
  });

  const datasetRows = await buildDataset({
    io,
    parser,
    schemaSet,
    language,
    testCases,
    uid,
    timeLimit,
    answerKey,
    CONCURRENCY: concurrency || 1,
  });

  const answerRows = await buildAnswer({
    io,
    parser,
    schemaSet,
    language,
    testCases,
    uid,
    timeLimit,
    answerKey,
    assignment,
    REGRADE: regrade,
  });

  if (answerRows?.length === 0) {
    io.emit(`autoGrade-${uid}-done`);

    return res.status(200).json({
      success: true,
      message: `Automatic grading finish`,
    });
  }

  const header = await buildHeader(schemaSet);

  const outputDir = path.join(BASE_DIR, "temp", req.uid);
  fs.mkdirSync(outputDir, { recursive: true });

  const toCSVRow = (r) => [
    r.row_id,
    r.score,
    r.scale,
    ...header.slice(3).map((k) => r.counter[k] || 0),
  ];

  await buildCSV(
    path.join(outputDir, `DATASET_${assignment_number}.csv`),
    header,
    datasetRows.map(toCSVRow)
  );

  await buildCSV(
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

  // Dataset and Result path
  const modelPyPath = path.join(BASE_DIR, "app", "utils", "python", "model.py");
  const datasetPath = path.join(outputDir, `DATASET_${assignment_number}.csv`);
  const answerPath = path.join(outputDir, `ANSWER_${assignment_number}.csv`);
  const resultPath = `${outputDir}/RESULT_${assignment_number}.json`;

  if (!fs.existsSync(modelPyPath)) {
    return res.status(400).json({
      success: false,
      message: "Automatic grading failed, Model AI not found",
    });
  }

  try {
    await buildModel({
      modelPyPath,
      datasetPath,
      answerPath,
      resultPath,
    });

    const submissionResult = await JSON.parse(
      fs.readFileSync(resultPath, "utf8")
    );

    const uploadResult = await submissionResult.map((result) => {
      Submission.update(
        {
          grade: result.score,
          assistant_uid: req.uid,
        },
        {
          where: {
            submission_number: result.submission_number,
          },
        }
      );
    });

    await Promise.all(uploadResult);
  } catch (err) {
    return res.status(500).json({
      success: false,
      message: "Automatic grading failed, (AI model error)",
      error: err.stderr || err.message || err,
    });
  }

  io.emit(`autoGrade-${uid}`, {
    message: `Auto grade finish`,
  });
  io.emit(`autoGrade-${uid}-done`);

  return res.status(200).json({
    success: true,
    message: `Automatic grading successfully`,
  });
};

export const run = async (req, res) => {
  const { language, codePath, code, input = "", timeLimit = 5000 } = req.body;
  const uid = req.uid;

  if (!language || (!codePath && !code) || !uid) {
    return res.status(400).json({
      success: false,
      message: "Running code failed, field cannot be empty",
    });
  }

  const normPath = codePath ? codePath?.replace(/\\/g, "/") : "";
  const absoluteCodePath = codePath ? path.resolve(BASE_DIR, normPath) : "";
  const tempDir = path.resolve(BASE_DIR, "temp", uid);

  if (!fs.existsSync(absoluteCodePath) && !code) {
    return res.status(404).json({
      success: false,
      message: "Running code failed, Source code not found",
      path: absoluteCodePath,
    });
  }

  const codeContent = codePath ? fs.readFileSync(absoluteCodePath, "utf8") : "";
  if (!codeContent.trim() && codePath) {
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
      const match = !codePath
        ? code?.match(/public\s+class\s+(\w+)/)
        : codeContent?.match(/public\s+class\s+(\w+)/);
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
  fs.writeFileSync(sourcePath, !codePath ? code : codeContent);

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

      return res.status(200).json({
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

export const downloadSubmissions = async (req, res) => {
  const { assignment_number } = req?.params;

  if (!assignment_number) {
    return res.status(400).json({
      success: false,
      message: "Download submissions failed, Params cannot empty",
    });
  }

  try {
    const assignment = await Assignment.findOne({
      where: {
        assignment_number,
      },
      include: [
        {
          association: Assignment.associations.submissions,
          as: "submissions",
        },
      ],
    });

    if (!assignment) {
      return res.status(404).json({
        success: false,
        message: "Download submissions failed, Assignment not found",
      });
    }

    const submissions = assignment?.submissions;

    if (!submissions) {
      return res.status(404).json({
        success: false,
        message: "Download submissions failed, No submissions",
      });
    }

    res.attachment(`submissions-${assignment_number}.zip`);

    const archive = archiver("zip", { zlib: { level: 9 } });

    archive.on("error", (err) => {
      throw err;
    });

    archive.pipe(res);

    submissions.forEach((sub) => {
      const filePath = sub?.answer?.replace(/\\/g, "/");

      if (fs.existsSync(filePath)) {
        const fileName = path.basename(filePath);
        archive.file(filePath, { name: fileName });
      }
    });

    await archive.finalize();
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Download submissions failed",
    });
  }
};
