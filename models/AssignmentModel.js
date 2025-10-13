import { DataTypes } from "sequelize";
import { db } from "./index.js";

const Assignment = db.define("assignments", {
  assignment_number: {
    type: DataTypes.STRING(36),
    allowNull: false,
    primaryKey: true,
  },
  title: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  description: {
    type: DataTypes.TEXT,
    allowNull: false,
  },
  class_code: {
    type: DataTypes.STRING(32),
    allowNull: false,
    references: {
      model: "classrooms",
      key: "class_code",
    },
  },
  assistant_uid: {
    type: DataTypes.STRING(16),
    allowNull: false,
    references: {
      model: "users",
      key: "uid",
    },
  },
  answer_key: {
    type: DataTypes.TEXT,
    allowNull: true,
  },
});

export default Assignment;
