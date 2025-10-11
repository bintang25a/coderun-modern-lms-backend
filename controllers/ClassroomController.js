import Classroom from "../models/ClassroomModel.js";
import User from "../models/UserModel.js";

export const index = async (req, res) => {
  try {
    const classrooms = await Classroom.findAll({
      include: [
        {
          model: User,
          as: "praktikan",
          attributes: ["uid", "name", "email"],
        },
        {
          model: User,
          as: "tutor",
          attributes: ["uid", "name", "email"],
        },
        {
          model: User,
          as: "asisten",
          attributes: ["uid", "name", "email"],
        },
      ],
    });

    res.status(200).json(classrooms);
  } catch (error) {
    console.log(error.message);
    res.status(500).json({ msg: "Display all classrooms failed" });
  }
};

export const show = async (req, res) => {
  try {
    const classroom = await Classroom.findOne({
      where: {
        class_code: req.params.class_code,
      },
      include: [
        {
          model: User,
          as: "praktikan",
          attributes: ["uid", "name", "email"],
        },
        {
          model: User,
          as: "tutor",
          attributes: ["uid", "name", "email"],
        },
        {
          model: User,
          as: "asisten",
          attributes: ["uid", "name", "email"],
        },
      ],
    });

    if (!classroom) {
      return res.status(404).json({ msg: "Classroom not found" });
    }

    res.status(200).json(classroom);
  } catch (error) {
    console.log(error.message);
    res.status(500).json({ msg: "Display one classroom failed" });
  }
};

export const store = async (req, res) => {
  const { class_code, name, uid_asisten1, uid_asisten2 } = req.body;

  if (!class_code || !name) {
    return res.status(400).json({ msg: "Field cannot empty" });
  }

  const classroom = await Classroom.findOne({
    where: {
      class_code: class_code,
    },
  });

  if (classroom) {
    return res.status(400).json({ msg: "Classroom code already exist" });
  }

  try {
    await Classroom.create({
      class_code,
      name,
      uid_asisten1,
      uid_asisten2,
    });

    res.status(201).json({ msg: "Create classroom successfully" });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({ msg: "Create classroom failed" });
  }
};

export const update = async (req, res) => {
  const { name, uid_asisten1, uid_asisten2 } = req.body;

  const classroom = await Classroom.findOne({
    where: {
      class_code: req.params.class_code,
    },
  });

  if (!classroom) {
    return res.status(404).json({ msg: "Classroom not found" });
  }

  if (!name) {
    return res.status(400).json({ msg: "Field cannot empty" });
  }

  try {
    await classroom.update(
      {
        name,
        uid_asisten1,
        uid_asisten2,
      },
      {
        where: {
          class_code: req.params.class_code,
        },
      }
    );

    res.status(200).json({ msg: "Update classroom successfully" });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({ msg: "Update classroom failed" });
  }
};

export const destroy = async (req, res) => {
  const classroom = await Classroom.findOne({
    where: {
      class_code: req.params.class_code,
    },
  });

  if (!classroom) {
    return res.status(404).json({ msg: "Classroom not found" });
  }

  try {
    await Classroom.destroy({
      where: {
        class_code: req.params.class_code,
      },
    });

    res.status(200).json({ msg: "Delete classroom successfully" });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({ msg: "Delete classroom failed" });
  }
};

export const addUser = async (req, res) => {
  const classroom = await Classroom.findOne({
    where: {
      class_code: req.params.class_code,
    },
  });
};
