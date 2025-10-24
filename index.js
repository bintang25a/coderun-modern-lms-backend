import express from "express";
import session from "express-session";
import cors from "cors";
import fs from "fs";
import { fileURLToPath } from "url";
import path from "path";
import dotenv from "dotenv";
import SequelizeStore from "connect-session-sequelize";
import UserRoute from "./app/routes/UserRoute.js";
import ClassroomRoute from "./app/routes/ClassroomRoute.js";
import UserClassroomRoute from "./app/routes/UserClassroomRoute.js";
import AssignmentRoute from "./app/routes/AssignmentRoute.js";
import SubmissionRoute from "./app/routes/SubmissionRoute.js";
import AuthRoute from "./app/routes/AuthRoute.js";
import { db } from "./database/models/index.js";

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
app.use(AssignmentRoute);
app.use(SubmissionRoute);
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

app.listen(port, () => console.log(`Server run on http://localhost:${port}`));
