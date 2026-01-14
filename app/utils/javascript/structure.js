import fs from "fs";
import { hybridSimilarity } from "./support-similarity.js";
import { normalizeCounter, executeCode } from "./support.js";

export const runTestCases = async (param) => {
  const {
    uid,
    language,
    codePath,
    expected,
    testCases,
    executeName,
    timeLimit,
  } = param;

  const result = {};
  const executions = await testCases.map((tc) =>
    executeCode({
      uid,
      language,
      codePath,
      input: tc.input || "",
      executeName: `${executeName}_${tc.name.trim()}`,
      timeLimit,
    }).then((actual) => ({
      tc,
      actual,
    }))
  );

  const outputs = await Promise.all(executions);

  for (const { tc, actual } of outputs) {
    const expectedOutput = expected[tc.name]?.trim() ?? "";
    const actualOutput = actual.output?.trim() ?? "";

    const similarity = await hybridSimilarity(expectedOutput, actualOutput);
    const threshold = 30;
    const pass = similarity >= threshold;

    result[`TC>${tc.name}`] = pass;
    result[`TC_WEIGHT>${tc.name}`] = tc.weight || 1;
  }

  return result;
};

export const deduplicateDataset = async (datasetRows) => {
  const seen = new Map();
  const result = [];

  for (const row of datasetRows) {
    if (row.row_id === "key") {
      result.push(row);
      continue;
    }

    const signature = await normalizeCounter(row.counter);

    if (seen.has(signature)) {
      if (row.filePath && fs.existsSync(row.filePath)) {
        fs.unlinkSync(row.filePath);
      }
      continue;
    }

    seen.set(signature, true);
    result.push(row);
  }

  return result;
};
