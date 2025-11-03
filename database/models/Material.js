import { DataTypes } from "sequelize";
import { db } from "./Model.js";

const Material = db.define("materials", {
  material_number: {
    type: DataTypes.STRING(64),
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
    onUpdate: "CASCADE",
  },
  title: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  material: {
    type: DataTypes.TEXT,
    allowNull: false,
  },
});

export default Material;
