import fs from "fs";
import path from "path";
import pty from "node-pty";
import { exec } from "child_process";

const BASE_DIR = process.cwd();
const HOST_BASE_DIR = process.env.HOST_PROJECT_PATH;

export function executeCode1({
  uid,
  language,
  codePath,
  input = "",
  timeLimit = 5000,
}) {
  return new Promise((resolve) => {
    if (!language || !codePath || !uid) {
      return resolve({
        success: false,
        error: "Field cannot be empty",
      });
    }

    const normalizedPath = codePath
      .replace(/\\/g, "/")
      .replace("backend", "app");
    const absoluteCodePath = path.resolve(BASE_DIR, normalizedPath);
    console.log(absoluteCodePath);

    if (!fs.existsSync(absoluteCodePath)) {
      return resolve({
        success: false,
        error: "Source code not found",
      });
    }

    const codeContent = fs.readFileSync(absoluteCodePath, "utf8");
    if (!codeContent.trim()) {
      return resolve({
        success: false,
        error: "Source code is empty",
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
        return resolve({
          success: false,
          error: "Language unsupported",
        });
    }

    const tempDir = path.resolve(BASE_DIR, "temp", uid);
    const hostTempDir = path
      .join(HOST_BASE_DIR, "temp", uid)
      .replace(/\\/g, "/");
    const sourcePath = path.join(tempDir, filename);

    fs.mkdirSync(tempDir, { recursive: true });
    fs.writeFileSync(sourcePath, codeContent);

    const containerName = `sandbox_${uid}`;

    const dockerArgs = [
      "run",
      "--rm",
      "--name",
      containerName,
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
    });

    let output = "";
    let finished = false;

    const formatOutput = (raw) =>
      raw
        .replace(/\x1B(?:[@-Z\\-_]|\[[0-?]*[ -/]*[@-~])/g, "")
        .replace(/\r\n/g, "\n")
        .trim();

    const inputQueue = input ? input.trim().split(/\s+/) : [];
    let inputIdx = 0;

    ptyProcess.onData((data) => {
      output += data;

      if (inputIdx < inputQueue.length) {
        ptyProcess.write(`${inputQueue[inputIdx]}\r`);
        inputIdx++;
      }
    });

    const timeout = setTimeout(() => {
      if (!finished) {
        finished = true;

        try {
          exec(`docker rm -f ${containerName}`);
          ptyProcess.kill();
        } catch {}

        resolve({
          success: true,
          output: formatOutput(output),
          isTimeout: true,
        });
      }
    }, Number(timeLimit));

    ptyProcess.onExit(({ exitCode }) => {
      if (finished) return;
      finished = true;
      clearTimeout(timeout);

      resolve({
        success: true,
        output: formatOutput(output),
        exitCode,
        isRuntimeError:
          exitCode !== 0 && output.toLowerCase().includes("error"),
      });
    });
  });
}

export const executeCode = async ({
  uid,
  language,
  codePath,
  input = "",
  timeLimit = 5000,
}) => {
  return new Promise((resolve) => {
    if (!language || !codePath || !uid) {
      return resolve({
        success: false,
        message: "Running code failed, field cannot be empty",
      });
    }

    const normalizedPath = codePath.replace(/\\/g, "/");
    // .replace("backend", "app");
    const absoluteCodePath = path.resolve(BASE_DIR, normalizedPath);
    console.log(absoluteCodePath);

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

        resolve({
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

      resolve({
        success: true,
        message:
          exitCode !== 0 && output.toLowerCase().includes("error")
            ? "Execution/Compile Error"
            : "",
        output: cleanOutput,
      });
    });
  });
};
