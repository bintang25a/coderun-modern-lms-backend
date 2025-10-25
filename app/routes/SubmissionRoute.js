import express from "express";
import {
  index,
  show,
  store,
  grade,
  update,
  destroy,
} from "../controllers/SubmissionController.js";
import { generateSubmissionNumber } from "../middlewares/GenerateUniqueCode.js";
import { verifyUser, assistantOnly } from "../middlewares/AuthUser.js";
import uploadProgram from "../middlewares/UploadAssignmentFile.js";

const router = express.Router();

router.get("/submissions/:assignment_number", verifyUser, index);
router.get(
  "/submissions/:assignment_number/:submission_number",
  verifyUser,
  show
);
router.post(
  "/submissions/:assignment_number",
  verifyUser,
  uploadProgram.single("answer"),
  generateSubmissionNumber,
  store
);
router.patch(
  "/submissions/grade/:submission_number",
  verifyUser,
  assistantOnly,
  grade
);
router.patch(
  "/submissions/:assignment_number/:submission_number",
  verifyUser,
  uploadProgram.single("answer"),
  update
);
router.delete(
  "/submissions/:assignment_number/:submission_number",
  verifyUser,
  destroy
);

export default router;
