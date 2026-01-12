import { executeCode } from "./execute.js";

async function outputSimilarity(expected, actual) {
  if (expected === actual) return 100;
  if (!expected || !actual) return 0;

  const n = expected.length;
  const m = actual.length;

  let prevRow = Array.from({ length: m + 1 }, (_, i) => i);
  let currRow = new Array(m + 1);

  for (let i = 1; i <= n; i++) {
    currRow[0] = i;
    for (let j = 1; j <= m; j++) {
      const cost = expected[i - 1] === actual[j - 1] ? 0 : 1;
      currRow[j] = Math.min(
        currRow[j - 1] + 1,
        prevRow[j] + 1,
        prevRow[j - 1] + cost
      );
    }
    prevRow = [...currRow];
  }

  const distance = prevRow[m];
  const maxLength = Math.max(n, m);

  return Math.ceil(((maxLength - distance) / maxLength) * 100);
}

export async function runTestCasesForFile(param) {
  const { uid, language, codePath, expected, testCases, containerName } = param;

  const result = {};
  const executions = await testCases.map((tc) =>
    executeCode({
      uid,
      language,
      codePath,
      input: tc.input || "",
      containerName: `${containerName}-${tc.name}`,
    }).then((actual) => ({
      tc,
      actual,
    }))
  );

  const outputs = await Promise.all(executions);

  for (const { tc, actual } of outputs) {
    const expectedOutput = expected[tc.name]?.trim() ?? "";
    const actualOutput = actual.output?.trim() ?? "";

    const similarity = await outputSimilarity(expectedOutput, actualOutput);
    const threshold = 30;
    const pass = similarity >= threshold;

    console.log(`Expected: ${expectedOutput}\nActual: ${actualOutput}`);

    result[`TC>${tc.name}`] = pass;
    result[`TC_WEIGHT>${tc.name}`] = tc.weight || 1;
  }

  return result;
}
