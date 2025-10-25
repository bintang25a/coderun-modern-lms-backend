import UserSeeder from "./UserSeeder.js";
import ClassroomSeeder from "./ClassroomSeeder.js";
import UserClassroomSeeder from "./UserClassroomSeeder.js";

const seeder = async () => {
  try {
    await UserSeeder();
    await ClassroomSeeder();
    await UserClassroomSeeder();

    console.log("Database seeding successfully");
  } catch (error) {
    console.error("Database seeding failed", error);
  }
};

seeder();
