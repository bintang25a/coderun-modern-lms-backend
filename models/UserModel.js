import { DataTypes } from "sequelize";
import db from "../config/database.js";

const User = db.define("users", {
  uid: {
    type: DataTypes.STRING(16),
    allowNull: false,
    primaryKey: true,
  },
  name: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  email: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  phone_number: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  role: {
    type: DataTypes.ENUM("Admin", "Asisten", "Praktikan"),
    allowNull: false,
  },
  password: {
    type: DataTypes.STRING,
    allowNull: true,
  },
  class_code: {
    type: DataTypes.STRING(64),
    allowNull: true,
    references: {
      model: "classrooms",
      key: "class_code",
    },
  },
});

export default User;
