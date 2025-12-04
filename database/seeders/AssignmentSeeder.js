import { Assignment } from "../models/Model.js";

export default async function AssignmentSeeder() {
  const assignments = [
    {
      assignment_number: "CS25C3-01",
      class_code: "CS25C3",
      assistant_uid: "U002",
      title: "Tugas 1",
      description: "Kelas king",
      answer_key: "public\\assignments\\CS25C3-01\\answer_key.c",
      startAt: "2025-10-24T14:30:00.000Z",
      endAt: "2025-10-25T14:30:00.000Z",
      overtime: false,
    },
    {
      assignment_number: "CS25C3-02",
      class_code: "CS25C3",
      assistant_uid: "U002",
      title: "Tugas 2",
      description: "Kelas king",
      answer_key: "public\\assignments\\CS25C3-02\\answer_key.c",
      startAt: "2025-10-24T14:30:00.000Z",
      endAt: "2025-10-25T14:30:00.000Z",
      overtime: false,
    },
    {
      assignment_number: "IF23A1-01",
      class_code: "IF23A1",
      assistant_uid: "U003",
      title: "Tugas 1",
      description: "Kelas king",
      answer_key: "public\\assignments\\IF23A1-01\\answer_key.java",
      startAt: "2025-10-24T14:30:00.000Z",
      endAt: "2025-10-25T14:30:00.000Z",
      overtime: false,
    },
  ];

  await Assignment.bulkCreate(assignments);
  console.log("Assignment seeding successfully");
}
