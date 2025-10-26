import UserSeeder from "./UserSeeder.js";
import ClassroomSeeder from "./ClassroomSeeder.js";
import StudentClassroomSeeder from "./StudentClassroomSeeder.js";

const seeder = async () => {
  try {
    await UserSeeder();
    await ClassroomSeeder();
    await StudentClassroomSeeder();

    console.log("Database seeding successfully");
  } catch (error) {
    console.error("Database seeding failed", error);
  }
};

seeder();
