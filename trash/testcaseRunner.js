import { executeCode } from "../app/utils/javascript/execute.js";

function normalize(code) {
  return code
    .replace(/\/\/.*|\/\*[\s\S]*?\*\//g, "")
    .replace(/\s+/g, "")
    .toLowerCase();
}

async function diceCoefficient(expected, actual) {
  const getBigrams = (str) => {
    const bigrams = new Set();
    for (let i = 0; i < str.length - 1; i++) {
      bigrams.add(str.substring(i, i + 2));
    }
    return bigrams;
  };

  const s1 = getBigrams(normalize(expected));
  const s2 = getBigrams(normalize(actual));

  let intersection = 0;
  for (let bit of s1) {
    if (s2.has(bit)) intersection++;
  }

  return Math.round(((2 * intersection) / (s1.size + s2.size)) * 100);
}

async function levenshteinDistance(expected, actual) {
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

  return Math.round(((maxLength - distance) / maxLength) * 100);
}

async function jaccardSimilarity(expected, actual) {
  const set1 = new Set(expected.split(/\W+/));
  const set2 = new Set(actual.split(/\W+/));

  const intersection = new Set([...set1].filter((x) => set2.has(x)));
  const union = new Set([...set1, ...set2]);

  return Math.round((intersection.size / union.size) * 100);
}

async function adaptiveSimilarity(expected, actual) {
  return;
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

    const similarity = await levenshteinDistance(expectedOutput, actualOutput);
    const threshold = 30;
    const pass = similarity >= threshold;

    console.log(
      `Score: ${similarity}\nExpected: ${expectedOutput}\nActual: ${actualOutput}`
    );

    result[`TC>${tc.name}`] = pass;
    result[`TC_WEIGHT>${tc.name}`] = tc.weight || 1;
  }

  return result;
}
