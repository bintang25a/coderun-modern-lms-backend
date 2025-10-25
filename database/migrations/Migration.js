import { db } from "../models/Model.js";

const migration = async () => {
  try {
    await db.query("SET FOREIGN_KEY_CHECKS = 0");
    await db.sync({ force: true });

    console.log("Database migration successfully");
  } catch (error) {
    console.error("Database migration failed:", error);
  }
};

migration();
