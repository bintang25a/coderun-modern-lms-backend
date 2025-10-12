import UserClassroom from "../models/UserClassroomModel.js";

export default async function UserClassroomSeeder() {
  const classrooms = [
    {
      class_code: "IF23A1",
      uid: "U005",
    },
    {
      class_code: "IF23A1",
      uid: "U006",
    },
    {
      class_code: "IF23A1",
      uid: "U007",
    },
    {
      class_code: "IF23A1",
      uid: "U008",
    },
    {
      class_code: "IF23A1",
      uid: "U009",
    },
    {
      class_code: "IF23A1",
      uid: "U010",
    },
    {
      class_code: "TI24B2",
      uid: "U005",
    },
    {
      class_code: "TI24B2",
      uid: "U006",
    },
    {
      class_code: "CS25C3",
      uid: "U007",
    },
    {
      class_code: "CS25C3",
      uid: "U008",
    },
    {
      class_code: "CS25C3",
      uid: "U009",
    },
    {
      class_code: "CS25C3",
      uid: "U010",
    },
  ];

  await UserClassroom.bulkCreate(classrooms);
  console.log("✅ 3 Data dummy berhasil dimasukkan ke tabel UserClassroom");
}
