import { Classroom } from "../../database/models/index.js";
import fs from "fs";
import path from "path";

export const index = async (req, res) => {
  try {
    const classrooms = await Classroom.findAll({
      include: [
        {
          association: Classroom.associations.tutor,
          as: "tutor",
          attributes: ["uid", "name", "email"],
        },
        {
          association: Classroom.associations.assistant,
          as: "assistant",
          attributes: ["uid", "name", "email"],
        },
        {
          association: Classroom.associations.praktikan,
          as: "praktikan",
          attributes: ["uid", "name", "email"],
          through: {
            attributes: [],
          },
        },
        {
          association: Classroom.associations.assignments,
          as: "assignments",
          attributes: ["assignment_number", "title", "description"],
        },
      ],
    });

    res.status(200).json({
      success: true,
      message: "Display all classrooms successfully",
      data: classrooms,
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Display all classrooms failed",
    });
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
          association: Classroom.associations.tutor,
          as: "tutor",
          attributes: ["uid", "name", "email"],
        },
        {
          association: Classroom.associations.assistant,
          as: "assistant",
          attributes: ["uid", "name", "email"],
        },
        {
          association: Classroom.associations.praktikan,
          as: "praktikan",
          attributes: ["uid", "name", "email"],
          through: {
            attributes: [],
          },
        },
        {
          association: Classroom.associations.assignments,
          as: "assignments",
          attributes: ["assignment_number", "title", "description"],
        },
      ],
    });

    if (!classroom) {
      return res.status(404).json({
        success: false,
        message: "Display classroom failed, Classroom not found",
      });
    }

    res.status(200).json({
      success: true,
      message: "Display classroom successfully",
      data: classroom,
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Display classroom failed",
    });
  }
};

export const store = async (req, res) => {
  const { class_code, name, assistant1_uid, assistant2_uid } = req.body;

  if (!class_code || !name) {
    return res.status(400).json({
      success: false,
      message: "Create classroom failed, Field cannot empty",
    });
  }

  const classroom = await Classroom.findOne({
    where: {
      class_code: class_code,
    },
  });

  if (classroom) {
    return res.status(400).json({
      success: false,
      message: "Create classroom failed, Classroom code already exist",
    });
  }

  try {
    const publicPath = path.resolve("public");
    const classroomPath = path.join(publicPath, "classrooms", class_code);
    if (!fs.existsSync(classroomPath)) {
      fs.mkdirSync(classroomPath, { recursive: true });
    }

    await Classroom.create({
      class_code,
      name,
      assistant1_uid,
      assistant2_uid,
    });

    res.status(201).json({
      success: true,
      message: "Create classroom successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Create classroom failed",
    });
  }
};

export const update = async (req, res) => {
  const { name, assistant1_uid, assistant2_uid } = req.body;

  const classroom = await Classroom.findOne({
    where: {
      class_code: req.params.class_code,
    },
  });

  if (!classroom) {
    return res.status(404).json({
      success: false,
      message: "Update classroom failed, Classroom not found",
    });
  }

  if (!name) {
    return res.status(400).json({
      success: false,
      message: "Update classroom failed, Field cannot empty",
    });
  }

  try {
    await classroom.update(
      {
        name,
        assistant1_uid,
        assistant2_uid,
      },
      {
        where: {
          class_code: req.params.class_code,
        },
      }
    );

    res.status(200).json({
      success: true,
      message: "Update classroom successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Update classroom failed",
    });
  }
};

export const destroy = async (req, res) => {
  const classroom = await Classroom.findOne({
    where: {
      class_code: req.params.class_code,
    },
  });

  if (!classroom) {
    return res.status(404).json({
      success: false,
      message: "Delete classroom failed, Classroom not found",
    });
  }

  try {
    const publicPath = path.resolve("public");
    const classroomPath = path.join(
      publicPath,
      "classrooms",
      classroom.class_code
    );
    if (fs.existsSync(classroomPath)) {
      fs.unlinkSync(classroomPath);
    }

    await Classroom.destroy({
      where: {
        class_code: req.params.class_code,
      },
    });

    res.status(200).json({
      success: true,
      message: "Delete classroom successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Delete classroom failed",
    });
  }
};
