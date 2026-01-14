import path from "path";
import { parseCode } from "./parser.js";
import { extendSchema } from "./schema.js";
import { SBCAM, STCAM, SEDM } from "./nodeCalculator.js";
import { scaling } from "./determineLabel.js";
import { executeCode } from "./execute.js";
import { runTestCasesForFile } from "./testcaseRunner.js";

const BASE_DIR = process.cwd();

export async function buildAnswer(param) {
  const { assignment, parser, testCases, schemaSet, uid, language, timeLimit } =
    param;

  const keyPath = path
    .join(BASE_DIR, assignment.answer_key)
    .replace(/\\/g, "/");

  const keyCounter = parseCode(parser, keyPath);

  if (!keyCounter) {
    throw new Error("Automatic grading failed, Answer key not found");
  }

  const expected = {};
  for (const tc of testCases) {
    const result = await executeCode({
      uid,
      language,
      codePath: keyPath,
      input: tc.input || "",
      executeName: `sandbox_key_${tc.name.trim()}`,
      timeLimit,
    });

    expected[tc.name] = result.output?.trim();
  }

  console.log(`User: ${uid} - Answer labelling start:`);

  const files = assignment.submissions;
  const rows = [];
  let rowId = 0;
  for (const sub of files) {
    if (sub.grade != null) continue;

    const filePath = path.join(BASE_DIR, sub.answer).replace(/\\/g, "/");

    const counter = parseCode(parser, filePath);
    if (!counter) continue;

    await extendSchema(schemaSet, counter);

    const testCaseResult = await runTestCasesForFile({
      uid,
      language,
      codePath: filePath,
      expected,
      testCases,
      executeName: `sandbox-answer-${uid}-${rowId++}`,
      timeLimit,
    });

    const sbcamScore = await SBCAM(keyCounter, counter);
    const sedmScore = await SEDM(counter);
    const stcamScore = await STCAM(testCaseResult);

    const scaleTemp = await scaling({ sbcamScore, stcamScore });
    const scale = sedmScore == 100 ? scaleTemp : "low";

    const interval = Math.max(1, Math.floor(files.length / 10));
    if (rowId % interval === 0 || rowId === 1 || rowId === files.length) {
      console.log(
        `User: ${uid} - Answer labelling process... ${rowId}/${files.length}`
      );
    }

    rows.push({
      row_id: sub.submission_number,
      score: 0,
      scale,
      counter,
    });
  }

  console.log(`User: ${uid} - Answer labelling finish!`);

  return rows;
}
