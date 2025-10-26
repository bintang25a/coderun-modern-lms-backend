import express from "express";
import {
  index,
  store,
  update,
  destroy,
} from "../controllers/AssistantClassroom.js";
import { verifyUser, assistantOnly } from "../middlewares/AuthUser.js";

const router = express.Router();

router.use(verifyUser);
router.get("/", index);
router.post("/", store);
router.patch("/:class_code/:uid", assistantOnly, update);
router.delete("/:class_code/:uid", assistantOnly, destroy);

export default router;
