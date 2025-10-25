import { DataTypes } from "sequelize";
import { db } from "./Model.js";

const Classroom = db.define("classrooms", {
  class_code: {
    type: DataTypes.STRING(32),
    allowNull: false,
    primaryKey: true,
  },
  name: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  assistant1_uid: {
    type: DataTypes.STRING(16),
    allowNull: true,
    references: {
      model: "users",
      key: "uid",
    },
  },
  assistant2_uid: {
    type: DataTypes.STRING(16),
    allowNull: true,
    references: {
      model: "users",
      key: "uid",
    },
  },
});

export default Classroom;
