import { User } from "../models/index.js";
import bcrypt from "bcrypt";
import fs from "fs";
import path from "path";

export const index = async (req, res) => {
  try {
    const users = await User.findAll({
      attributes: {
        exclude: ["password"],
      },
      include: [
        {
          association: User.associations.classroom,
          as: "classroom",
          attributes: ["name"],
          through: {
            attributes: [],
          },
        },
      ],
    });

    res.status(200).json({
      success: true,
      message: "Display all users successfully",
      data: users,
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Display all users failed",
    });
  }
};

export const show = async (req, res) => {
  try {
    const user = await User.findOne({
      where: {
        uid: req.params.uid,
      },
      attributes: {
        exclude: ["password"],
      },
      include: [
        {
          association: User.associations.classroom,
          as: "classroom",
          attributes: ["name"],
          through: {
            attributes: [],
          },
        },
      ],
    });

    if (!user) {
      return res.status(404).json({
        success: false,
        message: "Display user failed, User not found",
      });
    }

    res.status(200).json({
      success: true,
      message: "Display user successfully",
      data: user,
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Display user failed",
    });
  }
};

export const store = async (req, res) => {
  const { uid, name, phone_number, email, role, password, class_code } =
    req.body;

  if (!uid || !name || !phone_number || !email || !role || !password) {
    return res.status(400).json({
      success: false,
      message: "Create user failed, Field cannot empty",
    });
  }

  const photo = req.file ? req.file.filename : null;

  if (role !== "Praktikan" && role !== "Asisten" && role !== "Admin") {
    return res.status(400).json({
      success: false,
      message: "Create user failed, Invalid role option",
    });
  }

  const hashPassword = await bcrypt.hash(password, 10);

  try {
    await User.create({
      uid,
      name,
      phone_number,
      email,
      role,
      password: hashPassword,
      class_code,
      photo,
    });

    res.status(201).json({
      success: true,
      message: "Create user successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Create user failed",
    });
  }
};

export const update = async (req, res) => {
  const { name, phone_number, email, role, password, class_code } = req.body;

  const user = await User.findOne({
    where: {
      uid: req.params.uid,
    },
  });

  if (!user) {
    return res.status(404).json({
      success: false,
      message: "Update user failed, User not found",
    });
  }

  if (!name || !phone_number || !email || !role) {
    return res.status(400).json({
      success: true,
      message: "Update user failed, Field cannot empty",
    });
  }

  if (role !== "Praktikan" && role !== "Asisten" && role !== "Admin") {
    return res.status(400).json({
      success: false,
      message: "Update user failed, Invalid role option",
    });
  }

  let hashPassword;
  if (!password) {
    hashPassword = user.password;
  } else {
    hashPassword = await bcrypt.hash(password, 10);
  }

  try {
    let photo = user.photo;
    if (req.file) {
      photo = req.file.filename;

      if (user.photo) {
        const oldFilePath = path.join("src/profiles", user.photo);

        if (fs.existsSync(oldFilePath)) {
          fs.unlinkSync(oldFilePath);
        }
      }
    }

    await user.update(
      {
        name,
        phone_number,
        email,
        role,
        password: hashPassword,
        class_code,
        photo,
      },
      {
        where: {
          uid: req.params.uid,
        },
      }
    );

    res.status(200).json({
      success: true,
      message: "Update user successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Update user failed",
    });
  }
};

export const destroy = async (req, res) => {
  const user = await User.findOne({
    where: {
      uid: req.params.uid,
    },
  });

  if (!user) {
    return res.status(404).json({
      success: true,
      message: "Delete user failed, User not found",
    });
  }

  try {
    if (user.photo) {
      const oldFilePath = path.join("src/profiles", user.photo);

      if (fs.existsSync(oldFilePath)) {
        fs.unlinkSync(oldFilePath);
      }
    }

    await User.destroy({
      where: {
        uid: req.params.uid,
      },
    });

    res.status(200).json({
      success: true,
      message: "Delete user successfully",
    });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      message: "Delete user failed",
    });
  }
};
