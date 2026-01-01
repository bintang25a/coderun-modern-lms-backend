export const autoGrade = async (req, res) => {
  const { assignment_number, language } = req.body;

  if (!assignment_number || !language) {
    return res.status(400).json({
      success: false,
      message: "Automatic grading failed, Field cannot empty",
    });
  }

  const assignment = await Assignment.findOne({
    where: { assignment_number },
    include: [
      {
        association: Assignment.associations.submissions,
        as: "submissions",
        attributes: ["submission_number", "student_uid", "grade", "answer"],
      },
    ],
  });

  if (!assignment) {
    return res.status(404).json({
      success: false,
      message: "Automatic grading failed, Assignment not found",
    });
  }

  // Folder output CSV
  const outputDir = path.join(BASE_DIR, "temp", req.uid);
  if (!fs.existsSync(outputDir)) {
    fs.mkdirSync(outputDir, { recursive: true });
  }

  // Parser setup
  const parser = new Parser();
  if (language === "c") parser.setLanguage(C);
  else if (language === "cpp") parser.setLanguage(CPP);
  else if (language === "java") parser.setLanguage(Java);
  else if (language === "python") parser.setLanguage(Python);
  else {
    return res.status(400).json({
      success: false,
      message: "Automatic grading failed, Unsupported language",
    });
  }

  const countNodeTypes = (node, counter) => {
    const type = node.type;
    counter[type] = (counter[type] || 0) + 1;
    for (let i = 0; i < node.childCount; i++) {
      countNodeTypes(node.child(i), counter);
    }
  };

  // Prepare variabel
  let labelError = 0;
  let csvHeader = null;
  let csvDatasetPath = "";
  const delimiter = " ";

  const labelling = async () => {
    let csvRows = [];
    let nodeKeys = new Set();
    let results = [];
    let row_id = 1;

    const parseDataset = async (filePath, keyPath, row_id) => {
      const ext = path.extname(filePath);

      if (ext !== `.${language}`) {
        labelError++;
        return { row_id, message: "Wrong language" };
      }

      const keyCode = fs.readFileSync(keyPath, "utf8");
      const keyTree = parser.parse(keyCode);

      const code = fs.readFileSync(filePath, "utf8");
      const tree = parser.parse(code);

      const keyCounter = {};
      countNodeTypes(keyTree.rootNode, keyCounter);

      const counter = {};
      countNodeTypes(tree.rootNode, counter);

      const allKeys = new Set([
        ...Object.keys(keyCounter),
        ...Object.keys(counter),
      ]);

      let score = 0;
      let T = 0;

      for (const key of allKeys) {
        const keyNode = keyCounter[key] || 0;
        const answerNode = counter[key] || 0;

        score += Math.min(keyNode, answerNode);
        T += keyNode;
      }

      score = (score / T) * 100;

      return {
        counter,
        score: Number(score.toFixed(2)),
        row_id,
      };
    };

    const datasetDir = path.join(BASE_DIR, "database", "datasets", language);
    const datasetFiles = fs.readdirSync(datasetDir);
    const keyFile = path
      .join(BASE_DIR, assignment.answer_key)
      .replace(/\\/g, "/");

    if (!fs.existsSync(keyFile)) {
      return res.status(400).json({
        success: false,
        message: "Automatic grading failed, Answer key not uploaded",
      });
    }

    const keyCode = fs.readFileSync(keyFile, "utf8");
    const keyTree = parser.parse(keyCode);
    const keyCounter = {};
    countNodeTypes(keyTree.rootNode, keyCounter);

    Object.keys(keyCounter).forEach((k) => nodeKeys.add(k));

    results.push({
      row_id: "key",
      score: 100,
      counter: keyCounter,
    });

    for (const file of datasetFiles) {
      const ext = path.extname(file);
      if (ext !== `.${language}`) continue;

      const filePath = path.join(datasetDir, file);

      const result = await parseDataset(filePath, keyFile, row_id++);

      if (result?.message) continue;

      results.push(result);

      Object.keys(result.counter).forEach((k) => nodeKeys.add(k));
    }

    for (const result of results) {
      const row = [result.row_id, result.score];

      Array.from(nodeKeys).forEach((key) => {
        row.push(result.counter[key] || 0);
      });

      csvRows.push(row);
    }

    csvHeader = ["row_id", "score", ...Array.from(nodeKeys)];

    function escape(value) {
      if (value.includes(",")) {
        return "coma";
      }
      return value;
    }

    const header = csvHeader.map(escape).join(delimiter);
    const rows = csvRows.map((row) =>
      row.map((v) => escape(String(v))).join(delimiter)
    );

    csvDatasetPath = path.join(outputDir, `DATASET_${assignment_number}.csv`);
    fs.writeFileSync(csvDatasetPath, [header, ...rows].join("\n"));
  };

  await labelling();

  let csvAnswerPath = "";
  const preproccessing = async () => {
    let csvRows = [];
    let nodeKeys = new Set();
    let results = [];

    const parseAnswer = async (filePath, row_id) => {
      const ext = path.extname(filePath);

      if (!fs.existsSync(filePath)) {
        labelError++;

        return {
          submission_number: row_id,
          message: "Code not found",
        };
      }

      if (ext != `.${language}`) {
        labelError++;

        return {
          submission_number: row_id,
          message: "Wrong language",
        };
      }

      const code = fs.readFileSync(filePath, "utf8");
      const tree = parser.parse(code);

      const counter = {};
      countNodeTypes(tree.rootNode, counter);

      return {
        row_id,
        counter,
      };
    };

    const submissions = assignment.submissions;

    for (const submission of submissions) {
      if (submission.grade != null) {
        continue;
      }

      const fileNumber = submission.submission_number;
      const filePath = path
        .join(BASE_DIR, submission.answer)
        .replace(/\\/g, "/");

      const result = await parseAnswer(filePath, fileNumber);

      if (result?.message) continue;

      results.push(result);

      Object.keys(result.counter).forEach((k) => nodeKeys.add(k));
    }

    for (const result of results) {
      const row = [result.row_id];

      Array.from(csvHeader).forEach((key) => {
        if (key !== "row_id") {
          row.push(result.counter[key] || 0);
        }
      });

      csvRows.push(row);
    }

    const combinedKeysSet = new Set([...csvHeader, ...Array.from(nodeKeys)]);

    csvHeader = Array.from(combinedKeysSet);

    function escape(value) {
      if (value.includes(",")) {
        return "coma";
      }
      return value;
    }

    const header = csvHeader.map(escape).join(delimiter);
    const rows = csvRows.map((row) =>
      row.map((v) => escape(String(v))).join(delimiter)
    );

    csvAnswerPath = path.join(outputDir, `ANSWER_${assignment_number}.csv`);
    fs.writeFileSync(csvAnswerPath, [header, ...rows].join("\n"));
  };

  await preproccessing();

  return res.status(200).json({
    success: true,
    message: `Automatic grading successfully, ${labelError} file error`,
    dataset: csvDatasetPath,
    answer: csvAnswerPath,
  });
};
