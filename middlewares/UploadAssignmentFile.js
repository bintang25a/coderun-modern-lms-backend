import multer from "multer";
import path from "path";
import fs from "fs";

// Tentukan folder utama penyimpanan
const srcPath = path.resolve("src");

const storage = multer.diskStorage({
  destination: (req, file, cb) => {
    try {
      const class_code = req.params.class_code;

      if (!class_code) {
        return cb(new Error("Class code unidentified"));
      }

      const classFolder = path.join(
        srcPath,
        "classrooms",
        class_code,
        req.assignment_number
      );

      if (!fs.existsSync(classFolder)) {
        fs.mkdirSync(classFolder, { recursive: true });
      }

      cb(null, classFolder);
    } catch (err) {
      cb(err);
    }
  },

  filename: (req, file, cb) => {
    const { assistant_uid, uid } = req.body;
    const identifier = assistant_uid ? "answer_key" : uid || undefined;
    const fileName = `${identifier}${path.extname(file.originalname)}`;

    cb(null, fileName);
  },
});

// Filter ekstensi file
const fileFilter = (req, file, cb) => {
  const allowedExtensions = /\.(?:c|cpp|java|py|zip|rar|pdf)$/i;
  const ext = path.extname(file.originalname).toLowerCase();

  if (allowedExtensions.test(ext)) {
    cb(null, true);
  } else {
    cb(new Error("Only file c, cpp, java, py, zip, rar, pdf allowed"));
  }
};

// Batas ukuran maksimal 5 MB
const uploadProgram = multer({
  storage,
  fileFilter,
  limits: { fileSize: 5 * 1024 * 1024 },
});

export default uploadProgram;
