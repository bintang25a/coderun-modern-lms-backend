import db from "../config/database.js";
import User from "./UserModel.js";
import Classroom from "./ClassroomModel.js";
import UserClassroom from "./UserClassroomModel.js";

// Loan.belongsTo(User, {
//   foreignKey: "id_peminjam",
//   targetKey: "uid",
// });
// Loan.belongsTo(Room, {
//   foreignKey: "kode_ruangan",
//   targetKey: "kode_ruangan",
// });

User.belongsToMany(Classroom, {
  through: "user_classroom",
  foreignKey: "uid",
  otherKey: "class_code",
  as: "classroom",
});
Classroom.belongsToMany(User, {
  through: "user_classroom",
  foreignKey: "class_code",
  otherKey: "uid",
  as: "praktikan",
});
Classroom.belongsTo(User, {
  foreignKey: "uid_asisten1",
  sourceKey: "uid",
  as: "tutor",
});
Classroom.belongsTo(User, {
  foreignKey: "uid_asisten2",
  sourceKey: "uid",
  as: "asisten",
});

// Room.hasMany(Loan, {
//   foreignKey: "kode_ruangan",
//   sourceKey: "kode_ruangan",
// });

export { db, User, Classroom };
