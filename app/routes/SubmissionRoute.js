import express from "express";
import {
  index,
  show,
  store,
  update,
  destroy,
} from "../controllers/SubmissionController.js";
import { generateSubmissionNumber } from "../middlewares/GenerateUniqueCode.js";
import uploadProgram from "../middlewares/UploadAssignmentFile.js";

const router = express.Router();

router.get("/submissions/:assignment_number", index);
router.get("/submissions/:assignment_number/:submission_number", show);
router.post(
  "/submissions/:assignment_number/:student_uid",
  uploadProgram.single("answer"),
  generateSubmissionNumber,
  store
);
router.patch(
  "/submissions/:assignment_number/:submission_number",
  uploadProgram.single("answer"),
  generateSubmissionNumber,
  update
);
router.delete("/submissions/:assignment_number/:submission_number", destroy);

export default router;
