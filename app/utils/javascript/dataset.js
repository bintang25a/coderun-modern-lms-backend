import fs from "fs";
import path from "path";
import { parseCode } from "./parser.js";
import { calculateScore } from "./score.js";
import { deduplicateDataset, extendSchema } from "./schema.js";

const BASE_DIR = process.cwd();

export function buildDataset({ assignment, parser, language, schemaSet }) {
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

  for (const file of fs.readdirSync(datasetDir)) {
    if (!file.endsWith(`.${language}`)) continue;

    const counter = parseCode(parser, path.join(datasetDir, file));
    if (!counter) continue;

    extendSchema(schemaSet, counter);

    const filePath = path.join(datasetDir, file);

    rows.push({
      row_id: rowId++,
      score: calculateScore(keyCounter, counter),
      counter,
      filePath,
    });
  }

  return deduplicateDataset(rows);
}
