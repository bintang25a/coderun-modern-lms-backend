import express from "express";
import {
  index,
  show,
  store,
  update,
  destroy,
} from "../controllers/MaterialController.js";
import { verifyUser, assistantOnly } from "../middlewares/AuthUser.js";
import { generateMaterialNumber as GMN } from "../middlewares/GenerateUniqueCode.js";
import UploadFile from "../middlewares/UploadMaterialFile.js";

const router = express.Router({ mergeParams: true });

router.use(verifyUser);
router.get("/", index);
router.get("/:material_number", show);
router.use(assistantOnly);
router.post("/", GMN, UploadFile.single("material"), store);
router.patch("/:material_number", update);
router.delete("/:material_number", destroy);

export default router;
