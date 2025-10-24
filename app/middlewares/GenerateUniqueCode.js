import Assignment from "../../database/models/Assignment.js";

export const generateAssignmentNumber = async (req, res, next) => {
  try {
    const { class_code, assignment_number: paramNumber } = req.params;

    if (paramNumber) {
      req.assignment_number = paramNumber;
      return next();
    }

    const lastAssignment = await Assignment.findOne({
      where: { class_code },
      order: [["createdAt", "DESC"]],
    });

    let nextNumber = 1;

    if (lastAssignment) {
      const lastNumberPart = parseInt(
        lastAssignment.assignment_number.split("-").pop(),
        10
      );
      nextNumber = lastNumberPart + 1;
    }

    const newAssignmentNumber = `${class_code}-${String(nextNumber).padStart(
      2,
      "0"
    )}`;

    req.assignment_number = newAssignmentNumber;
    next();
  } catch (error) {
    console.error("Error generating assignment number:", error);
    res.status(500).json({ message: "Failed to generate assignment number" });
  }
};

export const generateSubmissionNumber = async (req, res, next) => {
  try {
    const dateNumber = new Date().toISOString().replace(/[-:.TZ]/g, "");
    const idNumber = req.params.student_uid;
    console.log(req.body);

    req.submission_number = `${idNumber}-${dateNumber}`;
    next();
  } catch (error) {
    console.error("Error generating submission number:", error);
    res.status(500).json({ message: "Failed to generate submission number" });
  }
};
