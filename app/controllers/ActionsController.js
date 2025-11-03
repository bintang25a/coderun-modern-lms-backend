import fs from "fs";
import path from "path";
import { spawn, exec } from "child_process";
import Parser from "tree-sitter";
import C from "tree-sitter-c";
import CPP from "tree-sitter-cpp";
import Java from "tree-sitter-java";
import Python from "tree-sitter-python";

export const labelling = (req, res) => {
  // const { assignment_number } = req.body;
  const assignment_number = "CS25C3-01";

  const countNodeTypes = (node, counter) => {
    const type = node.type;
    counter[type] = (counter[type] || 0) + 1;

    for (let i = 0; i < node.childCount; i++) {
      countNodeTypes(node.child(i), counter);
    }
  };

  const parseFile = async (filePath) => {
    const ext = path.extname(filePath);
    const parser = new Parser();

    if (ext === ".c") {
      parser.setLanguage(C);
    } else if (ext === ".cpp") {
      parser.setLanguage(CPP);
    } else if (ext === ".java") {
      parser.setLanguage(Java);
    } else if (ext === ".python") {
      parser.setLanguage(Python);
    } else throw new Error(`Unsupported file extension: ${ext}`);

    const code = fs.readFileSync(filePath, "utf8");
    const tree = parser.parse(code);

    const counter = {};
    countNodeTypes(tree.rootNode, counter);

    return {
      submission_number: "220407",
      label_count: counter,
    };
  };

  const main = async () => {
    const publicPath = path.resolve("public");
    const tempPath = path.resolve("temp");
    const inputDir = path.join(publicPath, "classrooms", assignment_number);
    const outputDir = path.join(tempPath, assignment_number);

    if (!fs.existsSync(outputDir)) fs.mkdirSync(outputDir);

    const files = fs
      .readdirSync(inputDir)
      .filter((f) => /\.(c|cpp|java|python)$/.test(f));

    for (const file of files) {
      const filePath = path.join(inputDir, file);
      console.log(`Parsing ${filePath} ...`);
      const jsonTree = await parseFile(filePath);
      const outPath = path.join(outputDir, `${file}.json`);
      fs.writeFileSync(outPath, JSON.stringify(jsonTree, null, 2));
    }

    console.log("✅ Parsing selesai! File JSON tersimpan di folder output/");
  };

  main().catch(console.error);
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
