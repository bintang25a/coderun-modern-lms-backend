import {
  AssistantClassroom,
  User,
  Classroom,
} from "../../database/models/Model.js";

export const index = async (req, res) => {
  try {
    const classrooms = await AssistantClassroom.findAll();

    res.status(200).json({
      success: true,
      message: "Display all assistants in classrooms successfully",
      data: classrooms,
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Display all assistants in classrooms failed",
    });
  }
};

export const store = async (req, res) => {
  const { class_code, uid } = req.body;

  if (!class_code || !uid) {
    return res.status(400).json({
      success: false,
      message: "Create assistant in classroom failed, Field cannot empty",
    });
  }

  const noUser = await AssistantClassroom.findOne({
    where: {
      class_code,
      uid,
    },
  });

  if (noUser) {
    return res.status(400).json({
      success: false,
      message:
        "Create assistant in classroom failed, User already in classroom",
    });
  }

  const assistant = await User.findOne({
    where: {
      uid,
    },
  });

  const classroom = await Classroom.findOne({
    where: {
      class_code,
    },
  });

  if (!assistant || !classroom) {
    return res.status(404).json({
      success: false,
      message:
        "Create assistant in classroom failed, UID or Class code not found",
    });
  }

  try {
    await AssistantClassroom.create({
      class_code,
      uid,
    });

    res.status(201).json({
      success: true,
      message: "Create assistant in classroom successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Create assistant in classroom failed",
    });
  }
};

export const update = async (req, res) => {
  const { class_code, uid } = req.body;

  const studentClassroom = await AssistantClassroom.findOne({
    where: {
      class_code: req.params.class_code,
      uid: req.params.uid,
    },
  });

  if (!studentClassroom) {
    return res.status(404).json({
      success: false,
      message:
        "Update assistant in classroom failed, User or Classroom not found",
    });
  }

  if (!class_code || !uid) {
    return res.status(400).json({
      success: false,
      message: "Update assistant in classroom failed, Field cannot empty",
    });
  }

  const noUser = await AssistantClassroom.findOne({
    where: {
      class_code,
      uid,
    },
  });

  if (noUser) {
    return res.status(400).json({
      success: false,
      message:
        "Update assistant in classroom failed, User already in classroom",
    });
  }

  const assistant = await User.findOne({
    where: {
      uid,
    },
  });

  const classroom = await Classroom.findOne({
    where: {
      class_code,
    },
  });

  if (!assistant || !classroom) {
    return res.status(404).json({
      success: false,
      message:
        "Update assistant in classroom failed, UID or Class code not found",
    });
  }

  try {
    await studentClassroom.destroy({
      where: {
        class_code: req.params.class_code,
        uid: req.params.uid,
      },
    });

    await AssistantClassroom.create({
      class_code,
      uid,
    });

    res.status(200).json({
      success: true,
      message: "Update assistant in classroom successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Update assistant in classroom failed",
    });
  }
};

export const destroy = async (req, res) => {
  const classroom = await AssistantClassroom.findOne({
    where: {
      class_code: req.params.class_code,
      uid: req.params.uid,
    },
  });

  if (!classroom) {
    return res.status(404).json({
      success: false,
      message:
        "Delete assistant in classroom failed, User or Classroom not found",
    });
  }

  try {
    await AssistantClassroom.destroy({
      where: {
        class_code: req.params.class_code,
        uid: req.params.uid,
      },
    });

    res.status(200).json({
      success: true,
      message: "Delete assistant in classroom successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Delete assistant in classroom failed",
    });
  }
};
