import { Assignment } from "../models/index.js";
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
  const { title, description, class_code, assistant_uid } = req.body;

  if ((!title, !description, !class_code, !assistant_uid)) {
    return res.status(400).json({
      success: false,
      message: "Create assignment failed, Field cannot empty",
    });
  }

  const count = await Assignment.count({
    where: {
      class_code,
    },
  });

  const assignment_number = req.assignment_number;
  const answer_key = req.file ? req.file.filename : null;

  try {
    await Assignment.create({
      assignment_number,
      title,
      description,
      class_code,
      assistant_uid,
      answer_key,
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
  const { title, description, class_code, assistant_uid } = req.body;

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

  if ((!title, !description, !class_code, !assistant_uid)) {
    return res.status(400).json({
      success: false,
      message: "Update assignment failed, Field cannot empty",
    });
  }

  try {
    let answer_key = assignment.answer_key;
    if (req.file) {
      answer_key = req.file.filename;

      if (assignment.answer_key) {
        const oldFilePath = path.join(
          "src/classrooms",
          class_code,
          assignment.assignment_number,
          assignment.answer_key
        );

        if (fs.existsSync(oldFilePath)) {
          fs.unlinkSync(oldFilePath);
        }
      }
    }

    await Assignment.update(
      {
        title,
        description,
        class_code,
        assistant_uid,
        answer_key,
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
    if (assignment.answer_key) {
      const oldFilePath = path.join(
        "src/classrooms",
        class_code,
        assignment.assignment_number,
        assignment.answer_key
      );

      if (fs.existsSync(oldFilePath)) {
        fs.unlinkSync(oldFilePath);
      }
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
