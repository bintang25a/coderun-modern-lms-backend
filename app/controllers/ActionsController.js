import fs from "fs";
import path from "path";
import { spawn, execSync } from "child_process";
import Parser from "tree-sitter";
import C from "tree-sitter-c";
import CPP from "tree-sitter-cpp";
import Java from "tree-sitter-java";
import Python from "tree-sitter-python";
import * as pty from "node-pty";
import { Assignment } from "../../database/models/Model.js";

export const autoGrade = async (req, res) => {
  const { assignment_number, language } = req.body;

  if (!assignment_number || !language) {
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

  // Folder output CSV
  const outputDir = path.resolve("temp");
  if (!fs.existsSync(outputDir)) {
    fs.mkdirSync(outputDir, { recursive: true });
  }

  // Parser setup
  const parser = new Parser();
  if (language === "c") parser.setLanguage(C);
  else if (language === "cpp") parser.setLanguage(CPP);
  else if (language === "java") parser.setLanguage(Java);
  else if (language === "python") parser.setLanguage(Python);
  else {
    return res.status(400).json({
      success: false,
      message: "Automatic grading failed, Unsupported language",
    });
  }

  const countNodeTypes = (node, counter) => {
    const type = node.type;
    counter[type] = (counter[type] || 0) + 1;
    for (let i = 0; i < node.childCount; i++) {
      countNodeTypes(node.child(i), counter);
    }
  };

  // Prepare variabel
  let labelError = 0;
  let csvHeader = null;
  let csvDatasetPath = "";
  const delimiter = " ";

  const labelling = async () => {
    let csvRows = [];
    let nodeKeys = new Set();
    let results = [];
    let row_id = 1;

    const parseDataset = async (filePath, keyPath, row_id) => {
      const ext = path.extname(filePath);

      if (ext !== `.${language}`) {
        labelError++;
        return { row_id, message: "Wrong language" };
      }

      const keyCode = fs.readFileSync(keyPath, "utf8");
      const keyTree = parser.parse(keyCode);

      const code = fs.readFileSync(filePath, "utf8");
      const tree = parser.parse(code);

      const keyCounter = {};
      countNodeTypes(keyTree.rootNode, keyCounter);

      const counter = {};
      countNodeTypes(tree.rootNode, counter);

      const allKeys = new Set([
        ...Object.keys(keyCounter),
        ...Object.keys(counter),
      ]);

      let score = 0;
      let T = 0;

      for (const key of allKeys) {
        const keyNode = keyCounter[key] || 0;
        const answerNode = counter[key] || 0;

        score += Math.min(keyNode, answerNode);
        T += keyNode;
      }

      score = (score / T) * 100;

      return {
        counter,
        score: Number(score.toFixed(2)),
        row_id,
      };
    };

    const datasetDir = path.resolve(`database/datasets/${language}`);
    const datasetFiles = fs.readdirSync(datasetDir);
    const keyFile = assignment.answer_key;

    const keyCode = fs.readFileSync(keyFile, "utf8");
    const keyTree = parser.parse(keyCode);
    const keyCounter = {};
    countNodeTypes(keyTree.rootNode, keyCounter);

    Object.keys(keyCounter).forEach((k) => nodeKeys.add(k));

    results.push({
      row_id: "key",
      score: 100,
      counter: keyCounter,
    });

    for (const file of datasetFiles) {
      const ext = path.extname(file);
      if (ext !== `.${language}`) continue;

      const filePath = path.join(datasetDir, file);

      const result = await parseDataset(filePath, keyFile, row_id++);

      if (result?.message) continue;

      results.push(result);

      Object.keys(result.counter).forEach((k) => nodeKeys.add(k));
    }

    for (const result of results) {
      const row = [result.row_id, result.score];

      Array.from(nodeKeys).forEach((key) => {
        row.push(result.counter[key] || 0);
      });

      csvRows.push(row);
    }

    csvHeader = ["row_id", "score", ...Array.from(nodeKeys)];

    function escape(value) {
      if (value.includes(",")) {
        return "coma";
      }
      return value;
    }

    const header = csvHeader.map(escape).join(delimiter);
    const rows = csvRows.map((row) =>
      row.map((v) => escape(String(v))).join(delimiter)
    );

    csvDatasetPath = path.join(outputDir, `DATASET_${assignment_number}.csv`);
    fs.writeFileSync(csvDatasetPath, [header, ...rows].join("\n"));
  };

  await labelling();

  let csvAnswerPath = "";
  const preproccessing = async () => {
    let csvRows = [];
    let nodeKeys = new Set();
    let results = [];

    const parseAnswer = async (filePath, row_id) => {
      const ext = path.extname(filePath);

      if (!fs.existsSync(filePath)) {
        labelError++;

        return {
          submission_number,
          message: "Code not found",
        };
      }

      if (ext != `.${language}`) {
        labelError++;

        return {
          submission_number,
          message: "Wrong language",
        };
      }

      const code = fs.readFileSync(filePath, "utf8");
      const tree = parser.parse(code);

      const counter = {};
      countNodeTypes(tree.rootNode, counter);

      return {
        row_id,
        counter,
      };
    };

    const submissions = assignment.submissions;
    console.log(assignment.submissions[0].student_uid);

    for (const submission of submissions) {
      if (submission.grade != null) {
        continue;
      }

      const fileNumber = submission.submission_number;
      const filePath = path.resolve(submission.answer);

      const result = await parseAnswer(filePath, fileNumber);

      if (result?.message) continue;

      results.push(result);

      Object.keys(result.counter).forEach((k) => nodeKeys.add(k));
    }

    for (const result of results) {
      const row = [result.row_id];

      Array.from(csvHeader).forEach((key) => {
        if (key !== "row_id") {
          row.push(result.counter[key] || 0);
        }
      });

      csvRows.push(row);
    }

    const combinedKeysSet = new Set([...csvHeader, ...Array.from(nodeKeys)]);

    csvHeader = Array.from(combinedKeysSet);

    function escape(value) {
      if (value.includes(",")) {
        return "coma";
      }
      return value;
    }

    const header = csvHeader.map(escape).join(delimiter);
    const rows = csvRows.map((row) =>
      row.map((v) => escape(String(v))).join(delimiter)
    );

    csvAnswerPath = path.join(outputDir, `ANSWER_${assignment_number}.csv`);
    fs.writeFileSync(csvAnswerPath, [header, ...rows].join("\n"));
  };

  await preproccessing();

  return res.status(200).json({
    success: true,
    message: `Automatic grading successfully, ${labelError} file error`,
    dataset: csvDatasetPath,
    answer: csvAnswerPath,
  });
};

export const parse2 = async (req, res) => {
  const { assignment_number, language } = req.body;

  if (!assignment_number || !language) {
    return res.status(400).json({
      success: false,
      message: "Automatic grading failed, Field cannot empty",
    });
  }

  const assignment = await Assignment.findOne({
    where: {
      assignment_number,
    },
    include: [
      {
        association: Assignment.associations.submissions,
        as: "submissions",
        attributes: ["submission_number", "student_uid", "answer", "grade"],
      },
    ],
  });

  if (!assignment) {
    return res.status(404).json({
      success: false,
      message: "Automatic grading failed, Assignment not found",
    });
  }

  const tempPath = path.resolve("temp");
  const outputDir = path.join(tempPath, assignment_number);
  if (!fs.existsSync(outputDir)) {
    fs.mkdirSync(outputDir, { recursive: true });
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

  const countNodeTypes = (node, counter) => {
    const type = node.type;
    counter[type] = (counter[type] || 0) + 1;

    for (let i = 0; i < node.childCount; i++) {
      countNodeTypes(node.child(i), counter);
    }
  };

  let labelError = 0;
  const parseFile = async (filePath, keyPath, submission_number) => {
    const ext = path.extname(filePath);

    if (!fs.existsSync(filePath)) {
      labelError++;

      return {
        submission_number,
        message: "Code not found",
      };
    }

    if (ext != `.${language}`) {
      labelError++;

      return {
        submission_number,
        message: "Wrong language",
      };
    }

    const keyCode = fs.readFileSync(keyPath, "utf8");
    const keyTree = parser.parse(keyCode);
    const code = fs.readFileSync(filePath, "utf8");
    const tree = parser.parse(code);

    const keyCounter = {};
    countNodeTypes(keyTree.rootNode, keyCounter);

    const counter = {};
    countNodeTypes(tree.rootNode, counter);

    const allKeys = new Set([
      ...Object.keys(keyCounter),
      ...Object.keys(counter),
    ]);

    let score = 0;
    let S = {};
    let T = 0;

    for (const key of allKeys) {
      const keyNode = keyCounter[key] || 0;
      const answerNode = counter[key] || 0;

      S[key] = Math.min(keyNode, answerNode);

      score += Number(S[key].toFixed(2));
      T += keyNode;
    }

    score = (score / T) * 100;

    return {
      submission_number,
      score: Number(score.toFixed(2)),
      ...counter,
    };
  };

  const submissions = assignment.submissions;

  for (const submission of submissions) {
    if (submission.grade != null) {
      continue;
    }

    const keyFile = assignment.answer_key;
    const fileNumber = submission.submission_number;
    const filePath = path.resolve(submission.answer);
    const fileName = path.basename(filePath);

    const fileParse = await parseFile(filePath, keyFile, fileNumber);

    if (!fileParse?.message) {
      const outPath = path.join(outputDir, `${fileName}.json`);
      fs.writeFileSync(outPath, JSON.stringify(fileParse, null, 2));
    }
  }

  if (submissions.length == labelError) {
    return res.status(400).json({
      success: false,
      message: `Automatic grading failed, All file error`,
    });
  }

  return res.status(200).json({
    success: true,
    message: `Automatic grading successfully, ${labelError} file error`,
  });
};

export const countSBCAM = async (req, res) => {
  const { assignment_number, language } = req.body;

  if (!assignment_number || !language) {
    return res.status(400).json({
      success: false,
      message: "Automatic grading failed, Field cannot empty",
    });
  }

  const assignment = await Assignment.findOne({
    where: {
      assignment_number,
    },
    include: [
      {
        association: Assignment.associations.submissions,
        as: "submissions",
        attributes: ["submission_number", "student_uid", "answer", "grade"],
      },
    ],
  });

  if (!assignment) {
    return res.status(404).json({
      success: false,
      message: "Automatic grading failed, Assignment not found",
    });
  }

  const tempPath = path.resolve("temp");
  const outputDir = path.join(tempPath, assignment_number);
  if (!fs.existsSync(outputDir)) {
    fs.mkdirSync(outputDir, { recursive: true });
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

  const countNodeTypes = (node, counter) => {
    const type = node.type;
    counter[type] = (counter[type] || 0) + 1;

    for (let i = 0; i < node.childCount; i++) {
      countNodeTypes(node.child(i), counter);
    }
  };

  let labelError = 0;
  const parseFile = async (filePath, keyPath, submission_number) => {
    const ext = path.extname(filePath);

    if (!fs.existsSync(filePath)) {
      labelError++;

      return {
        submission_number,
        message: "Code not found",
      };
    }

    if (ext != `.${language}`) {
      labelError++;

      return {
        submission_number,
        message: "Wrong language",
      };
    }

    const keyCode = fs.readFileSync(keyPath, "utf8");
    const keyTree = parser.parse(keyCode);
    const code = fs.readFileSync(filePath, "utf8");
    const tree = parser.parse(code);

    const keyCounter = {};
    countNodeTypes(keyTree.rootNode, keyCounter);

    const counter = {};
    countNodeTypes(tree.rootNode, counter);

    const allKeys = new Set([
      ...Object.keys(keyCounter),
      ...Object.keys(counter),
    ]);

    let score = 0;
    let Fs = {};

    for (const key of allKeys) {
      const tf = keyCounter[key] || 0;
      const sf = counter[key] || 0;

      Fs[key] = Number((1 - Math.abs(tf - sf) / tf).toFixed(2));
      Fs[key] = Number(Math.max(0, Math.min(1, Fs[key])).toFixed(2));

      score = Number(score.toFixed(2)) + Number(Fs[key].toFixed(2));
    }

    score = (score / Object.keys(keyCounter).length) * 100;

    return {
      submission_number,
      score: Number(score.toFixed(2)),
      Fs,
      counter,
      keyCounter,
    };
  };

  const submissions = assignment.submissions;

  for (const submission of submissions) {
    if (submission.grade != null) {
      continue;
    }

    const keyFile = assignment.answer_key;
    const fileNumber = submission.submission_number;
    const filePath = path.resolve(submission.answer);
    const fileName = path.basename(filePath);

    const fileParse = await parseFile(filePath, keyFile, fileNumber);

    if (!fileParse?.message) {
      const outPath = path.join(outputDir, `${fileName}.json`);
      fs.writeFileSync(outPath, JSON.stringify(fileParse, null, 2));
    }
  }

  if (submissions.length == labelError) {
    return res.status(400).json({
      success: false,
      message: `Automatic grading failed, All file error`,
    });
  }

  return res.status(200).json({
    success: true,
    message: `Automatic grading successfully, ${labelError} file error`,
  });
};

export const run1 = async (req, res) => {
  const { language, codePath, input = "" } = req.body;
  const uid = req.uid;

  if (!language || !codePath || !uid) {
    return res.status(400).json({
      success: false,
      message: "Running code failed, field cannot be empty",
    });
  }

  const projectRoot = process.cwd();
  const absoluteCodePath = path.resolve(
    projectRoot,
    codePath.replace(/\\/g, "/")
  );

  if (!fs.existsSync(absoluteCodePath)) {
    return res.status(404).json({
      success: false,
      message: "Source code not found",
    });
  }

  const tempDir = path.resolve("temp", uid);
  fs.mkdirSync(tempDir, { recursive: true });

  let filename, compileRunCmd;

  switch (language) {
    case "c":
      filename = "main.c";
      compileRunCmd = "gcc main.c -o app && stdbuf -o0 ./app";
      break;

    case "cpp":
      filename = "main.cpp";
      compileRunCmd = "g++ main.cpp -o app && stdbuf -o0 ./app";
      break;

    case "java":
      filename = "Main.java";
      compileRunCmd = "javac Main.java && stdbuf -o0 java Main";
      break;

    default:
      return res.status(400).json({
        success: false,
        message: "Language unsupported",
      });
  }

  const codeContent = fs.readFileSync(absoluteCodePath, "utf8");
  if (!codeContent.trim()) {
    return res.status(400).json({
      success: false,
      message: "Source code is empty",
    });
  }

  fs.writeFileSync(path.join(absoluteTempPathBackend, filename), codeContent);

  const dockerArgs = [
    "run",
    "--rm",
    "-i",
    "--cpus=1",
    "--memory=256m",
    "--pids-limit=64",
    "--network=none",
    "-v",
    `${absoluteTempPathBackend}:/app`,
    "-w",
    "/app",
    "lms-code-sandbox",
    "bash",
    "-c",
    compileRunCmd,
  ];

  const proc = spawn("docker", dockerArgs, {
    stdio: ["pipe", "pipe", "pipe"],
  });

  let stdout = "";
  let stderr = "";

  proc.stdout.on("data", (d) => (stdout += d.toString()));
  proc.stderr.on("data", (d) => (stderr += d.toString()));

  if (input && input.trim().length > 0) {
    proc.stdin.write(input.endsWith("\n") ? input : input + "\n");
  }

  const timeout = setTimeout(() => {
    proc.kill("SIGKILL");
  }, 3000);

  proc.on("close", () => {
    clearTimeout(timeout);

    let finalOutput = "";
    const prompts = stdout.split(":");
    const inputs = input.trim().split(/\s+/);

    for (let i = 0; i < prompts.length; i++) {
      finalOutput += prompts[i];

      if (i < prompts.length - 1) {
        finalOutput += ": ";

        if (inputs[i]) {
          finalOutput += "" + inputs[i] + "\n";
        }
      }
    }

    return res.status(200).json({
      success: true,
      output: finalOutput.trim(),
      error: stderr.trim(),
    });
  });
};

export const run2 = async (req, res) => {
  const { language, codePath, input = "" } = req.body;
  const uid = req.uid;

  if (!language || !codePath || !uid) {
    return res.status(400).json({
      success: false,
      message: "Running code failed, Field cannot empty",
    });
  }

  const tempDir = path.resolve("temp");
  if (!fs.existsSync(tempDir)) {
    fs.mkdirSync(tempDir, { recursive: true });
  }

  const fileBase = `code-${uid}`;
  let filePath, exePath, compileCmd, runCmd;

  if (language === "c") {
    filePath = path.join(tempDir, `${fileBase}.c`);
    exePath = path.join(tempDir, `${fileBase}.exe`);
    compileCmd = `gcc "${filePath}" -o "${exePath}"`;
    runCmd = exePath;
  } else if (language === "cpp") {
    filePath = path.join(tempDir, `${fileBase}.cpp`);
    exePath = path.join(tempDir, `${fileBase}.exe`);
    compileCmd = `g++ "${filePath}" -o "${exePath}"`;
    runCmd = exePath;
  } else if (language === "java") {
    filePath = path.join(tempDir, `${fileBase}.java`);
    compileCmd = `javac "${filePath}"`;
    runCmd = `java -cp ${tempDir} ${fileBase}`;
  } else {
    return res.status(400).json({
      success: false,
      message: "Running code failed, Language unsupported",
    });
  }

  // Simpan kode ke file
  const code = fs.readFileSync(codePath);
  fs.writeFileSync(filePath, code);

  // Compile dulu
  exec(compileCmd, (compileErr, _, compileStderr) => {
    if (compileErr) {
      return res.status(400).json({
        success: false,
        message: "Running code failed, " + compileStderr.toString(),
      });
    }

    const [command, ...args] =
      language === "java" ? runCmd.split(" ") : [runCmd];
    const runProcess = spawn(command, args, { cwd: process.cwd() });

    let output = "";
    let error = "";
    let killed = false;

    // Set timeout 3 detik
    const timeout = setTimeout(() => {
      killed = true;
      runProcess.kill();
    }, 3000);

    // Kirim input awal
    if (input.trim() !== "") {
      runProcess.stdin.write(input + "\n");
    }
    runProcess.stdin.end();

    runProcess.stdout.on("data", (data) => {
      output += data.toString();
    });

    runProcess.stderr.on("data", (data) => {
      error += data.toString();
    });

    let results = [];

    runProcess.on("close", (code) => {
      clearTimeout(timeout);

      if (killed) {
        return res.status(400).json({
          success: false,
          message: "Running code failed, Unlimited looping",
        });
      }

      results.push({
        input,
        output: output.trim(),
        expected: expectedOutput,
        pass: actualOutput === expectedOutput ? 1 : 0,
        weight: weight,
      });

      if (error) {
        return res.status(400).json({
          success: false,
          message: "Running code failed, " + error.message,
        });
      }

      return res.status(200).json({
        success: false,
        message: "Running code successfully",
        output: output.trim(),
        exitCode: code,
      });
    });
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

  const hostProjectRoot = process.env.HOST_PROJECT_PATH;

  const projectRoot = process.cwd();
  const normalizedPath = codePath.replace(/\\/g, "/");
  const absoluteCodePath = path.resolve(projectRoot, normalizedPath);

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

  const tempDir = path.resolve(projectRoot, "temp", uid);
  const hostTempDir = path
    .join(hostProjectRoot, "temp", uid)
    .replace(/\\/g, "/");

  if (fs.existsSync(tempDir)) {
    fs.rmSync(tempDir, { recursive: true, force: true });
  }

  fs.mkdirSync(tempDir, { recursive: true });

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

  const sourcePath = path.join(tempDir, filename);
  fs.writeFileSync(sourcePath, codeContent);

  const dockerArgs = [
    "run",
    "--rm",
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
