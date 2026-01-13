import fs from "fs";
import path from "path";
import pLimit from "p-limit";
import { parseCode } from "./parser.js";
import { SBCAM, SEDM, STCAM } from "./nodeCalculator.js";
import { deduplicateDataset, extendSchema } from "./schema.js";
import { runTestCasesForFile } from "./testcaseRunner.js";
import { executeCode } from "./execute.js";
import { scaling, scoring } from "./determineLabel.js";

const BASE_DIR = process.cwd();

export async function buildDataset(param) {
  const { assignment, uid, schemaSet, language } = param;
  const { testCases, parser, CONCURRENCY = 1 } = param;

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

  await extendSchema(schemaSet, keyCounter);

  const rows = [];
  rows.push({
    row_id: "key",
    score: 100,
    scale: "high",
    counter: keyCounter,
  });

  const expected = {};
  for (const tc of testCases) {
    console.log(`User: ${uid} - ${tc.name} execute`);

    const result = await executeCode({
      uid,
      language,
      codePath: keyPath,
      input: tc.input || "",
      executeName: `sandbox-dataset-${uid}-${tc.name}`,
    });

    console.log(`${tc.name}: ${result.output?.trim()}\n`);

    expected[tc.name] = result.output?.trim();
  }

  console.log(`User: ${uid} - Dataset labelling start:`);

  const limit = pLimit(CONCURRENCY);
  const files = fs.readdirSync(datasetDir);

  const tasks = files.map((file, idx) =>
    limit(async () => {
      const rowId = idx + 1;
      const filePath = path.join(datasetDir, file);

      const interval = Math.max(1, Math.floor(files.length / 10));
      if (rowId % interval === 0 || rowId === 1 || rowId === files.length) {
        console.log(
          `User: ${uid} - Dataset labelling process... ${rowId}/${files.length}`
        );
      }

      const counter = parseCode(parser, filePath);
      if (!counter) return null;

      const testCaseResult = await runTestCasesForFile({
        uid,
        language,
        codePath: filePath,
        expected,
        testCases,
        executeName: `sandbox-dataset-${uid}-${rowId}`,
      });

      return {
        row_id: rowId,
        counter,
        testCaseResult,
        filePath,
      };
    })
  );

  const intermediateResults = (await Promise.all(tasks)).filter(Boolean);

  for (const r of intermediateResults) {
    extendSchema(schemaSet, r.counter);
  }

  for (const r of intermediateResults) {
    const sbcamScore = await SBCAM(keyCounter, r.counter);
    const sedmScore = await SEDM(r.counter);
    const stcamScore = await STCAM(r.testCaseResult);

    console.log(
      `SBCAM: ${sbcamScore}, SEDM: ${sedmScore}, STCAM ${stcamScore}`
    );

    const scoreTemp = await scoring({ sbcamScore, stcamScore, sedmScore });
    const scale = await scaling({ sbcamScore, stcamScore });
    const score =
      scale === "low"
        ? Math.min(scoreTemp, 30)
        : scale === "medium"
        ? Math.max(scoreTemp, 30)
        : Math.max(scoreTemp, 70);

    rows.push({
      row_id: r.row_id,
      score,
      scale,
      counter: r.counter,
      filePath: r.filePath,
    });
  }

  console.log(`User: ${uid} - Dataset labelling finish!`);

  return await deduplicateDataset(rows);
}
