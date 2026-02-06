import { Testcase } from "../../database/models/Model.js";
import fs from "fs";
import path from "path";

export const index = async (req, res) => {
  const whereClause = {};
  const { assignment_number } = req.params;

  if (assignment_number && assignment_number !== "admin") {
    whereClause.assignment_number = assignment_number;
  }

  try {
    const testcases = await Testcase.findAll({
      where: whereClause,
      include: [
        {
          association: Testcase.associations.assignment,
          as: "assignment",
          attributes: ["title"],
        },
      ],
    });

    res.status(200).json({
      success: true,
      message: "Display all testcases successfully",
      data: testcases,
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Display all testcases failed",
    });
  }
};

export const show = async (req, res) => {
  const whereClause = {};
  const { assignment_number, testcase_number } = req.params;

  if (assignment_number && assignment_number !== "admin") {
    whereClause.assignment_number = assignment_number;
  }

  if (testcase_number) {
    whereClause.testcase_number = testcase_number;
  }

  try {
    const testcase = await Testcase.findOne({
      where: whereClause,
      include: [
        {
          association: Testcase.associations.assignment,
          as: "assignment",
          attributes: ["title"],
        },
      ],
    });

    if (!testcase) {
      return res.status(404).json({
        success: false,
        message: "Display testcase failed, Testcase not found",
      });
    }

    res.status(200).json({
      success: true,
      message: "Display testcase successfully",
      data: testcase,
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Display testcase failed",
    });
  }
};

export const store = async (req, res) => {
  const { assignment_number } = req.params;
  const { name, weight, input } = req.body;

  if (!assignment_number || !name || !weight || !input) {
    return res.status(400).json({
      success: false,
      message: "Create testcase failde, Field cannot empty",
    });
  }

  try {
    await Testcase.create({
      assignment_number,
      name,
      weight: Number(weight),
      input,
    });

    res.status(201).json({
      success: true,
      message: "Create testcase successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Create testcase failed",
    });
  }
};

export const update = async (req, res) => {
  const { assignment_number, testcase_number } = req?.params;
  const { name, weight, input } = req?.body;

  if (!assignment_number || !testcase_number || !name || !weight || !input) {
    return res.status(400).json({
      success: false,
      message: "Update testcase failed, Field cannot empty",
    });
  }

  const testcase = await Testcase.findOne({
    where: {
      testcase_number,
      assignment_number,
    },
  });

  if (!testcase) {
    return res.status(404).json({
      success: false,
      message: "Update testcase failed, Testcase not found",
    });
  }

  try {
    await Submission.update(
      {
        name,
        weight,
        input,
      },
      {
        where: {
          testcase_number,
          assignment_number,
        },
      }
    );

    res.status(200).json({
      success: true,
      message: "Update testcase successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Update testcase failed",
    });
  }
};

export const destroy = async (req, res) => {
  const { assignment_number, testcase_number } = req?.params;

  if (!assignment_number || !testcase_number) {
    return res.status(400).json({
      success: false,
      message: "Delete testcase failed, Params cannot empty",
    });
  }

  const testcase = await Testcase.findOne({
    where: {
      testcase_number,
      assignment_number,
    },
  });

  if (!testcase) {
    return res.status(404).json({
      success: true,
      message: "Delete testcase failed, Testcase not found",
    });
  }

  try {
    await Testcase.destroy({
      where: {
        testcase_number,
        assignment_number,
      },
    });

    res.status(200).json({
      success: true,
      message: "Delete testcase successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Delete testcase failed",
    });
  }
};
