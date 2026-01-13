export const executeCode1 = async (param) => {
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
        compileRunCmd = `javac ${filename} && java -Djdk.console=java.base -Dsun.stdout.buffered=false ${className}`;
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

    const MAX_OUTPUT = 512 * 1024;

    dockerProcess.stdout.on("data", (d) => {
      if (output.length < MAX_OUTPUT) {
        output += d.toString();
      }
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
