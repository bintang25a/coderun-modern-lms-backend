import { DataTypes } from "sequelize";
import { db } from "./Model.js";

const UserClassroom = db.define("user_classroom", {
  uid: {
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
});

export default UserClassroom;
