import db from "../../config/database.js";
import User from "./User.js";
import Classroom from "./Classroom.js";
import AssistantClassroom from "./AssistantClassroom.js";
import StudentClassroom from "./StudentClassroom.js";
import Material from "./Materials.js";
import Assignment from "./Assignment.js";
import Submission from "./Submission.js";
import { DataTypes } from "sequelize";

User.belongsToMany(Classroom, {
  through: StudentClassroom,
  foreignKey: "uid",
  otherKey: "class_code",
  as: "classrooms",
});
User.belongsToMany(Classroom, {
  through: AssistantClassroom,
  foreignKey: "uid",
  otherKey: "class_code",
  as: "assists",
});

Classroom.belongsToMany(User, {
  through: StudentClassroom,
  foreignKey: "class_code",
  otherKey: "uid",
  as: "students",
});
Classroom.belongsToMany(User, {
  through: AssistantClassroom,
  foreignKey: "class_code",
  otherKey: "uid",
  as: "assistants",
});
Classroom.hasMany(Assignment, {
  foreignKey: "class_code",
  sourceKey: "class_code",
  as: "assignments",
});
Classroom.hasMany(Material, {
  foreignKey: "class_code",
  sourceKey: "class_code",
  as: "materials",
});

AssistantClassroom.belongsTo(User, {
  foreignKey: "uid",
  as: "user",
});
AssistantClassroom.belongsTo(Classroom, {
  foreignKey: "class_code",
  as: "classroom",
});

StudentClassroom.belongsTo(User, {
  foreignKey: "uid",
  as: "user",
});
StudentClassroom.belongsTo(Classroom, {
  foreignKey: "class_code",
  as: "classroom",
});

Material.belongsTo(Classroom, {
  foreignKey: "class_code",
  sourceKey: "class_code",
  as: "classroom",
});

Assignment.belongsTo(Classroom, {
  foreignKey: "class_code",
  sourceKey: "class_code",
  as: "classroom",
});
Assignment.hasMany(Submission, {
  foreignKey: "assignment_number",
  sourceKey: "assignment_number",
  as: "submissions",
});

Submission.belongsTo(User, {
  foreignKey: "student_uid",
  sourceKey: "uid",
  as: "student",
});

const Setting = db.define("settings", {
  key: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  value: {
    type: DataTypes.TEXT,
    allowNull: false,
  },
});

const Token = db.define("tokens", {
  token: {
    type: DataTypes.TEXT,
    allowNull: false,
  },
  expiredAt: {
    type: DataTypes.DATE,
    allowNull: false,
  },
});

export {
  db,
  User,
  Classroom,
  AssistantClassroom,
  StudentClassroom,
  Material,
  Assignment,
  Submission,
  Token,
  Setting,
};
