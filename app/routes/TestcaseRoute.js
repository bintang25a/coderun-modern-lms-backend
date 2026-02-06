import express from "express";
import {
  index,
  show,
  store,
  update,
  destroy,
} from "../controllers/TestcaseController.js";
import { verifyUser } from "../middlewares/AuthUser.js";

const router = express.Router({ mergeParams: true });

router.use(verifyUser);
router.get("/", index);
router.get("/:testcase_number", show);
router.post("/", store);
router.patch("/:testcase_number", update);
router.delete("/:testcase_number", destroy);

export default router;
