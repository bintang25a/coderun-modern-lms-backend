import express from "express";
import {
  index,
  show,
  store,
  update,
  destroy,
} from "../controllers/ClassroomController.js";

const router = express.Router();

router.get("/classrooms", index);
router.get("/classrooms/:class_code", show);
router.post("/classrooms/", store);
router.patch("/classrooms/:class_code", update);
router.delete("/classrooms/:class_code", destroy);

export default router;
