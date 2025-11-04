import UserSeeder from "./UserSeeder.js";
import ClassroomSeeder from "./ClassroomSeeder.js";
import StudentClassroomSeeder from "./StudentClassroomSeeder.js";
import AssignmentSeeder from "./AssignmentSeeder.js";
import SubmissionSeeder from "./SubmissionSeeder.js";

const seeder = async () => {
  try {
    await UserSeeder();
    await ClassroomSeeder();
    await StudentClassroomSeeder();
    await AssignmentSeeder();
    await SubmissionSeeder();

    console.log("Database seeding successfully");
  } catch (error) {
    console.error("Database seeding failed", error);
  }
};

seeder();
