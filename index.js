import express from "express";
import fs from "fs";
import { fileURLToPath } from "url";
import path from "path";
import dotenv from "dotenv";
import cors from "./config/cors.js";
import UserRoute from "./app/routes/UserRoute.js";
import ClassroomRoute from "./app/routes/ClassroomRoute.js";
import UserClassroomRoute from "./app/routes/UserClassroomRoute.js";
import AssignmentRoute from "./app/routes/AssignmentRoute.js";
import SubmissionRoute from "./app/routes/SubmissionRoute.js";
import AuthRoute from "./app/routes/AuthRoute.js";

dotenv.config();

const app = express();
const port = process.env.APP_PORT;

app.use(cors);
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use(AuthRoute);
app.use(UserRoute);
app.use(ClassroomRoute);
app.use(UserClassroomRoute);
app.use(AssignmentRoute);
app.use(SubmissionRoute);

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
app.use(
  "/users/photo",
  express.static(path.join(__dirname, "public/profiles"))
);

const classroomPath = "./public/classrooms";
if (!fs.existsSync(classroomPath)) {
  fs.mkdirSync(classroomPath, { recursive: true });
}

const profilesPath = "./public/profiles";
if (!fs.existsSync(profilesPath)) {
  fs.mkdirSync(profilesPath, { recursive: true });
}

app.listen(port, () => console.log(`Server run on http://localhost:${port}`));
