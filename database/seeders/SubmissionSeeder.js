import { Submission } from "../models/Model.js";

export default async function SubmissionSeeder() {
  const submissions = [
    {
      submission_number: "U010-20251104062613161",
      assignment_number: "CS25C3-01",
      student_uid: "U010",
      answer: "public\\classrooms\\CS25C3-01\\U010.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613162",
      assignment_number: "CS25C3-01",
      student_uid: "U011",
      answer: "public\\classrooms\\CS25C3-01\\U011.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613163",
      assignment_number: "CS25C3-01",
      student_uid: "U012",
      answer: "public\\classrooms\\CS25C3-01\\U012.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613164",
      assignment_number: "CS25C3-01",
      student_uid: "U013",
      answer: "public\\classrooms\\CS25C3-01\\U013.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613165",
      assignment_number: "CS25C3-01",
      student_uid: "U014",
      answer: "public\\classrooms\\CS25C3-01\\U014.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613166",
      assignment_number: "CS25C3-01",
      student_uid: "U015",
      answer: "public\\classrooms\\CS25C3-01\\U015.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613167",
      assignment_number: "CS25C3-01",
      student_uid: "U016",
      answer: "public\\classrooms\\CS25C3-01\\U016.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613155",
      assignment_number: "CS25C3-01",
      student_uid: "U017",
      answer: "public\\classrooms\\CS25C3-01\\U017.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613145",
      assignment_number: "CS25C3-01",
      student_uid: "U018",
      answer: "public\\classrooms\\CS25C3-01\\U018.c",
      grade: null,
    },
    {
      submission_number: "U010-20251104062613144",
      assignment_number: "CS25C3-01",
      student_uid: "U019",
      answer: "public\\classrooms\\CS25C3-01\\U019.c",
      grade: null,
    },
  ];

  await Submission.bulkCreate(submissions);
  console.log("Submission seeding successfully");
}
