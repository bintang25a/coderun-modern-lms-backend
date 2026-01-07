import { executeCode } from "./execute.js";

export async function runTestCasesForFile({
  uid,
  language,
  codePath,
  expected,
  testCases,
}) {
  const result = {};

  let i = 0;
  for (const tc of testCases) {
    const actual = await executeCode({
      uid: `${uid}_student`,
      language,
      codePath,
      input: tc.input || "",
    });

    const pass = expected[tc.name] === actual.output;
    console.log(`Expected: ${expected[tc.name]}, Actual: ${actual.message}`);

    result[`TC>${tc.name}`] = pass;
    result[`TC_WEIGHT>${tc.name}`] = tc.weight || 1;
  }

  return result;
}
