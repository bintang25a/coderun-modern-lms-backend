import fs from "fs";
import path from "path";
import { spawn } from "child_process";
import { exec } from "child_process";
import { promisify } from "util";

const BASE_DIR = process.cwd();
const HOST_BASE_DIR = process.env.HOST_PROJECT_PATH;

const asyncExec = promisify(exec);

export const executeCode = async (param) => {
  const { uid, language, codePath } = param;
  const { input = "", timeLimit = 5000, containerName = "sandbox" } = param;

  try {
    await asyncExec(`docker rm -f ${containerName}`);
  } catch (e) {
    console.warn(`Container delete failed, ${e.message}`);
  }

  if (!language || !codePath || !uid) {
    return resolve({
      success: false,
      message: "Running code failed, field cannot be empty",
    });
  }

  return new Promise((resolve) => {
    const normalizedPath = codePath.replace(/\\/g, "/");
    const absoluteCodePath = path.resolve(BASE_DIR, normalizedPath);

    if (!fs.existsSync(absoluteCodePath)) {
      return resolve({
        success: false,
        message: `Running code failed, Source code not found`,
      });
    }

    const codeContent = fs.readFileSync(absoluteCodePath, "utf8");
    if (!codeContent.trim()) {
      return resolve({
        success: false,
        message: "Running code failed, Source code is empty",
      });
    }

    let filename, compileRunCmd;
    const limitS = timeLimit / 1000;

    switch (language) {
      case "c":
        filename = `${containerName}.c`;
        compileRunCmd = `gcc ${containerName}.c -o ${containerName} && timeout 1s stdbuf -i0 -o0 -e0 ./${containerName}`;
        break;
      case "cpp":
        filename = `${containerName}.cpp`;
        compileRunCmd = `g++ ${containerName}.cpp -o ${containerName} && timeout 1s stdbuf -i0 -o0 -e0 ./${containerName}`;
        break;
      case "java":
        const classMatch = codeContent.match(
          /public\s+class\s+([a-zA-Z_$][a-zA-Z\d_$]*)/
        );
        const className = classMatch ? classMatch[1] : "Main";

        filename = `${className}.java`;
        compileRunCmd = `javac ${filename} && timeout 1s stdbuf -i0 -o0 -e0 java ${className}`;
        break;
      case "python":
        filename = "main.py";
        compileRunCmd = `timeout 1s stdbuf -i0 -o0 -e0 python3 -u main.py`;
        break;
      default:
        return resolve({
          success: false,
          message: "Running code failed, Language unsupported",
        });
    }

    const tempDir = path.resolve(BASE_DIR, "temp", uid);
    const hostTempDir = path
      .join(HOST_BASE_DIR, "temp", uid)
      .replace(/\\/g, "/");
    const sourcePath = path.join(tempDir, filename);

    if (fs.existsSync(sourcePath)) {
      fs.rmSync(sourcePath, { recursive: true, force: true });
    }

    fs.mkdirSync(tempDir, { recursive: true });
    fs.writeFileSync(sourcePath, codeContent);

    (async () => {
      await new Promise((r) => setTimeout(r, 500));
    })();

    const dockerArgs = [
      "run",
      "--rm",
      "--name",
      `${containerName}`,
      "-i",
      "--user",
      "root",
      "--cpus=1",
      "--memory=128m",
      "--network=none",
      "-v",
      `${hostTempDir}:/app`,
      "-w",
      "/app",
      "coderun-modern-lms-sandbox",
      "sh",
      "-c",
      compileRunCmd,
    ];

    const dockerProcess = spawn("docker", dockerArgs, {
      stdio: ["pipe", "pipe", "pipe"],
    });

    if (input && input.length > 0) {
      dockerProcess.stdin.write(input.trim() + "\n");
    }

    let output = "";
    let isFinished = false;

    const MAX_OUTPUT = 512 * 1024; // 512KB

    dockerProcess.stdout.on("data", (d) => {
      if (output.length < MAX_OUTPUT) {
        output += d.toString();
      }
    });

    dockerProcess.stderr.on("data", (data) => {
      output += data.toString();
    });

    const timeout = setTimeout(() => {
      if (isFinished) return;
      isFinished = true;

      const cleanOutput = output
        .replace(/\x1B(?:[@-Z\\-_]|\[[0-?]*[ -/]*[@-~])/g, "")
        .replace(/\r\n/g, "\n")
        .trim();

      dockerProcess.kill("SIGTERM");
      exec(`docker rm -f ${containerName}`);

      resolve({
        success: false,
        message: "Running code failed, Execution Timed Out",
        output: cleanOutput,
      });
    }, Math.max(Number(timeLimit), 5000));

    dockerProcess.on("close", (exitCode) => {
      if (isFinished) return;

      isFinished = true;

      let status = "OK";

      if (exitCode === 124) {
        status = "Time Limit Exceeded";
      } else if (exitCode !== 0) {
        status = "Runtime Error";
      }

      clearTimeout(timeout);

      const cleanOutput = output
        .replace(/\x1B(?:[@-Z\\-_]|\[[0-?]*[ -/]*[@-~])/g, "")
        .replace(/\r\n/g, "\n")
        .trim();

      resolve({
        success: exitCode === 0,
        message:
          exitCode === 0
            ? "Running code successfully"
            : "Execution/Compile Error",
        output: cleanOutput,
      });
    });
  });
};
