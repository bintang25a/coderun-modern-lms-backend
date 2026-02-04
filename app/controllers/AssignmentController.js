import { Assignment } from "../../database/models/Model.js";
import fs from "fs";
import path from "path";

export const index = async (req, res) => {
  const whereClause = {};
  const { class_code } = req.params;

  if (class_code || class_code !== "admin") {
    whereClause.class_code = class_code;
  }

  try {
    const assignments = await Assignment.findAll({
      whereClause,
      include: [
        {
          association: Assignment.associations.assistant,
          as: "assistant",
          attributes: ["name", "uid"],
        },
        {
          association: Assignment.associations.classroom,
          as: "classroom",
          attributes: ["class_code", "name"],
        },
        {
          association: Assignment.associations.submissions,
          as: "submissions",
          attributes: ["submission_number", "student_uid", "grade"],
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
  const whereClause = {};
  const { class_code, assignment_number } = req.params;

  if (class_code || class_code !== "admin") {
    whereClause.class_code = class_code;
  }

  if (assignment_number) {
    whereClause.assignment_number = assignment_number;
  }

  try {
    const assignment = await Assignment.findOne({
      whereClause,
      include: [
        {
          association: Assignment.associations.assistant,
          as: "assistant",
          attributes: ["name", "uid"],
        },
        {
          association: Assignment.associations.classroom,
          as: "classroom",
          attributes: ["class_code", "name"],
        },
        {
          association: Assignment.associations.submissions,
          as: "submissions",
          attributes: ["submission_number", "student_uid", "grade"],
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
  const { title, description, startAt, endAt, overtime } = req.body;

  if (!title || !description || !startAt || !endAt || !overtime) {
    return res.status(400).json({
      success: false,
      message: "Create assignment failed, Field cannot empty",
    });
  }

  if (!req.uid) {
    return res.status(400).json({
      success: false,
      message: "Create assignment failed, User unknown",
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
      "public/assignments",
      assignment_number,
      req.file.filename
    );

    answer_key = answerPath;
  }

  try {
    await Assignment.create({
      assignment_number,
      class_code,
      assistant_uid: req.uid,
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
  const { title, description, startAt, endAt, overtime } = req.body;

  const assignment = await Assignment.findOne({
    where: {
      assignment_number: req.params.assignment_number,
      class_code: req.params.class_code,
    },
  });

  if (!assignment) {
    return res.status(404).json({
      success: false,
      message: "Update assignment failed, Assignment not found",
    });
  }

  if (!title || !description || !startAt || !endAt || !overtime) {
    return res.status(400).json({
      success: false,
      message: "Update assignment failed, Field cannot empty",
    });
  }

  if (!req.uid) {
    return res.status(400).json({
      success: false,
      message: "Update assignment failed, User unknown",
    });
  }

  try {
    const assignment_number = assignment.assignment_number;
    let answer_key = null;

    if (req.file) {
      const answerPath = path.join(
        "public/assignments",
        assignment_number,
        req.file.filename
      );

      answer_key = answerPath;
    }

    await Assignment.update(
      {
        assistant_uid: req.uid,
        title,
        description,
        startAt,
        endAt,
        overtime,
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
      class_code: req.params.class_code,
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
      "public/assignments",
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
