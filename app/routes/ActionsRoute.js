import express from "express";
import { run, autoGrade, grade } from "../controllers/ActionsController.js";
import { verifyUser, assistantOnly } from "../middlewares/AuthUser.js";

const router = express.Router();

router.use(verifyUser);
router.post("/run", run);
router.post("/grade", assistantOnly, autoGrade);
router.patch("/:submission_number/grade", assistantOnly, grade);

export default router;
