import { UserClassroom, User, Classroom } from "../../database/models/index.js";

export const index = async (req, res) => {
  try {
    const classrooms = await UserClassroom.findAll();

    res.status(200).json({
      success: true,
      message: "Display all users in classrooms successfully",
      data: classrooms,
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Display all users in classrooms failed",
    });
  }
};

export const store = async (req, res) => {
  const { class_code, uid } = req.body;

  if (!class_code || !uid) {
    return res.status(400).json({
      success: false,
      message: "Create user in classroom failed, Field cannot empty",
    });
  }

  const noUser = await UserClassroom.findOne({
    where: {
      class_code,
      uid,
    },
  });

  if (noUser) {
    return res.status(400).json({
      success: false,
      message: "Create user in classroom failed, User already in classroom",
    });
  }

  const user = await UserClassroom.associations.findOne({
    where: {
      uid,
    },
  });

  const classroom = await Classroom.findOne({
    where: {
      class_code,
    },
  });

  if (!user || !classroom) {
    return res.status(404).json({
      success: false,
      message: "Create user in classroom failed, UID or Class code not found",
    });
  }

  try {
    await UserClassroom.create({
      class_code,
      uid,
    });

    res.status(201).json({
      success: true,
      message: "Create user in classroom successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Create user in classroom failed",
    });
  }
};

export const update = async (req, res) => {
  const { class_code, uid } = req.body;

  const userClassroom = await UserClassroom.findOne({
    where: {
      class_code: req.params.class_code,
      uid: req.params.uid,
    },
  });

  if (!userClassroom) {
    return res.status(404).json({
      success: false,
      message: "Update user in classroom failed, User or Classroom not found",
    });
  }

  if (!class_code || !uid) {
    return res.status(400).json({
      success: false,
      message: "Update user in classroom failed, Field cannot empty",
    });
  }

  const noUser = await UserClassroom.findOne({
    where: {
      class_code,
      uid,
    },
  });

  if (noUser) {
    return res.status(400).json({
      success: false,
      message: "Update user in classroom failed, User already in classroom",
    });
  }

  const user = await User.findOne({
    where: {
      uid,
    },
  });

  const classroom = await Classroom.findOne({
    where: {
      class_code,
    },
  });

  if (!user || !classroom) {
    return res.status(404).json({
      success: false,
      message: "Update user in classroom failed, UID or Class code not found",
    });
  }

  try {
    await userClassroom.destroy({
      where: {
        class_code: req.params.class_code,
        uid: req.params.uid,
      },
    });

    await UserClassroom.create({
      class_code,
      uid,
    });

    res.status(200).json({
      success: true,
      message: "Update user in classroom successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Update user in classroom failed",
    });
  }
};

export const destroy = async (req, res) => {
  const classroom = await UserClassroom.findOne({
    where: {
      class_code: req.params.class_code,
      uid: req.params.uid,
    },
  });

  if (!classroom) {
    return res.status(404).json({
      success: false,
      message: "Delete user in classroom failed, User or Classroom not found",
    });
  }

  try {
    await UserClassroom.destroy({
      where: {
        class_code: req.params.class_code,
        uid: req.params.uid,
      },
    });

    res.status(200).json({
      success: true,
      message: "Delete user in classroom successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Delete user in classroom failed",
    });
  }
};
