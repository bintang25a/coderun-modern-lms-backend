import express from "express";
import {
  index,
  show,
  store,
  update,
  destroy,
} from "../controllers/UserController.js";
import { verifyUser, adminOnly } from "../middlewares/AuthUser.js";
import uploadPhoto from "../middlewares/UploadUserPhoto.js";

const router = express.Router();

router.get("/users", verifyUser, index);
router.get("/users/:uid", verifyUser, show);
router.post(
  "/users/",
  verifyUser,
  adminOnly,
  uploadPhoto.single("photo"),
  store
);
router.patch("/users/:uid", verifyUser, uploadPhoto.single("photo"), update);
router.delete("/users/:uid", verifyUser, adminOnly, destroy);

export default router;
