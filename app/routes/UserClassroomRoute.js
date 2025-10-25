import express from "express";
import {
  index,
  store,
  update,
  destroy,
} from "../controllers/UserClassroomController.js";
import { verifyUser, assistantOnly } from "../middlewares/AuthUser.js";

const router = express.Router();

router.get("/classrooms/users/list", verifyUser, index);
router.post("/classrooms/users/add", verifyUser, store);
router.patch(
  "/classrooms/users/:class_code/:uid",
  verifyUser,
  assistantOnly,
  update
);
router.delete(
  "/classrooms/users/:class_code/:uid",
  verifyUser,
  assistantOnly,
  destroy
);

export default router;
