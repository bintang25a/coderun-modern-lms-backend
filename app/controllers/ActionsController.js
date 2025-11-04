import fs from "fs";
import path from "path";
import { spawn, exec } from "child_process";
import Parser from "tree-sitter";
import C from "tree-sitter-c";
import CPP from "tree-sitter-cpp";
import Java from "tree-sitter-java";
import Python from "tree-sitter-python";
import { Assignment } from "../../database/models/Model.js";

export const labelling = async (req, res) => {
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
  const parseFile = async (filePath, submission_number) => {
    const ext = path.extname(filePath);

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
      submission_number,
      label_count: counter,
    };
  };

  const submissions = assignment.submissions;

  for (const submission of submissions) {
    if (submission.grade != null) {
      continue;
    }

    const fileNumber = submission.submission_number;
    const filePath = path.resolve(submission.answer);
    const fileName = path.basename(filePath);

    const fileParse = await parseFile(filePath, fileNumber);

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

export const run = async (req, res) => {
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

    runProcess.on("close", (code) => {
      clearTimeout(timeout);

      if (killed) {
        return res.status(400).json({
          success: false,
          message: "Running code failed, Unlimited looping",
        });
      }

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

// function nodeToJSON(node) {
//   const result = {
//     type: node.type,
//     startPosition: node.startPosition,
//     endPosition: node.endPosition,
//   };

//   if (node.childCount > 0) {
//     result.children = [];
//     for (let i = 0; i < node.childCount; i++) {
//       result.children.push(nodeToJSON(node.child(i)));
//     }
//   } else {
//     result.text = node.text;
//   }
//   return result;
// }
