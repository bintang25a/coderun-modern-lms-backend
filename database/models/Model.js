import db from "../../config/database.js";
import User from "./User.js";
import Classroom from "./Classroom.js";
import UserClassroom from "./UserClassroom.js";
import Assignment from "./Assignment.js";
import Submission from "./Submission.js";
import { DataTypes } from "sequelize";

User.belongsToMany(Classroom, {
  through: UserClassroom,
  foreignKey: "uid",
  otherKey: "class_code",
  as: "classroom",
});

Classroom.belongsToMany(User, {
  through: UserClassroom,
  foreignKey: "class_code",
  otherKey: "uid",
  as: "praktikan",
});
Classroom.belongsTo(User, {
  foreignKey: "assistant1_uid",
  sourceKey: "uid",
  as: "tutor",
  constraints: false,
});
Classroom.belongsTo(User, {
  foreignKey: "assistant2_uid",
  sourceKey: "uid",
  as: "assistant",
  constraints: false,
});
Classroom.hasMany(Assignment, {
  foreignKey: "class_code",
  sourceKey: "class_code",
  as: "assignments",
});

UserClassroom.belongsTo(User, {
  foreignKey: "uid",
  as: "user",
});
UserClassroom.belongsTo(Classroom, {
  foreignKey: "class_code",
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
  as: "submission",
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
  UserClassroom,
  Assignment,
  Submission,
  Token,
  Setting,
};
