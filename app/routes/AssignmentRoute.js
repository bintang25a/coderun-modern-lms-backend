import express from "express";
import {
  index,
  show,
  store,
  update,
  destroy,
} from "../controllers/AssignmentController.js";
import { verifyUser, assistantOnly } from "../middlewares/AuthUser.js";
import { generateAssignmentNumber } from "../middlewares/GenerateUniqueCode.js";
import uploadProgram from "../middlewares/UploadAssignmentFile.js";

const router = express.Router();

router.get("/assignments/:class_code", verifyUser, index);
router.get("/assignments/:class_code/:assignment_number", verifyUser, show);
router.post(
  "/assignments/:class_code",
  verifyUser,
  assistantOnly,
  generateAssignmentNumber,
  uploadProgram.single("answer_key"),
  store
);
router.patch(
  "/assignments/:class_code/:assignment_number",
  verifyUser,
  assistantOnly,
  uploadProgram.single("answer_key"),
  update
);
router.delete(
  "/assignments/:class_code/:assignment_number",
  verifyUser,
  assistantOnly,
  destroy
);

export default router;
