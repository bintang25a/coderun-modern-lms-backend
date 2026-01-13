export const runPythonModel = async (param) =>
  new Promise((resolve, reject) => {
    const { modelPyPath, datasetPath, answerPath, resultPath } = param;

    const py = spawn("python3", [
      modelPyPath,
      datasetPath,
      answerPath,
      resultPath,
    ]);

    let stdout = "";
    let stderr = "";

    py.stdout.on("data", (data) => {
      stdout += data.toString();
    });

    py.stderr.on("data", (data) => {
      stderr += data.toString();
    });

    py.on("close", (code) => {
      if (code !== 0) {
        return reject({
          message: "Python model execution failed",
          stderr,
        });
      }
      resolve(stdout);
    });
  });
