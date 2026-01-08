import { executeCode } from "./execute.js";

function tokenize(str) {
  return str.trim().split(/\s+/);
}

function outputSimilarity(expected, actual) {
  const T = tokenize(expected);
  const A = tokenize(actual);

  const freq = {};
  T.forEach((t) => (freq[t] = (freq[t] || 0) + 1));

  let matchedWeight = 0;

  for (const token of A) {
    if (freq[token]) {
      matchedWeight += 1;
      freq[token]--;
    }
  }

  if (T.length === 0) return 100;

  return (matchedWeight / T.length) * 100;
}

export async function runTestCasesForFile({
  uid,
  language,
  codePath,
  expected,
  testCases,
  containerName,
}) {
  const result = {};

  const executions = testCases.map((tc) =>
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

    const similarity = outputSimilarity(expectedOutput, actualOutput);
    const threshold = 30;
    const pass = similarity >= threshold;

    result[`TC>${tc.name}`] = pass;
    result[`TC_WEIGHT>${tc.name}`] = tc.weight || 1;
  }

  return result;
}
