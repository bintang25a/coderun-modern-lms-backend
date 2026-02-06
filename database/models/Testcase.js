import { DataTypes } from "sequelize";
import { db } from "./Model.js";

const Testcase = db.define("testcases", {
  testcase_number: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true,
  },
  assignment_number: {
    type: DataTypes.STRING(36),
    allowNull: false,
    references: {
      model: "assignments",
      key: "assignment_number",
    },
  },
  name: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  weight: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
});

export default Testcase;
