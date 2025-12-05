import express from "express";
import { run, autoGrade } from "../controllers/ActionsController.js";
import { verifyUser, assistantOnly } from "../middlewares/AuthUser.js";

const router = express.Router();

router.use(verifyUser, assistantOnly);
router.post("/run", run);
// router.post("/grade", parsing);
router.post("/grade", autoGrade);

export default router;
