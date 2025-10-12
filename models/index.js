import db from "../config/database.js";
import User from "./UserModel.js";
import Classroom from "./ClassroomModel.js";
import UserClassroom from "./UserClassroomModel.js";
import Assignment from "./AssignmentModel.js";

User.belongsToMany(Classroom, {
  through: UserClassroom,
  foreignKey: "uid",
  otherKey: "class_code",
  as: "classroom",
});

Classroom.belongsToMany(User, {
  through: UserClassroom,
  foreignKey: "class_code",
  otherKey: "uid",
  as: "praktikan",
});
Classroom.belongsTo(User, {
  foreignKey: "uid_asisten1",
  sourceKey: "uid",
  as: "tutor",
  constraints: false,
});
Classroom.belongsTo(User, {
  foreignKey: "uid_asisten2",
  sourceKey: "uid",
  as: "asisten",
  constraints: false,
});
Classroom.hasMany(Assignment, {
  foreignKey: "class_code",
  sourceKey: "class_code",
  as: "assignment",
});

UserClassroom.belongsTo(User, {
  foreignKey: "uid",
  as: "user",
});
UserClassroom.belongsTo(Classroom, {
  foreignKey: "class_code",
  as: "classroom",
});

Assignment.belongsTo(Classroom, {
  foreignKey: "class_code",
  sourceKey: "class_code",
  as: "classroom",
});

export { db, User, Classroom, UserClassroom, Assignment };
