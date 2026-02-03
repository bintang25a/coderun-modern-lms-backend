import express from "express";
import {
  index,
  store,
  update,
  destroy,
} from "../controllers/MaterialClassroomController.js";
import { verifyUser, assistantOnly } from "../middlewares/AuthUser.js";

const router = express.Router({ mergeParams: true });

router.use(verifyUser);
router.get("/", index);
router.post("/", assistantOnly, store);
router.patch("/:class_code/:material_number", assistantOnly, update);
router.delete("/:class_code/:material_number", assistantOnly, destroy);

export default router;
