import { DataTypes } from "sequelize";
import { db } from "./index.js";

const Submission = db.define("submissions", {
  submission_number: {
    type: DataTypes.STRING,
    allowNull: false,
    primaryKey: true,
  },
  assignment_number: {
    type: DataTypes.STRING(36),
    allowNull: false,
    references: {
      model: "assignments",
      key: "assignment_number",
    },
  },
  student_uid: {
    type: DataTypes.STRING(16),
    allowNull: false,
    references: {
      model: "users",
      key: "uid",
    },
  },
  answer: {
    type: DataTypes.TEXT,
    allowNull: false,
  },
});

export default Submission;
