import {
  StudentClassroom,
  User,
  Classroom,
} from "../../database/models/Model.js";

export const index = async (req, res) => {
  try {
    const classrooms = await StudentClassroom.findAll();

    res.status(200).json({
      success: true,
      message: "Display all students in classrooms successfully",
      data: classrooms,
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Display all students in classrooms failed",
    });
  }
};

export const store = async (req, res) => {
  const { class_code, uid } = req.body;

  if (!class_code || !uid) {
    return res.status(400).json({
      success: false,
      message: "Create student in classroom failed, Field cannot empty",
    });
  }

  const noUser = await StudentClassroom.findOne({
    where: {
      class_code,
      uid,
    },
  });

  if (noUser) {
    return res.status(400).json({
      success: false,
      message: "Create student in classroom failed, User already in classroom",
    });
  }

  const student = await User.findOne({
    where: {
      uid,
    },
  });

  const classroom = await Classroom.findOne({
    where: {
      class_code,
    },
  });

  if (!student || !classroom) {
    return res.status(404).json({
      success: false,
      message:
        "Create student in classroom failed, UID or Class code not found",
    });
  }

  try {
    await StudentClassroom.create({
      class_code,
      uid,
    });

    res.status(201).json({
      success: true,
      message: "Create student in classroom successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Create student in classroom failed",
    });
  }
};

export const destroy = async (req, res) => {
  const classroom = await StudentClassroom.findOne({
    where: {
      class_code: req.params.class_code,
      uid: req.params.uid,
    },
  });

  if (!classroom) {
    return res.status(404).json({
      success: false,
      message:
        "Delete student in classroom failed, User or Classroom not found",
    });
  }

  try {
    await StudentClassroom.destroy({
      where: {
        class_code: req.params.class_code,
        uid: req.params.uid,
      },
    });

    res.status(200).json({
      success: true,
      message: "Delete student in classroom successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Delete student in classroom failed",
    });
  }
};
