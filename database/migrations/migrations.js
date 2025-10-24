import { db } from "../models/index.js";
import UserSeeder from "../seeds/UserSeeder.js";
import ClassroomSeeder from "../seeds/ClassroomSeeder.js";
import UserClassroomSeeder from "../seeds/UserClassroomSeeder.js";

const migrate = async () => {
  try {
    await db.query("SET FOREIGN_KEY_CHECKS = 0");
    await db.sync({ force: true });

    await UserSeeder();
    await ClassroomSeeder();
    await UserClassroomSeeder();

    console.log("Database synced (tables created)");
  } catch (err) {
    console.error("Gagal sync:", err);
  }
};

migrate();
