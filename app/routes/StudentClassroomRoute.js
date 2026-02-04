import express from "express";
import {
  index,
  store,
  destroy,
} from "../controllers/StudentClassroomController.js";
import { verifyUser } from "../middlewares/AuthUser.js";

const router = express.Router({ mergeParams: true });

router.use(verifyUser);
router.get("/", index);
router.post("/", store);
router.delete("/:class_code/:uid", destroy);

export default router;
