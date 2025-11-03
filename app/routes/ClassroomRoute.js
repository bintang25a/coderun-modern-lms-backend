import express from "express";
import {
  index,
  show,
  store,
  update,
  destroy,
} from "../controllers/ClassroomController.js";
import { verifyUser, assistantOnly } from "../middlewares/AuthUser.js";

const router = express.Router({ mergeParams: true });

router.use(verifyUser);
router.get("/", index);
router.get("/:class_code", show);
router.use(assistantOnly);
router.post("/", store);
router.patch("/:class_code", update);
router.delete("/:class_code", destroy);

export default router;
