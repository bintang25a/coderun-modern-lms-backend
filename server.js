import express from "express";
import fs from "fs";
import { exec } from "child_process";
import path from "path";
import { fileURLToPath } from "url";
import cors from "cors";

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const app = express();
app.use(express.json());
app.use(cors());

// Buat folder temp kalau belum ada
const tempDir = path.join(__dirname, "temp");
if (!fs.existsSync(tempDir)) fs.mkdirSync(tempDir);

// Fungsi untuk buat file sementara
const makeTempFile = (ext) => {
  const filename = `code_${Date.now()}.${ext}`;
  return path.join(tempDir, filename);
};

// Endpoint utama
app.post("/run", async (req, res) => {
  const { language, code } = req.body;

  if (!language || !code) {
    return res.status(400).json({ error: "language dan code harus diisi" });
  }

  let filename, command;

  try {
    switch (language.toLowerCase()) {
      case "c": {
        filename = makeTempFile("c");
        fs.writeFileSync(filename, code);
        const outputFile = filename.replace(".c", ".exe");
        command = `gcc "${filename}" -o "${outputFile}" && "${outputFile}"`;
        break;
      }
      case "cpp":
      case "c++": {
        filename = makeTempFile("cpp");
        fs.writeFileSync(filename, code);
        const outputFile = filename.replace(".cpp", ".exe");
        command = `g++ "${filename}" -o "${outputFile}" && "${outputFile}"`;
        break;
      }
      case "java": {
        filename = makeTempFile("java");
        fs.writeFileSync(filename, code);
        const classname = path.basename(filename, ".java");
        const dir = path.dirname(filename);
        command = `javac "${filename}" && java -cp "${dir}" ${classname}`;
        break;
      }
      default:
        return res.status(400).json({ error: "Bahasa tidak didukung" });
    }

    exec(command, { timeout: 8000 }, (err, stdout, stderr) => {
      if (err) {
        return res.json({
          success: false,
          error: stderr || err.message,
        });
      }
      return res.json({
        success: true,
        output: stdout,
      });
    });
  } catch (e) {
    res.status(500).json({ error: e.message });
  }
});

const PORT = 3000;
app.listen(PORT, () =>
  console.log(`Server running on http://localhost:${PORT}`)
);
