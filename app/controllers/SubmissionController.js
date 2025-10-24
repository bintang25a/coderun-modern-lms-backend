import { Submission } from "../../database/models/index.js";
import fs from "fs";
import path from "path";

export const index = async (req, res) => {
  try {
    const submissions = await Submission.findAll({
      where: {
        assignment_number: req.params.assignment_number,
      },
      // include: [
      //   {
      //     association: Submission.associations.classroom,
      //     as: "classroom",
      //     attributes: ["name"],
      //   },
      // ],
    });

    res.status(200).json({
      success: true,
      message: "Display all submissions successfully",
      data: submissions,
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Display all submissions failed",
    });
  }
};

export const show = async (req, res) => {
  try {
    const submission = await Submission.findOne({
      where: {
        submission_number: req.params.submission_number,
      },
      // include: [
      //   {
      //     association: Submission.associations.classroom,
      //     as: "classroom",
      //     attributes: ["name"],
      //   },
      // ],
    });

    if (!submission) {
      return res.status(404).json({
        success: false,
        message: "Display submission failed, Submission not found",
      });
    }

    res.status(200).json({
      success: true,
      message: "Display submission successfully",
      data: submission,
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Display submission failed",
    });
  }
};

export const store = async (req, res) => {
  const { student_uid, class_code } = req.body;

  if (!student_uid || !class_code || !req.file) {
    return res.status(400).json({
      success: false,
      message: "Create submission failed, Field must not empty",
    });
  }

  const { assignment_number } = req.params;
  const answerPath = path.join(
    "public/classrooms",
    class_code,
    assignment_number,
    req.file.filename
  );

  const answer = answerPath;

  try {
    await Submission.create({
      submission_number: req.submission_number,
      assignment_number,
      student_uid,
      answer,
    });

    res.status(201).json({
      success: true,
      message: "Create submission successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Create submission failed",
    });
  }
};

export const update = async (req, res) => {
  const { student_uid, class_code } = req.body;

  const submission = await Submission.findOne({
    where: {
      submission_number: req.params.submission_number,
    },
  });

  if (!submission) {
    return res.status(404).json({
      success: false,
      message: "Update submission failed, Submission not found",
    });
  }

  if (!student_uid || !class_code) {
    return res.status(400).json({
      success: false,
      message: "Update submission failed, Field must not empty",
    });
  }

  try {
    let answer = submission.answer;

    if (req.file) {
      if (fs.existsSync(answer)) {
        fs.unlinkSync(answer);
      }

      const { assignment_number } = req.params;
      const publicPath = path.resolve("public");
      answer = path.join(
        publicPath,
        "classrooms",
        class_code,
        assignment_number,
        req.file.filename
      );
    }

    await Submission.update(
      {
        student_uid,
        answer,
      },
      {
        where: {
          assignment_number: req.params.assignment_number,
        },
      }
    );

    res.status(200).json({
      success: true,
      message: "Update submission successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Update submission failed",
    });
  }
};

export const destroy = async (req, res) => {
  const submission = await Submission.findOne({
    where: {
      submission_number: req.params.submission_number,
    },
  });

  if (!submission) {
    return res.status(404).json({
      success: false,
      message: "Delete submission failed, Submission not found",
    });
  }

  try {
    const answer = submission.answer;

    if (fs.existsSync(answer)) {
      fs.unlinkSync(answer);
    }

    await Submission.destroy({
      where: {
        submission_number: req.params.submission_number,
      },
    });

    res.status(200).json({
      success: true,
      message: "Delete submission successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Delete submission failed",
    });
  }
};
