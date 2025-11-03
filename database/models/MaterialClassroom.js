import { DataTypes } from "sequelize";
import { db } from "./Model.js";

const MaterialClassroom = db.define("material_classroom", {
  material_number: {
    type: DataTypes.STRING(64),
    allowNull: false,
    references: {
      model: "materials",
      key: "material_number",
    },
    onDelete: "CASCADE",
    onUpdate: "CASCADE",
  },
  class_code: {
    type: DataTypes.STRING(32),
    allowNull: false,
    references: {
      model: "classrooms",
      key: "class_code",
    },
    onDelete: "CASCADE",
    onUpdate: "CASCADE",
  },
});

export default MaterialClassroom;
