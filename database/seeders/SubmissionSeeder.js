import { Submission } from "../models/Model.js";
import path from "path";
import fs from "fs";

export default async function SubmissionSeeder() {
  const submissions = [
    {
      submission_number: "U010-20251104062613161",
      assignment_number: "CS25C3-01",
      student_uid: "U010",
      answer: "public\\assignments\\CS25C3-01\\U010.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613162",
      assignment_number: "CS25C3-01",
      student_uid: "U011",
      answer: "public\\assignments\\CS25C3-01\\U011.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613163",
      assignment_number: "CS25C3-01",
      student_uid: "U012",
      answer: "public\\assignments\\CS25C3-01\\U012.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613164",
      assignment_number: "CS25C3-01",
      student_uid: "U013",
      answer: "public\\assignments\\CS25C3-01\\U013.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613165",
      assignment_number: "CS25C3-01",
      student_uid: "U014",
      answer: "public\\assignments\\CS25C3-01\\U014.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613166",
      assignment_number: "CS25C3-01",
      student_uid: "U015",
      answer: "public\\assignments\\CS25C3-01\\U015.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613167",
      assignment_number: "CS25C3-01",
      student_uid: "U016",
      answer: "public\\assignments\\CS25C3-01\\U016.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613155",
      assignment_number: "CS25C3-01",
      student_uid: "U017",
      answer: "public\\assignments\\CS25C3-01\\U017.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613145",
      assignment_number: "CS25C3-01",
      student_uid: "U018",
      answer: "public\\assignments\\CS25C3-01\\U018.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613144",
      assignment_number: "CS25C3-01",
      student_uid: "U019",
      answer: "public\\assignments\\CS25C3-01\\U019.c",
      grade: null,
    },

    {
      submission_number: "U001-20251104062613177",
      assignment_number: "IF23A1-01",
      student_uid: "U001",
      answer: "public\\assignments\\IF23A1-01\\U001.java",
      grade: null,
    },
    {
      submission_number: "U002-20251104062613178",
      assignment_number: "IF23A1-01",
      student_uid: "U002",
      answer: "public\\assignments\\IF23A1-01\\U002.java",
      grade: null,
    },
    {
      submission_number: "U003-20251104062613172",
      assignment_number: "IF23A1-01",
      student_uid: "U003",
      answer: "public\\assignments\\IF23A1-01\\U003.java",
      grade: null,
    },
    {
      submission_number: "U004-20251104062613172",
      assignment_number: "IF23A1-01",
      student_uid: "U004",
      answer: "public\\assignments\\IF23A1-01\\U004.java",
      grade: null,
    },
    {
      submission_number: "U005-20251104062613172",
      assignment_number: "IF23A1-01",
      student_uid: "U005",
      answer: "public\\assignments\\IF23A1-01\\U005.java",
      grade: null,
    },
  ];

  const databasePath = path.resolve("database");
  const srcPath1 = path.join(databasePath, "/resource/_dataset2");
  const srcPath2 = path.join(databasePath, "/resource/_dataset3");

  const publicPath = path.resolve("public");
  const destPath1 = path.join(publicPath, "assignments/CS25C3-01");
  const destPath2 = path.join(publicPath, "assignments/IF23A1-01");

  fs.mkdirSync(destPath1, { recursive: true });
  fs.mkdirSync(destPath2, { recursive: true });

  const entries1 = fs.readdirSync(srcPath1, { withFileTypes: true });
  const entries2 = fs.readdirSync(srcPath2, { withFileTypes: true });

  for (let entry of entries1) {
    const src = path.join(srcPath1, entry.name);
    const dest = path.join(destPath1, entry.name);

    if (entry.isDirectory()) {
      fs.mkdirSync(dest, { recursive: true });
    } else {
      fs.copyFileSync(src, dest);
    }
  }

  for (let entry of entries2) {
    const src = path.join(srcPath2, entry.name);
    const dest = path.join(destPath2, entry.name);

    if (entry.isDirectory()) {
      fs.mkdirSync(dest, { recursive: true });
    } else {
      fs.copyFileSync(src, dest);
    }
  }

  await Submission.bulkCreate(submissions);
  console.log("Submission seeding successfully");
}
