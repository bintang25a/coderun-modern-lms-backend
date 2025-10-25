import { Classroom } from "../models/Model.js";

export default async function ClassroomSeeder() {
  const classrooms = [
    {
      class_code: "IF23A1",
      name: "Pemrograman Web",
    },
    {
      class_code: "TI24B2",
      name: "Struktur Data",
    },
    {
      class_code: "CS25C3",
      name: "Jaringan Komputer",
    },
  ];

  await Classroom.bulkCreate(classrooms);
  console.log("Classroom seeding successfully");
}
