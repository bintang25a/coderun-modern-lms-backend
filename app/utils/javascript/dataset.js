import fs from "fs";
import path from "path";
import { parseCode } from "./parser.js";
import { calculateScore } from "./score.js";
import { deduplicateDataset, extendSchema } from "./schema.js";
import { runTestCasesForFile } from "./testcaseRunner.js";
import { executeCode } from "./execute.js";

const BASE_DIR = process.cwd();

export async function buildDataset({
  assignment,
  parser,
  schemaSet,
  language,
  testCases,
}) {
  const datasetDir = path
    .join(BASE_DIR, "database/resource", language)
    .replace(/\\/g, "/");
  const keyPath = path
    .join(BASE_DIR, assignment.answer_key)
    .replace(/\\/g, "/");

  const keyCounter = parseCode(parser, keyPath);

  if (!keyCounter) {
    throw new Error("Automatic grading failed, Answer key not found");
  }

  extendSchema(schemaSet, keyCounter);

  const rows = [];
  rows.push({
    row_id: "key",
    score: 100,
    counter: keyCounter,
  });

  let rowId = 1;
  const expected = {};

  for (const tc of testCases) {
    const result = await executeCode({
      uiduid: `${rowId}_answer`,
      language,
      codePath: keyPath,
      input: tc.input || "",
    });

    expected[tc.name] = result.output;
  }

  rowId = 1;
  for (const file of fs.readdirSync(datasetDir)) {
    if (!file.endsWith(`.${language}`)) continue;

    const counter = parseCode(parser, path.join(datasetDir, file));
    if (!counter) continue;

    extendSchema(schemaSet, counter);

    const filePath = path.join(datasetDir, file);

    // console.log(filePath);

    const testCaseResult = await runTestCasesForFile({
      uid: rowId,
      language,
      codePath: filePath,
      expected,
      testCases,
    });

    rows.push({
      row_id: rowId++,
      score: calculateScore(keyCounter, counter, testCaseResult),
      counter,
      testCaseResult,
      filePath,
    });
  }

  return deduplicateDataset(rows);
}
