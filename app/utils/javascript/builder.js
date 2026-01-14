import fs from "fs";
import path from "path";
import { spawn } from "child_process";
import pLimit from "p-limit";
import { SBCAM, SEDM, STCAM } from "./support-ast.js";
import { extendSchema, executeCode, parseCode, escapeCSV } from "./support.js";
import { deduplicateDataset, runTestCases } from "./structure.js";
import { scaling, scoring } from "./support-label.js";

const BASE_DIR = process.cwd();

export const buildAnswerKey = async (param) => {
  const { parser, language, testCases, uid, timeLimit, assignment } = param;

  const keyPath = path
    .join(BASE_DIR, assignment.answer_key)
    .replace(/\\/g, "/");

  const keyCounter = await parseCode(parser, keyPath);

  if (!keyCounter) {
    throw new Error("Automatic grading failed, Answer key not found");
  }

  const expected = {};
  for (const tc of testCases) {
    console.log(`User: ${uid} - ${tc.name} execute`);

    const result = await executeCode({
      uid,
      language,
      codePath: keyPath,
      input: tc.input || "",
      executeName: `sandbox_key_${tc.name.trim()}`,
      timeLimit,
    });

    // console.log(result.output?.trim());

    expected[tc.name] = result.output?.trim();
  }

  return {
    expected,
    keyCounter,
  };
};

export const buildDataset = async (param) => {
  const {
    parser,
    schemaSet,
    language,
    testCases,
    uid,
    timeLimit,
    answerKey,
    CONCURRENCY,
  } = param;

  const { keyCounter, expected } = await answerKey;

  const datasetDir = path
    .join(BASE_DIR, "database/resource", language)
    .replace(/\\/g, "/");

  await extendSchema(schemaSet, keyCounter);

  const rows = [];
  rows.push({
    row_id: "key",
    score: 100,
    scale: "high",
    counter: keyCounter,
  });

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

      const counter = await parseCode(parser, filePath);
      if (!counter) return null;

      const testCaseResult = await runTestCases({
        uid,
        language,
        codePath: filePath,
        expected,
        testCases,
        executeName: `sandbox_dataset_${rowId}`,
        timeLimit,
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
    await extendSchema(schemaSet, r.counter);
  }

  for (const r of intermediateResults) {
    const sbcamScore = await SBCAM(keyCounter, r.counter);
    const sedmScore = await SEDM(r.counter);
    const stcamScore = await STCAM(r.testCaseResult);

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
};

export const buildAnswer = async (param) => {
  const {
    parser,
    schemaSet,
    language,
    testCases,
    uid,
    timeLimit,
    answerKey,
    assignment,
    REGRADE,
  } = param;

  const { keyCounter, expected } = await answerKey;

  const submissions = assignment.submissions;

  console.log(`User: ${uid} - Answer labelling start:`);

  const rows = [];
  let rowId = 0;
  for (const sub of submissions) {
    if (sub.grade != null && !REGRADE) continue;

    const filePath = path.join(BASE_DIR, sub.answer).replace(/\\/g, "/");

    const counter = await parseCode(parser, filePath);
    if (!counter) continue;

    await extendSchema(schemaSet, counter);

    const testCaseResult = await runTestCases({
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

    const interval = Math.max(1, Math.floor(submissions.length / 10));
    if (rowId % interval === 0 || rowId === 1 || rowId === submissions.length) {
      console.log(
        `User: ${uid} - Answer labelling process... ${rowId}/${submissions.length}`
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
};

export const buildHeader = async (schemaSet) => {
  return ["row_id", "score", "scale", ...Array.from(schemaSet).sort()];
};

export const buildCSV = async (filePath, header, rows, delimiter = ",") => {
  const content = [
    header.map((h) => escapeCSV(h, delimiter)).join(delimiter),
    ...rows.map((row) =>
      row.map((cell) => escapeCSV(cell, delimiter)).join(delimiter)
    ),
  ].join("\n");

  fs.writeFileSync(filePath, content);
};

export const buildModel = async (param) =>
  new Promise((resolve, reject) => {
    const {
      modelPyPath,
      datasetPath,
      answerPath,
      resultPath,
      timeLimit = 60000,
    } = param;

    const py = spawn("python3", [modelPyPath], {
      stdio: ["pipe", "ignore", "pipe"], // stdout di-ignore
    });

    let stderr = "";
    let finished = false;

    // kirim input ke python (SESUSAI URUTAN input())
    py.stdin.write(datasetPath + "\n");
    py.stdin.write(answerPath + "\n");
    py.stdin.write(resultPath + "\n");
    py.stdin.end();

    py.stderr.on("data", (d) => {
      stderr += d.toString();
    });

    const timer = setTimeout(() => {
      if (finished) return;
      finished = true;
      py.kill("SIGKILL");
      reject({ message: "Python model timeout" });
    }, timeLimit);

    py.on("close", (code) => {
      if (finished) return;
      finished = true;
      clearTimeout(timer);

      if (code !== 0) {
        return reject({
          message: "Python model failed",
          stderr,
        });
      }

      // sukses → JSON sudah ditulis oleh Python
      resolve({ success: true, resultPath });
    });
  });
