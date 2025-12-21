import { Submission } from "../../database/models/Model.js";
import fs from "fs";
import path from "path";

export const index = async (req, res) => {
  try {
    const submissions = await Submission.findAll({
      where: {
        assignment_number: req.params.assignment_number,
      },
      include: [
        {
          association: Submission.associations.student,
          as: "student",
          attributes: ["uid", "name", "email"],
        },
      ],
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
        assignment_number: req.params.assignment_number,
      },
      include: [
        {
          association: Submission.associations.student,
          as: "student",
          attributes: ["uid", "name", "email"],
        },
      ],
    });

    if (!submission) {
      return res.status(404).json({
        success: false,
        message: "Display submission failed, Submission not found",
      });
    }

    const code = fs.existsSync(submission.answer)
      ? fs.readFileSync(submission.answer, "utf-8")
      : null;

    res.status(200).json({
      success: true,
      message: "Display submission successfully",
      data: {
        ...submission.toJSON(),
        code,
      },
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
  if (!req.uid) {
    return res.status(400).json({
      success: false,
      message: "Create submission failed, Student UID unidentified",
    });
  }

  const { assignment_number } = req.params;
  const answerPath = path.join(
    "public/assignments",
    assignment_number,
    req.file.filename
  );

  const answer = answerPath;

  const submission = await Submission.findOne({
    where: {
      answer,
    },
  });

  if (submission) {
    return res.status(400).json({
      success: false,
      message: "Create submission failed, This user already submit",
    });
  }

  try {
    await Submission.create({
      submission_number: req.submission_number,
      assignment_number,
      student_uid: req.uid,
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

export const grade = async (req, res) => {
  const submission = await Submission.findOne({
    where: {
      submission_number: req.params.submission_number,
      assignment_number: req.params.assignment_number,
    },
  });

  if (!submission) {
    return res.status(404).json({
      success: false,
      message: "Grading submission failed, Submission not found",
    });
  }

  const { grade } = req.body;

  if (!grade) {
    return res.status(400).json({
      success: false,
      message: "Grading submission failed, Field cannot empty",
    });
  }

  if (!req.uid) {
    return res.status(400).json({
      success: false,
      message: "Grading submission failed, User unknown",
    });
  }

  try {
    await Submission.update(
      {
        grade,
        assistant_uid: req.uid,
      },
      {
        where: {
          submission_number: req.params.submission_number,
        },
      }
    );

    res.status(200).json({
      success: true,
      message: "Grading submission successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Grading submission failed",
    });
  }
};

export const update = async (req, res) => {
  const submission = await Submission.findOne({
    where: {
      submission_number: req.params.submission_number,
      assignment_number: req.params.assignment_number,
    },
  });

  if (!submission) {
    return res.status(404).json({
      success: false,
      message: "Update submission failed, Submission not found",
    });
  }

  try {
    let answer = submission.answer;

    if (req.file) {
      const { assignment_number } = req.params;
      answer = path.join(
        "public/assignments",
        assignment_number,
        req.file.filename
      );
    }

    await Submission.update(
      {
        answer,
      },
      {
        where: {
          submission_number: req.params.submission_number,
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
      assignment_number: req.params.assignment_number,
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
