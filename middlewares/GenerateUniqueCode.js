import Assignment from "../models/AssignmentModel.js";

export const generateAssignmentNumber = async (req, res, next) => {
  const count = await Assignment.count();
  let assignment_number = "";

  if (req.params.assignment_number) {
    assignment_number = req.params.assignment_number;
  } else {
    assignment_number = `${class_code}-${String(count + 1).padStart(2, "0")}`;
  }

  req.assignment_number = assignment_number;
  next();
};
