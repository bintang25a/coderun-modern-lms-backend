import fs from "fs";
import path from "path";

/**
 * Rename semua file program dalam folder
 * contoh output: program1.cpp, program2.cpp, ...
 *
 * @param {string} targetDir - folder target
 * @param {string} extension - ekstensi file (contoh: "cpp", "c", "java")
 * @param {string} prefix - nama awal file (default: "program")
 */
function renamePrograms(targetDir, extension, prefix = "program") {
  if (!fs.existsSync(targetDir)) {
    console.error("Folder tidak ditemukan:", targetDir);
    return;
  }

  const files = fs
    .readdirSync(targetDir)
    .filter((f) => f.endsWith(`.${extension}`))
    .sort(); // biar urut & konsisten

  let counter = 1;

  for (const file of files) {
    const oldPath = path.join(targetDir, file);
    const newName = `${prefix}${counter}.${extension}`;
    const newPath = path.join(targetDir, newName);

    // Hindari overwrite
    if (fs.existsSync(newPath)) {
      console.warn("Skip (sudah ada):", newName);
      counter++;
      continue;
    }

    fs.renameSync(oldPath, newPath);
    console.log(`${file} → ${newName}`);
    counter++;
  }

  console.log("✅ Rename selesai");
}

/* =======================
   CONTOH PEMAKAIAN
======================= */

// ganti sesuai kebutuhan
const folder = "./database/resource/cpp";
const language = "cpp";

renamePrograms(folder, language);
