import express from "express";
import {
  index,
  store,
  destroy,
} from "../controllers/MaterialClassroomController.js";
import { verifyUser, assistantOnly } from "../middlewares/AuthUser.js";

const router = express.Router({ mergeParams: true });

router.use(verifyUser);
router.get("/", index);
router.post("/:class_code/:material_number", assistantOnly, store);
router.delete("/:class_code/:material_number", assistantOnly, destroy);

export default router;
