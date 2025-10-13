import express from "express";
import session from "express-session";
import cors from "cors";
import fs from "fs";
import { fileURLToPath } from "url";
import path from "path";
import dotenv from "dotenv";
import SequelizeStore from "connect-session-sequelize";
import UserRoute from "./routes/UserRoute.js";
import ClassroomRoute from "./routes/ClassroomRoute.js";
import UserClassroomRoute from "./routes/UserClassroomRoute.js";
import AuthRoute from "./routes/AuthRoute.js";
import { db } from "./models/index.js";

dotenv.config();

const app = express();
const port = process.env.APP_PORT;

const sessionStore = SequelizeStore(session.Store);

const store = new sessionStore({
  db,
});

app.use(
  cors({
    credentials: true,
    origin: [
      "https://7418fqfm-5173.asse.devtunnels.ms",
      "http://localhost:5173",
    ],
  })
);

app.use(
  session({
    secret: process.env.SESS_KEY,
    resave: false,
    saveUninitialized: false,
    store,
    cookie: {
      maxAge: 1 * 60 * 60 * 1000, // 1 hari
      secure: "auto",
      httpOnly: true,
      sameSite: "lax",
    },
  })
);

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

app.use(express.json());
app.use(UserRoute);
app.use(ClassroomRoute);
app.use(UserClassroomRoute);
app.use(AuthRoute);

app.use("/users/photo", express.static(path.join(__dirname, "src/profiles")));

const srcPath = path.resolve("src");
if (!fs.existsSync(srcPath)) {
  fs.mkdirSync(srcPath);
}

const classroomPath = "./src/classrooms";
if (!fs.existsSync(classroomPath)) {
  fs.mkdirSync(classroomPath);
}

// (async () => {
//   try {
//     await db.query("SET FOREIGN_KEY_CHECKS = 0");
//     await db.sync({ force: true });

//     console.log("Database synced (tables created)");

//     const { default: UserSeeder } = await import("./seed/UserSeeder.js");
//     const { default: ClassroomSeeder } = await import(
//       "./seed/ClassroomSeeder.js"
//     );
//     const { default: UserClassroomSeeder } = await import(
//       "./seed/UserClassroomSeeder.js"
//     );

//     await UserSeeder();
//     await ClassroomSeeder();
//     await UserClassroomSeeder();
//   } catch (err) {
//     console.error("Gagal sync:", err);
//   }
// })();

app.listen(port, () => console.log(`Server run on http://localhost:${port}`));
