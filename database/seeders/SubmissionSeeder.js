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
  ];

  const databasePath = path.resolve("database");
  const srcPath = path.join(databasePath, "seeders/_dataset2");

  const publicPath = path.resolve("public");
  const destPath = path.join(publicPath, "assignments/CS25C3-01");

  fs.mkdirSync(destPath, { recursive: true });

  const entries = fs.readdirSync(srcPath, { withFileTypes: true });

  for (let entry of entries) {
    const src = path.join(srcPath, entry.name);
    const dest = path.join(destPath, entry.name);

    if (entry.isDirectory()) {
      fs.mkdirSync(dest, { recursive: true });
    } else {
      fs.copyFileSync(src, dest);
    }
  }

  await Submission.bulkCreate(submissions);
  console.log("Submission seeding successfully");
}
