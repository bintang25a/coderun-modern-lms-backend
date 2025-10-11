import express from "express";
import {
  index,
  show,
  store,
  update,
  destroy,
} from "../controllers/UserController.js";
import { verifyUser, adminOnly } from "../middlewares/AuthUser.js";

const router = express.Router();

router.get("/users", index);
router.get("/users/:uid", show);
router.post("/users/", store);
router.patch("/users/:uid", update);
router.delete("/users/:uid", destroy);

export default router;
