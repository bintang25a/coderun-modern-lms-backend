import { Classroom } from "../../database/models/Model.js";

export const index = async (req, res) => {
  try {
    const classrooms = await Classroom.findAll({
      include: [
        {
          association: Classroom.associations.assistants,
          as: "assistants",
          through: {
            attributes: [],
          },
          include: ["uid", "name"],
        },
        {
          association: Classroom.associations.students,
          as: "students",
          through: {
            attributes: [],
          },
          include: ["uid", "name"],
        },
        {
          association: Classroom.associations.materials,
          as: "materials",
          through: {
            attributes: [],
          },
        },
        {
          association: Classroom.associations.assignments,
          as: "assignments",
          include: [
            {
              association: "assistant",
              include: ["uid", "name"],
            },
            {
              association: "testcases",
            },
            {
              association: "submissions",
            },
          ],
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
  const { class_code } = req?.params;
  try {
    const classroom = await Classroom.findOne({
      where: {
        class_code,
      },
      include: [
        {
          association: Classroom.associations.assistants,
          as: "assistants",
          through: {
            attributes: [],
          },
          exclude: ["password"],
        },
        {
          association: Classroom.associations.students,
          as: "students",
          through: {
            attributes: [],
          },
          exclude: ["password"],
        },
        {
          association: Classroom.associations.materials,
          as: "materials",
          through: {
            attributes: [],
          },
        },
        {
          association: Classroom.associations.assignments,
          as: "assignments",
          include: [
            {
              association: "assistant",
              exclude: ["password"],
            },
            {
              association: "testcases",
            },
            {
              association: "submissions",
            },
          ],
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
  const { class_code, name } = req?.body;

  if (!class_code || !name) {
    return res.status(400).json({
      success: false,
      message: "Create classroom failed, Field cannot empty",
    });
  }

  const classroom = await Classroom.findOne({
    where: {
      class_code,
    },
  });

  if (classroom) {
    return res.status(400).json({
      success: false,
      message: "Create classroom failed, Classroom code already exist",
    });
  }

  try {
    await Classroom.create({
      class_code,
      name,
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
  const { class_code } = req?.params;
  const { name } = req?.body;

  const classroom = await Classroom.findOne({
    where: {
      class_code,
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
      },
      {
        where: {
          class_code,
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
  const { class_code } = req?.params;

  if (!class_code) {
    return res.status(400).json({
      success: false,
      message: "Delete classroom failed, Params cannot empty",
    });
  }

  const classroom = await Classroom.findOne({
    where: {
      class_code,
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
        class_code,
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
