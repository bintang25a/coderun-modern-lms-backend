import { Assignment } from "../models/index.js";

export const index = async (req, res) => {
  try {
    const assignments = await Assignment.findAll({
      where: {
        class_code: req.params.class_code,
      },
      include: [
        {
          association: Assignment.associations.classroom,
          as: "classroom",
          attributes: ["name"],
          through: {
            attributes: [],
          },
        },
      ],
    });

    res.status(200).json({
      success: true,
      message: "Display all assignments successfully",
      data: assignments,
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Display all assignments failed",
    });
  }
};

export const show = async (req, res) => {
  try {
    const assignment = await Assignment.findOne({
      where: {
        assignment_number: req.params.assignment_number,
      },
      include: [
        {
          association: Assignment.associations.classroom,
          as: "classroom",
          attributes: ["name"],
          through: {
            attributes: [],
          },
        },
      ],
    });

    if (!assignment) {
      return res.status(404).json({
        success: false,
        message: "Display assignment failed, Assignment not found",
      });
    }

    res.status(200).json({
      success: true,
      message: "Display assignment successfully",
      data: assignment,
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Display assignment failed",
    });
  }
};

export const store = async (req, res) => {
  const { title, description, class_code, asisten_uid, answer_key } = req.body;

  if ((!title, !description, !class_code, !asisten_uid, !answer_key)) {
    return res.status(400).json({
      success: false,
      message: "Create assignment failed, Field cannot empty",
    });
  }

  try {
    await Assignment.create({
      assignment_number,
      title,
      description,
      class_code,
      asisten_uid,
      answer_key,
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
  const { name, uid_asisten1, uid_asisten2 } = req.body;

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
        uid_asisten1,
        uid_asisten2,
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
