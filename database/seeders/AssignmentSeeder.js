import { Assignment } from "../models/Model.js";

export default async function AssignmentSeeder() {
  const assignments = [
    {
      assignment_number: "CS25C3-01",
      class_code: "CS25C3",
      assistant_uid: "U002",
      title: "Tugas 1",
      description: "Kelas king",
      answer_key: ".....",
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
      answer_key: ".....",
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
      answer_key: ".....",
      startAt: "2025-10-24T14:30:00.000Z",
      endAt: "2025-10-25T14:30:00.000Z",
      overtime: false,
    },
  ];

  await Assignment.bulkCreate(assignments);
  console.log("Assignment seeding successfully");
}
