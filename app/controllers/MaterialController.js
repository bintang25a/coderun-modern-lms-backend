import { Material } from "../../database/models/Model.js";
import { fileURLToPath } from "url";
import path from "path";
import fs from "fs";

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

export const index = async (req, res) => {
  const filters = req.query;
  const whereClause = {};

  Object.entries(filters).forEach(([key, value]) => {
    if (value) {
      whereClause[key] = value;
    }
  });

  try {
    const materials = await Material.findAll({
      where: whereClause,
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
  const { material_number } = req?.params;

  if (!material_number) {
    return res.status(400).json({
      success: false,
      message: "Show material failed, Params cannot empty",
    });
  }

  try {
    const material = await Material.findByPk(material_number, {
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

export const file = async (req, res) => {
  const { material_number } = req.params;

  if (!material_number) {
    return res.status(400).json({
      success: false,
      message: "Show material failed, Params cannot empty",
    });
  }

  try {
    const material = await Material.findByPk(material_number, {});

    if (!material) {
      return res.status(404).json({
        success: false,
        message: "Show material failed, Material not found",
      });
    }

    const filePath = path
      .join(__dirname, "../../", material.material)
      .replace(/\\/g, "/");

    if (!fs.existsSync(filePath)) {
      return res.status(404).json({
        success: false,
        message: "Show material failed, File not found",
      });
    }

    // HEADER untuk blob inline
    res.setHeader("Content-Type", "application/pdf");
    res.setHeader("Content-Disposition", "inline");
    res.setHeader("Content-Length", fs.statSync(filePath).size);

    // STREAM PDF
    const stream = fs.createReadStream(filePath);
    stream.pipe(res);
  } catch (error) {
    console.error(error);
    res.status(500).json({
      success: false,
      message: "Failed to stream material",
    });
  }
};

export const store = async (req, res) => {
  const { title } = req?.body;

  if (!title || !req.file) {
    return res.status(400).json({
      success: false,
      message: "Create material failed, Field cannot empty",
    });
  }

  if (!req.uid) {
    return res.status(400).json({
      success: false,
      message: "Create material failed, User unknown",
    });
  }

  const material_number = req.material_number;
  const materialPath = path.join("public/materials", req.file.filename);

  try {
    await Material.create({
      material_number,
      assistant_uid: req.uid,
      title,
      material: materialPath,
    });

    res.status(201).json({
      success: true,
      message: "Create material successfully",
      data: material_number,
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
  const { material_number } = req?.params;
  const { title } = req?.body;

  if (!material_number) {
    return res.status(400).json({
      success: false,
      message: "Update material failed, Params cannot empty",
    });
  }

  const material = await Material.findByPk(material_number, {});

  if (!material) {
    return res.status(404).json({
      success: false,
      message: "Update material failed, Material not found",
    });
  }

  if (!title || !material_number) {
    return res.status(400).json({
      success: false,
      message: "Create material failed, Field cannot empty",
    });
  }

  if (!req.uid) {
    return res.status(400).json({
      success: false,
      message: "Update material failed, User unknown",
    });
  }

  try {
    await Material.update(
      {
        assistant_uid: req?.uid,
        title,
      },
      {
        where: {
          material_number,
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
  const { material_number } = req?.params;

  if (!material_number) {
    return res.status(400).json({
      success: false,
      message: "Delete material failed, Params cannot empty",
    });
  }

  const material = await Material.findByPk({});

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
        material_number,
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
