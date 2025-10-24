import express from "express";
import {
  index,
  show,
  store,
  update,
  destroy,
} from "../controllers/UserController.js";
import uploadPhoto from "../middlewares/UploadUserPhoto.js";

const router = express.Router();

router.get("/users", index);
router.get("/users/:uid", show);
router.post("/users/", uploadPhoto.single("photo"), store);
router.patch("/users/:uid", uploadPhoto.single("photo"), update);
router.delete("/users/:uid", destroy);

export default router;
