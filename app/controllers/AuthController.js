import { User, Token } from "../../database/models/Model.js";
import bcrypt from "bcrypt";
import jwt from "jsonwebtoken";

export const login = async (req, res) => {
  const { uid, password, role } = req.body;

  if ((!uid, !password, !role)) {
    return res.status(400).json({
      success: false,
      message: "Login failed, Field cannot empty",
    });
  }

  const user = await User.findOne({
    where: {
      uid,
    },
  });

  if (!user) {
    return res.status(404).json({
      success: false,
      message: "Login failed, User not found",
    });
  }

  const allowedRoles = {
    Admin: ["Admin", "Asisten", "Praktikan"],
    Asisten: ["Asisten", "Praktikan"],
    Praktikan: ["Praktikan"],
  };

  if (!allowedRoles[user.role]?.includes(role)) {
    return res.status(403).json({
      success: false,
      message: `Login failed, ${user.role} cannot login as ${role}`,
    });
  }

  const match = await bcrypt.compare(password, user.password);

  if (!match) {
    return res.status(400).json({
      success: false,
      message: "Login failed, Wrong password!",
    });
  }

  const payload = {
    uid: user.uid,
    name: user.name,
    role: user.role,
  };

  const token = jwt.sign(payload, process.env.JWT_SECRET, {
    expiresIn: "1d",
  });

  const decoded = jwt.decode(token);
  const exp = decoded?.exp
    ? decoded.exp * 1000
    : Date.now() + 24 * 60 * 60 * 1000;

  await Token.create({
    token,
    expiredAt: new Date(exp),
  });

  return res.status(200).json({
    success: true,
    message: "Login successfully",
    data: {
      uid: user.uid,
      name: user.name,
      role,
      token,
    },
  });
};

export const logout = async (req, res) => {
  const authHeader = req.headers["authorization"];
  const activeToken = authHeader && authHeader.split(" ")[1];

  const token = await Token.findOne({
    where: {
      token: activeToken,
    },
  });

  if (!token) {
    return res.status(200).json({
      success: true,
      message: "Logout successfully, Token already expired",
    });
  }

  await Token.destroy({
    where: {
      token: activeToken,
    },
  });

  return res.status(200).json({
    success: true,
    message: "Logout successfully",
  });
};
