import express from "express";
import {
  index,
  show,
  store,
  update,
  destroy,
} from "../controllers/SubmissionController.js";
import { generateSubmissionNumber as GSN } from "../middlewares/GenerateUniqueCode.js";
import { verifyUser } from "../middlewares/AuthUser.js";
import uploadAnswer from "../middlewares/UploadAssignmentFile.js";

const router = express.Router({ mergeParams: true });

router.use(verifyUser);
router.get("/", index);
router.get("/:submission_number", show);
router.post("/", uploadAnswer.single("answer"), GSN, store);
router.patch("/:submission_number", uploadAnswer.single("answer"), update);
router.delete("/:submission_number", destroy);

export default router;
