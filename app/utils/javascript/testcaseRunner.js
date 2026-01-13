import { executeCode } from "./execute.js";

async function lcsSimilarity(a, b) {
  if (!a || !b) return 0;

  const n = a.length;
  const m = b.length;

  // Optimized DP (2 rows)
  let prev = new Uint16Array(m + 1);
  let curr = new Uint16Array(m + 1);

  for (let i = 1; i <= n; i++) {
    for (let j = 1; j <= m; j++) {
      if (a[i - 1] === b[j - 1]) {
        curr[j] = prev[j - 1] + 1;
      } else {
        curr[j] = Math.max(prev[j], curr[j - 1]);
      }
    }
    prev.set(curr);
    curr.fill(0);
  }

  return Math.round((prev[m] / Math.max(n, m)) * 100);
}

async function cosineSimilarity(a, b) {
  if (!a || !b) return 0;

  const tokenize = (s) => {
    const map = new Map();
    s.toLowerCase()
      .split(/\W+/)
      .forEach((t) => {
        if (!t) return;
        map.set(t, (map.get(t) || 0) + 1);
      });
    return map;
  };

  const v1 = tokenize(a);
  const v2 = tokenize(b);

  const tokens = new Set([...v1.keys(), ...v2.keys()]);

  let dot = 0;
  let mag1 = 0;
  let mag2 = 0;

  for (const t of tokens) {
    const x = v1.get(t) || 0;
    const y = v2.get(t) || 0;
    dot += x * y;
    mag1 += x * x;
    mag2 += y * y;
  }

  if (mag1 === 0 || mag2 === 0) return 0;

  return Math.round((dot / (Math.sqrt(mag1) * Math.sqrt(mag2))) * 100);
}

async function lineSimilarity(a, b) {
  if (!a || !b) return 0;

  const A = a.trim().split(/\r?\n/);
  const B = b.trim().split(/\r?\n/);

  const maxLines = Math.max(A.length, B.length);
  let matched = 0;

  for (let i = 0; i < Math.min(A.length, B.length); i++) {
    if (A[i].trim() === B[i].trim()) {
      matched++;
    }
  }

  return Math.round((matched / maxLines) * 100);
}

async function hybridSimilarity(expected, actual) {
  if (!expected || !actual) return 0;

  const len = expected.length;

  // Bobot adaptif berdasarkan panjang output
  let wLCS = 0.5;
  let wCos = 0.3;
  let wLine = 0.2;

  if (len < 150) {
    wLCS = 0.7;
    wCos = 0.2;
    wLine = 0.1;
  } else if (len > 500) {
    wLCS = 0.4;
    wCos = 0.4;
    wLine = 0.2;
  }

  const lcs = await lcsSimilarity(expected, actual);
  const cos = await cosineSimilarity(expected, actual);
  const line = await lineSimilarity(expected, actual);

  return Math.round(lcs * wLCS + cos * wCos + line * wLine);
}

export async function runTestCasesForFile(param) {
  const { uid, language, codePath, expected, testCases, executeName } = param;

  const result = {};
  const executions = await testCases.map((tc) =>
    executeCode({
      uid,
      language,
      codePath,
      input: tc.input || "",
      executeName: `${executeName}-${tc.name}`,
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

    console.log(
      `Score: ${similarity}\nLen: ${expectedOutput.length}\nExpected: ${expectedOutput}\nActual: ${actualOutput}`
    );

    result[`TC>${tc.name}`] = pass;
    result[`TC_WEIGHT>${tc.name}`] = tc.weight || 1;
  }

  return result;
}
