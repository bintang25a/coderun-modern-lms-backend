import express from "express";
import { run, autoGrade, grade } from "../controllers/ActionsController.js";
import { verifyUser, assistantOnly } from "../middlewares/AuthUser.js";

const router = express.Router();

router.use(verifyUser, assistantOnly);
router.post("/run", run);
router.post("/grade", autoGrade);
router.patch("/:submission_number/grade", grade);

export default router;
