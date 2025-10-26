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
});

export default Classroom;
