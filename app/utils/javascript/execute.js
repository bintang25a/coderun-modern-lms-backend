import fs from "fs";
import path from "path";
import { spawn } from "child_process";

const BASE_DIR = process.cwd();

export const executeCode = async (param) => {
  const { uid, language, codePath } = param;
  const { input = "", timeLimit = 5000, executeName = "sandbox" } = param;

  if (!language || !codePath || !uid) {
    return {
      success: false,
      message: "Running code failed, field cannot be empty",
    };
  }

  const absoluteCodePath = path.resolve(BASE_DIR, codePath);
  const tempDir = path.resolve(BASE_DIR, "temp", uid);

  if (!fs.existsSync(absoluteCodePath)) {
    return {
      success: false,
      message: "Running code failed, Source code not found",
    };
  }

  const codeContent = fs.readFileSync(absoluteCodePath, "utf8");
  if (!codeContent.trim()) {
    return {
      success: false,
      message: "Running code failed, Source code is empty",
    };
  }

  fs.mkdirSync(tempDir, { recursive: true });

  let filename, command;

  switch (language) {
    case "c":
      filename = `${executeName}.c`;
      command = `gcc ${filename} -o ${executeName} && stdbuf -i0 -o0 -e0 ./${executeName}`;
      break;

    case "cpp":
      filename = `${executeName}.cpp`;
      command = `g++ ${filename} -o ${executeName} && stdbuf -i0 -o0 -e0 ./${executeName}`;
      break;

    case "java": {
      const className = executeName;
      filename = `${className}.java`;

      let javaCode = codeContent;

      if (/public\s+class\s+\w+/.test(javaCode)) {
        javaCode = javaCode.replace(
          /public\s+class\s+\w+/,
          `public class ${className}`
        );
      } else {
        javaCode = `public class ${className} {\n${javaCode}\n}`;
      }

      fs.writeFileSync(path.join(tempDir, filename), javaCode);

      command = `javac ${filename} && java -Dsun.stdout.buffered=false ${className}`;
      break;
    }

    case "python":
      filename = `${executeName}.py`;
      command = `python3 -u ${filename}`;
      break;

    default:
      return {
        success: false,
        message: "Running code failed, Language unsupported",
      };
  }

  const sourcePath = path.join(tempDir, filename);

  if (language !== "java") {
    fs.writeFileSync(sourcePath, codeContent);
  }

  return new Promise((resolve) => {
    const child = spawn("bash", ["-c", command], {
      cwd: tempDir,
      env: process.env,
      stdio: ["pipe", "pipe", "pipe"],
    });

    let output = "";
    let finished = false;
    const MAX_OUTPUT = 512 * 1024;

    // ===== STDOUT =====
    child.stdout.on("data", (data) => {
      if (output.length < MAX_OUTPUT) {
        output += data.toString();
      }
    });

    // ===== STDERR =====
    child.stderr.on("data", (data) => {
      if (output.length < MAX_OUTPUT) {
        output += data.toString();
      }
    });

    // ===== INPUT =====
    if (input && input.length > 0) {
      child.stdin.write(input.trim() + "\n");
    }
    child.stdin.end();

    // ===== TIMEOUT =====
    const timer = setTimeout(() => {
      if (finished) return;
      finished = true;

      child.kill("SIGKILL");

      resolve({
        success: false,
        message: "Running code failed, Execution Timed Out",
        output: cleanOutput(output),
      });
    }, Math.min(Number(timeLimit), 20000));

    // ===== EXIT =====
    child.on("close", (code) => {
      if (finished) return;
      finished = true;
      clearTimeout(timer);

      resolve({
        success: code === 0,
        message:
          code === 0
            ? "Running code successfully"
            : "Execution / Compile Error",
        output: cleanOutput(output),
      });
    });
  });
};

// ===== HELPER =====
function cleanOutput(raw) {
  return raw
    .replace(/\x1B(?:[@-Z\\-_]|\[[0-?]*[ -/]*[@-~])/g, "")
    .replace(/\r\n/g, "\n")
    .trim();
}
