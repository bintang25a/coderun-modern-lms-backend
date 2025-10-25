import express from "express";
import {
  index,
  show,
  store,
  update,
  destroy,
} from "../controllers/ClassroomController.js";
import { verifyUser, assistantOnly } from "../middlewares/AuthUser.js";

const router = express.Router();

router.get("/classrooms", verifyUser, index);
router.get("/classrooms/:class_code", verifyUser, show);
router.post("/classrooms/", verifyUser, assistantOnly, store);
router.patch("/classrooms/:class_code", verifyUser, assistantOnly, update);
router.delete("/classrooms/:class_code", verifyUser, assistantOnly, destroy);

export default router;
