import { spawn } from "child_process";

export const runPythonModel = (param) =>
  new Promise((resolve, reject) => {
    const {
      modelPyPath,
      datasetPath,
      answerPath,
      resultPath,
      timeLimit = 60000,
    } = param;

    const py = spawn("python3", [modelPyPath], {
      stdio: ["pipe", "ignore", "pipe"], // stdout di-ignore
    });

    let stderr = "";
    let finished = false;

    // kirim input ke python (SESUSAI URUTAN input())
    py.stdin.write(datasetPath + "\n");
    py.stdin.write(answerPath + "\n");
    py.stdin.write(resultPath + "\n");
    py.stdin.end();

    py.stderr.on("data", (d) => {
      stderr += d.toString();
    });

    const timer = setTimeout(() => {
      if (finished) return;
      finished = true;
      py.kill("SIGKILL");
      reject({ message: "Python model timeout" });
    }, timeLimit);

    py.on("close", (code) => {
      if (finished) return;
      finished = true;
      clearTimeout(timer);

      if (code !== 0) {
        return reject({
          message: "Python model failed",
          stderr,
        });
      }

      // sukses → JSON sudah ditulis oleh Python
      resolve({ success: true, resultPath });
    });
  });
