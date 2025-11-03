import express from "express";
import {
  index,
  show,
  store,
  update,
  destroy,
} from "../controllers/AssignmentController.js";
import { verifyUser, assistantOnly } from "../middlewares/AuthUser.js";
import { generateAssignmentNumber as GAN } from "../middlewares/GenerateUniqueCode.js";
import UploadFile from "../middlewares/UploadAssignmentFile.js";

const router = express.Router({ mergeParams: true });

router.use(verifyUser);
router.get("/", index);
router.get("/:assignment_number", show);
router.use(assistantOnly);
router.post("/", GAN, UploadFile.single("answer"), store);
router.patch("/:assignment_number", UploadFile.single("answer"), update);
router.delete("/:assignment_number", destroy);

export default router;
