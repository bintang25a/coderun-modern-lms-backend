import express from "express";
import { run } from "../controllers/CoderunController.js";

const router = express.Router();

router.post("/run", run);
router.post("/grade", run);

export default router;
