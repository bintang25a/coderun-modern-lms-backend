import fs from "fs";
import path from "path";

// --- KONFIGURASI ---
const TARGET_FOLDER = "./trash/rename"; // Folder tempat file Java berada
const NEW_BASE_NAME = "poscode"; // Nama dasar (a1, a2, ...)
// -------------------

function processJavaFiles() {
  try {
    const files = fs
      .readdirSync(TARGET_FOLDER)
      .filter((file) => file.endsWith(".java"))
      .sort(); // Urutkan agar penomoran rapi

    if (files.length === 0) {
      console.log("Tidak ada file .java ditemukan.");
      return;
    }

    files.forEach((filename, index) => {
      const oldPath = path.join(TARGET_FOLDER, filename);
      const oldClassName = path.parse(filename).name;
      const newClassName = `${NEW_BASE_NAME}${index + 1}`;
      const newPath = path.join(TARGET_FOLDER, `${newClassName}.c`);

      // 1. Baca isi file
      let content = fs.readFileSync(oldPath, "utf8");

      // 2. Hapus baris package (menggunakan Regex)
      // Menghapus baris yang dimulai dengan 'package' sampai titik koma ';'
      content = content.replace(/^package\s+[\w.]+;\s*\r?\n?/gm, "");

      // 3. Update nama Class agar sesuai dengan nama file baru
      // Mencari 'public class NamaLama' dan menggantinya ke 'public class a1'
      const classRegex = new RegExp(`public\\s+class\\s+${oldClassName}`, "g");
      content = content.replace(classRegex, `public class ${newClassName}`);

      // 4. Tulis ulang file dengan konten baru
      fs.writeFileSync(oldPath, content, "utf8");

      // 5. Rename file fisik
      fs.renameSync(oldPath, newPath);

      console.log(
        `Berhasil: ${filename} -> ${newClassName}.java (Package dihapus & Class diupdate)`
      );
    });

    console.log("\nProses selesai semua!");
  } catch (err) {
    console.error("Terjadi kesalahan:", err.message);
  }
}

processJavaFiles();
