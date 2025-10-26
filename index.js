import express from "express";
import fs from "fs";
import { fileURLToPath } from "url";
import path from "path";
import dotenv from "dotenv";
import cors from "./config/cors.js";
import AuthRoute from "./app/routes/AuthRoute.js";
import UserRoute from "./app/routes/UserRoute.js";
import ClassroomRoute from "./app/routes/ClassroomRoute.js";
import AssistantClassroomRoute from "./app/routes/AssistantClassroomRoute.js";
import StudentClassroomRoute from "./app/routes/StudentClassroomRoute.js";
import MaterialRoute from "./app/routes/MaterialRoute.js";
import AssignmentRoute from "./app/routes/AssignmentRoute.js";
import SubmissionRoute from "./app/routes/SubmissionRoute.js";
import CoderunRoute from "./app/routes/CoderunRoute.js";

dotenv.config();

const app = express();
const port = process.env.APP_PORT;

app.use(cors);
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use(AuthRoute);
app.use("/users", UserRoute);
app.use("/classrooms", ClassroomRoute);
app.use("/assistants", AssistantClassroomRoute);
app.use("/students", StudentClassroomRoute);
app.use("/materials/:class_code", MaterialRoute);
app.use("/assignments/:class_code", AssignmentRoute);
app.use("/submissions/:assignment_number", SubmissionRoute);
app.use(CoderunRoute);

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
