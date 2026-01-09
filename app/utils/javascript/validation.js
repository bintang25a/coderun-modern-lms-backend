export function determineTaskLabel(
  testCaseResult,
  stcamScore,
  { passRatioThreshold = 0.3, stcamThreshold = 50 } = {}
) {
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

  const isTestCasePass = passRatio >= passRatioThreshold;
  const isStcamHigh = stcamScore >= stcamThreshold;

  let validation = "";
  if (isTestCasePass && isStcamHigh) {
    validation = "valid";
  } else if (isTestCasePass || isStcamHigh) {
    validation = "middle";
  } else {
    validation = "invalid";
  }

  return validation;
}
