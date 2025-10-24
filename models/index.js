import db from "../config/database.js";
import User from "./UserModel.js";
import Classroom from "./ClassroomModel.js";
import UserClassroom from "./UserClassroomModel.js";
import Assignment from "./AssignmentModel.js";
import Submission from "./SubmissionModel.js";

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
  foreignKey: "assistant1_uid",
  sourceKey: "uid",
  as: "tutor",
  constraints: false,
});
Classroom.belongsTo(User, {
  foreignKey: "assistant2_uid",
  sourceKey: "uid",
  as: "assistant",
  constraints: false,
});
Classroom.hasMany(Assignment, {
  foreignKey: "class_code",
  sourceKey: "class_code",
  as: "assignments",
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

export { db, User, Classroom, UserClassroom, Assignment, Submission };
