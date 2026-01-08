export function determineTaskLabel(testCaseResult, passRatioThreshold = 0.3) {
  let totalWeight = 0;
  let passedWeight = 0;

  for (const key in testCaseResult) {
    if (!key.startsWith("TC>")) continue;

    const tcName = key.replace("TC>", "");
    const passed = testCaseResult[key];
    const weight = testCaseResult[`TC_WEIGHT>${tcName}`] || 1;

    totalWeight += weight;
    if (passed) passedWeight += weight;
  }

  const passRatio = totalWeight === 0 ? 0 : passedWeight / totalWeight;

  return passRatio >= passRatioThreshold ? "in-task" : "out-task";
}
