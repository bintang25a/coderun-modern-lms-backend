import { DataTypes } from "sequelize";
import db from "../config/database.js";

const Classroom = db.define("classrooms", {
  class_code: {
    type: DataTypes.STRING(64),
    allowNull: false,
    primaryKey: true,
  },
  name: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  uid_asisten1: {
    type: DataTypes.STRING(16),
    allowNull: true,
    references: {
      model: "users",
      key: "uid",
    },
  },
  uid_asisten2: {
    type: DataTypes.STRING(16),
    allowNull: true,
    references: {
      model: "users",
      key: "uid",
    },
  },
});

export default Classroom;
