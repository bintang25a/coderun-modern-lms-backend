import express from "express";
import {
  index,
  show,
  store,
  update,
  destroy,
} from "../controllers/AssignmentController.js";
import { generateAssignmentNumber } from "../middlewares/GenerateUniqueCode.js";
import uploadProgram from "../middlewares/UploadProgram.js";

const router = express.Router();

router.get("/assignments/:class_code", index);
router.get("/assignments/:class_code/:assignment_number", show);
router.post(
  "/assignments/",
  generateAssignmentNumber,
  uploadProgram.single("program"),
  store
);
router.patch(
  "/assignments/:class_code/:assignment_number",
  generateAssignmentNumber,
  uploadProgram.single("program"),
  update
);
router.delete("/assignments/:class_code/:assignment_number", destroy);

export default router;
