import { DataTypes } from "sequelize";
import db from "../config/database.js";

const UserClassroom = db.define("user_classroom", {
  uid: {
    type: DataTypes.STRING(16),
    allowNull: false,
  },
  class_code: {
    type: DataTypes.STRING(64),
    allowNull: false,
  },
});

export default UserClassroom;
