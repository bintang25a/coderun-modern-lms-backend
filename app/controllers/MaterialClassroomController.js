import {
  MaterialClassroom,
  Material,
  Classroom,
} from "../../database/models/Model.js";

export const index = async (req, res) => {
  try {
    const materials = await MaterialClassroom.findAll();

    res.status(200).json({
      success: true,
      message: "Display all materials in classrooms successfully",
      data: materials,
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Display all materials in classrooms failed",
    });
  }
};

export const store = async (req, res) => {
  const { class_code, material_number } = req?.params;

  if (!class_code || !material_number) {
    return res.status(400).json({
      success: false,
      message: "Create material in classroom failed, Params cannot empty",
    });
  }

  const noMaterial = await MaterialClassroom.findOne({
    where: {
      class_code,
      material_number,
    },
  });

  if (noMaterial) {
    return res.status(400).json({
      success: false,
      message:
        "Create material in classroom failed, Material already in classroom",
    });
  }

  const material = await Material.findOne({
    where: {
      material_number,
    },
  });

  const classroom = await Classroom.findOne({
    where: {
      class_code,
    },
  });

  if (!material || !classroom) {
    return res.status(404).json({
      success: false,
      message:
        "Create material in classroom failed, Material Number or Class code not found",
    });
  }

  try {
    await MaterialClassroom.create({
      class_code,
      material_number,
    });

    res.status(201).json({
      success: true,
      message: "Create material in classroom successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Create material in classroom failed",
    });
  }
};

export const destroy = async (req, res) => {
  const { class_code, material_number } = req?.params;

  if (!class_code || !material_number) {
    return res.status(400).json({
      success: false,
      message: "Delete material in classroom failed, Params cannot empty",
    });
  }

  const classroom = await MaterialClassroom.findOne({
    where: {
      class_code,
      material_number,
    },
  });

  if (!classroom) {
    return res.status(404).json({
      success: false,
      message:
        "Delete material in classroom failed, Material or Classroom not found",
    });
  }

  try {
    await MaterialClassroom.destroy({
      where: {
        class_code,
        material_number,
      },
    });

    res.status(200).json({
      success: true,
      message: "Delete material in classroom successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Delete material in classroom failed",
    });
  }
};
