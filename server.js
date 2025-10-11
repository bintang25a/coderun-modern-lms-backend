import cors from "cors";
import fs from "fs";
import { spawn, exec } from "child_process";
import path from "path";

const app = express();
app.use(cors());
app.use(express.json());

const TEMP_DIR = "./temp";
if (!fs.existsSync(TEMP_DIR)) fs.mkdirSync(TEMP_DIR);

app.post("/run", async (req, res) => {
  const { language, code, input = "" } = req.body;
  if (!language || !code)
    return res.status(400).json({ error: "language dan code wajib diisi" });

  const timestamp = Date.now();
  const fileBase = `code_${timestamp}`;
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
    return res.status(400).json({ error: "Bahasa tidak didukung" });
  }

  // Simpan kode ke file
  fs.writeFileSync(filePath, code);

  // Compile dulu
  exec(compileCmd, (compileErr, _, compileStderr) => {
    if (compileErr) {
      return res.status(400).json({ error: compileStderr.toString() });
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
          error:
            "Program berjalan terlalu lama atau menunggu input berikutnya (kemungkinan loop tak berujung).",
        });
      }

      if (error) {
        return res.status(400).json({ error });
      }

      res.json({ output: output.trim(), exitCode: code });
    });
  });
});

app.listen(3000, () => console.log("Server running on http://localhost:3000"));
