import fs from "fs";
import path from "path";
import { spawn, exec } from "child_process";

export const run = async (req, res) => {
  const { language, codePath, input = "", uid } = req.body;

  if (!language || !codePath || !uid) {
    return res.status(400).json({
      success: false,
      message: "Running code failed, Field cannot empty",
    });
  }

  const TEMP_DIR = "./public/temp";
  if (!fs.existsSync(TEMP_DIR)) {
    fs.mkdirSync(TEMP_DIR, { recursive: true });
  }

  const fileBase = `code_${uid}`;
  let filePath, exePath, compileCmd, runCmd;

  if (language === "c") {
    filePath = path.join(TEMP_DIR, `${fileBase}.c`);
    exePath = path.join(TEMP_DIR, `${fileBase}.exe`);
    compileCmd = `gcc "${filePath}" -o "${exePath}"`;
    runCmd = exePath;
  } else if (language === "cpp") {
    filePath = path.join(TEMP_DIR, `${fileBase}.cpp`);
    exePath = path.join(TEMP_DIR, `${fileBase}.exe`);
    compileCmd = `g++ "${filePath}" -o "${exePath}"`;
    runCmd = exePath;
  } else if (language === "java") {
    filePath = path.join(TEMP_DIR, `${fileBase}.java`);
    compileCmd = `javac "${filePath}"`;
    runCmd = `java -cp ${TEMP_DIR} ${fileBase}`;
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
