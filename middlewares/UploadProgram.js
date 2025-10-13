import multer from "multer";
import path from "path";
import fs from "fs";

// Tentukan folder utama penyimpanan
const srcPath = path.resolve("src");

const storage = multer.diskStorage({
  destination: (req, file, cb) => {
    try {
      // Ambil class_code dari request body (pastikan dikirim dari frontend)
      const { class_code, asisten_uid, uid } = req.body;
      const identifier = asisten_uid ? "answer_key" : uid || undefined;

      if (!class_code || !identifier) {
        return cb(new Error("class_code harus disertakan di body request"));
      }

      // Buat folder dinamis berdasarkan class_code
      const classFolder = path.join(
        srcPath,
        "classrooms",
        class_code,
        req.assignment_number,
        identifier
      );

      // Buat folder jika belum ada
      if (!fs.existsSync(classFolder)) {
        fs.mkdirSync(classFolder, { recursive: true });
      }

      cb(null, classFolder);
    } catch (err) {
      cb(err);
    }
  },

  filename: (req, file, cb) => {
    const fileName = path.extname(file.originalname);

    cb(null, fileName);
  },
});

// Filter ekstensi file
const fileFilter = (req, file, cb) => {
  const allowedExtensions = /\.(c|cpp|java|py)$/i;
  const ext = path.extname(file.originalname).toLowerCase();

  if (allowedExtensions.test(ext)) {
    cb(null, true);
  } else {
    cb(new Error("Hanya file .c, .cpp, .java, atau .py yang diperbolehkan"));
  }
};

// Batas ukuran maksimal 5 MB
const uploadProgram = multer({
  storage,
  fileFilter,
  limits: { fileSize: 5 * 1024 * 1024 },
});

export default uploadProgram;
