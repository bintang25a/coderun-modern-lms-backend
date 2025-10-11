import User from "../models/UserModel.js";
import bcrypt from "bcrypt";

import Classroom from "../models/ClassroomModel.js";

export const index = async (req, res) => {
  try {
    const users = await User.findAll({
      attributes: {
        exclude: ["password"],
      },
      include: [
        {
          model: Classroom,
          as: "classroom",
          attributes: ["name"],
        },
      ],
    });

    res.status(200).json(users);
  } catch (error) {
    console.log(error.message);
    res.status(500).json({ msg: "Display all users failed" });
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
          model: Classroom,
          as: "classroom",
          attributes: ["name"],
        },
      ],
    });

    if (!user) {
      return res.status(404).json({ msg: "User not found" });
    }

    res.status(200).json(user);
  } catch (error) {
    console.log(error.message);
    res.status(500).json({ msg: "Display one user failed" });
  }
};

export const store = async (req, res) => {
  const { uid, name, phone_number, email, role, password, class_code } =
    req.body;

  if (!uid || !name || !phone_number || !email || !role || !password) {
    return res.status(400).json({ msg: "Field cannot empty" });
  }

  if (role !== "Praktikan" && role !== "Asisten" && role !== "Admin") {
    return res.status(400).json({ msg: "Invalid role option" });
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
    });

    res.status(201).json({ msg: "Create user successfully" });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({ msg: "Create user failed" });
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
    return res.status(404).json({ msg: "User not found" });
  }

  if (!name || !phone_number || !email || !role) {
    return res.status(400).json({ msg: "Field cannot empty" });
  }

  if (role !== "Praktikan" && role !== "Asisten" && role !== "Admin") {
    return res.status(400).json({ msg: "Invalid role option" });
  }

  let hashPassword;
  if (!password) {
    hashPassword = user.password;
  } else {
    hashPassword = await bcrypt.hash(password, 10);
  }

  try {
    await user.update(
      {
        name,
        phone_number,
        email,
        role,
        password: hashPassword,
        class_code,
      },
      {
        where: {
          uid: req.params.uid,
        },
      }
    );

    res.status(200).json({ msg: "Update user successfully" });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({ msg: "Update user failed" });
  }
};

export const destroy = async (req, res) => {
  const user = await User.findOne({
    where: {
      uid: req.params.uid,
    },
  });

  if (!user) {
    return res.status(404).json({ msg: "User not found" });
  }

  try {
    await User.destroy({
      where: {
        uid: req.params.uid,
      },
    });

    res.status(200).json({ msg: "Delete user successfully" });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({ msg: "Delete user failed" });
  }
};
