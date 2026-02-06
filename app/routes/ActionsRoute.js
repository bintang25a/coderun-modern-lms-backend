import express from "express";
import { run, autoGrade, grade } from "../controllers/ActionsController.js";
import { verifyUser, assistantOnly } from "../middlewares/AuthUser.js";

const router = express.Router();

router.use(verifyUser);
router.post("/run", run);
router.post("/grade", assistantOnly, grade);
router.post("/auto-grade", assistantOnly, autoGrade);

export default router;
