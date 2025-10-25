import { DataTypes } from "sequelize";
import { db } from "./Model.js";

const Assignment = db.define("assignments", {
  assignment_number: {
    type: DataTypes.STRING(36),
    allowNull: false,
    primaryKey: true,
  },
  assistant_uid: {
    type: DataTypes.STRING(16),
    allowNull: false,
    references: {
      model: "users",
      key: "uid",
    },
  },
  class_code: {
    type: DataTypes.STRING(32),
    allowNull: false,
    references: {
      model: "classrooms",
      key: "class_code",
    },
  },
  title: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  description: {
    type: DataTypes.TEXT,
    allowNull: false,
  },
  answer_key: {
    type: DataTypes.TEXT,
    allowNull: true,
  },
  start_time: {
    type: DataTypes.DATE,
    allowNull: false,
  },
  end_time: {
    type: DataTypes.DATE,
    allowNull: false,
  },
});

export default Assignment;
