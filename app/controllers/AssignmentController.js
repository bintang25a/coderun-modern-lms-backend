import { Assignment } from "../../database/models/Model.js";
import fs from "fs";
import path from "path";

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
        },
        {
          association: Assignment.associations.submission,
          as: "submission",
          attributes: ["student_uid"],
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
        },
        {
          association: Assignment.associations.submission,
          as: "submission",
          attributes: ["student_uid"],
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
  const { assistant_uid, title, description, startAt, endAt, overtime } =
    req.body;

  if (
    !assistant_uid ||
    !title ||
    !description ||
    !startAt ||
    !endAt ||
    !overtime
  ) {
    return res.status(400).json({
      success: false,
      message: "Create assignment failed, Field cannot empty",
    });
  }

  const startTime = new Date(startAt);
  const endTime = new Date(endAt);

  if (startTime > endTime) {
    return res.status(400).json({
      success: false,
      message: "Create assignment failed, Time over deadline",
    });
  }

  const assignment_number = req.assignment_number;
  const class_code = req.params.class_code;

  let answer_key = null;

  if (req.file) {
    const answerPath = path.join(
      "public/classrooms",
      assignment_number,
      req.file.filename
    );

    answer_key = answerPath;
  }

  try {
    await Assignment.create({
      assignment_number,
      class_code,
      assistant_uid,
      title,
      description,
      answer_key,
      startAt,
      endAt,
      overtime,
    });

    res.status(201).json({
      success: true,
      message: "Create assignment successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Create assignment failed",
    });
  }
};

export const update = async (req, res) => {
  const { assistant_uid, title, description, startAt, endAt, overtime } =
    req.body;

  const assignment = await Assignment.findOne({
    where: {
      assignment_number: req.params.assignment_number,
    },
  });

  if (!assignment) {
    return res.status(404).json({
      success: false,
      message: "Update assignment failed, Assignment not found",
    });
  }

  if (
    !assistant_uid ||
    !title ||
    !description ||
    !startAt ||
    !endAt ||
    !overtime
  ) {
    return res.status(400).json({
      success: false,
      message: "Update assignment failed, Field cannot empty",
    });
  }

  try {
    await Assignment.update(
      {
        assistant_uid,
        title,
        description,
        startAt,
        endAt,
        overtime,
      },
      {
        where: {
          assignment_number: req.params.assignment_number,
        },
      }
    );

    res.status(200).json({
      success: true,
      message: "Update assignment successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Update assignment failed",
    });
  }
};

export const destroy = async (req, res) => {
  const assignment = await Assignment.findOne({
    where: {
      assignment_number: req.params.assignment_number,
    },
  });

  if (!assignment) {
    return res.status(404).json({
      success: false,
      message: "Delete assignment failed, Assignment not found",
    });
  }

  try {
    const assignmentPath = path.join(
      "public/classrooms",
      req.params.assignment_number
    );
    if (fs.existsSync(assignmentPath)) {
      fs.rmSync(assignmentPath, { recursive: true, force: true });
    }

    await Assignment.destroy({
      where: {
        assignment_number: req.params.assignment_number,
      },
    });

    res.status(200).json({
      success: true,
      message: "Delete assignment successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Delete assignment failed",
    });
  }
};
