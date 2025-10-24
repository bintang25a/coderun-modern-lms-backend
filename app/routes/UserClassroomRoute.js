import express from "express";
import {
  index,
  store,
  update,
  destroy,
} from "../controllers/UserClassroomController.js";

const router = express.Router();

router.get("/classrooms/users/list", index);
router.post("/classrooms/users/add", store);
router.patch("/classrooms/users/:class_code/:uid", update);
router.delete("/classrooms/users/:class_code/:uid", destroy);

export default router;
