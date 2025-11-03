import { Material } from "../../database/models/Model.js";
import fs from "fs";
import path from "path";

export const index = async (req, res) => {
  try {
    const materials = await Material.findAll({
      include: [
        {
          association: Material.associations.classrooms,
          as: "classrooms",
          attributes: ["class_code", "name"],
          through: {
            attributes: [],
          },
        },
      ],
    });

    res.status(200).json({
      success: true,
      message: "Display all materials successfully",
      data: materials,
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Display all materials failed",
    });
  }
};

export const show = async (req, res) => {
  try {
    const material = await Material.findOne({
      where: {
        material_number: req.params.material_number,
      },
      include: [
        {
          association: Material.associations.classrooms,
          as: "classrooms",
          attributes: ["class_code", "name"],
          through: {
            attributes: [],
          },
        },
      ],
    });

    if (!material) {
      return res.status(404).json({
        success: false,
        message: "Display material failed, Material not found",
      });
    }

    res.status(200).json({
      success: true,
      message: "Display material successfully",
      data: material,
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Display material failed",
    });
  }
};

export const store = async (req, res) => {
  const { assistant_uid, title } = req.body;

  if (!assistant_uid || !title || !req.file) {
    return res.status(400).json({
      success: false,
      message: "Create material failed, Field cannot empty",
    });
  }

  const material_number = req.material_number;
  const materialPath = path.join("public/materials", req.file.filename);

  try {
    await Material.create({
      material_number,
      assistant_uid,
      title,
      material: materialPath,
    });

    res.status(201).json({
      success: true,
      message: "Create material successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Create material failed",
    });
  }
};

export const update = async (req, res) => {
  const { assistant_uid, title } = req.body;

  const material = await Material.findOne({
    where: {
      material_number: req.params.material_number,
    },
  });

  if (!material) {
    return res.status(404).json({
      success: false,
      message: "Update material failed, Material not found",
    });
  }

  if (!assistant_uid || !title) {
    return res.status(400).json({
      success: false,
      message: "Create material failed, Field cannot empty",
    });
  }

  try {
    await Material.update(
      {
        assistant_uid,
        title,
      },
      {
        where: {
          material_number: req.params.material_number,
        },
      }
    );

    res.status(200).json({
      success: true,
      message: "Update material successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Update material failed",
    });
  }
};

export const destroy = async (req, res) => {
  const material = await Material.findOne({
    where: {
      material_number: req.params.material_number,
    },
  });

  if (!material) {
    return res.status(404).json({
      success: false,
      message: "Delete material failed, Material not found",
    });
  }

  try {
    const materialPath = material.material;
    if (fs.existsSync(materialPath)) {
      fs.rmSync(materialPath, { recursive: true, force: true });
    }

    await Material.destroy({
      where: {
        material_number: req.params.material_number,
      },
    });

    res.status(200).json({
      success: true,
      message: "Delete material successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Delete material failed",
    });
  }
};
