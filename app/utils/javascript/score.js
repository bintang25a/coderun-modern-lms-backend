export function plagiarismChecker(key, answer) {
  let score = 0;
  let total = 0;

  for (const k of Object.keys(key)) {
    score += Math.min(key[k], answer[k] || 0);
    total += key[k];
  }

  return Math.round((score / total) * 100);
}

export function SBCAM(key, answer) {
  let Tf = 0;
  let Sf = 0;

  for (const k of Object.keys(key)) {
    Tf += key[k];
    Sf += answer[k] || 0;
  }

  if (Tf === 0) return 0;

  const Fs = Math.max(0, Math.min(1, 1 - Math.abs(Tf - Sf) / Tf));

  return Math.round(Fs * 100);
}

export function SEDM(answer) {
  const TOTAL_LINES = answer.TOTAL_LINES || 0;
  if (TOTAL_LINES <= 0) return 0;

  let totalErrors = 0;

  for (const key of Object.keys(answer)) {
    if (key.includes("ERROR")) {
      totalErrors += answer[key] || 0;
    }
  }

  const errorPercentage = totalErrors / TOTAL_LINES;

  const score = Math.max((1 - errorPercentage) * 100, 0);

  return Math.round(score);
}

export function STCAM(answer) {
  let weightedPassSum = 0;
  let totalWeight = 0;

  for (const key of Object.keys(answer)) {
    if (key.startsWith("TC>")) {
      const testCaseName = key.replace("TC>", "");
      const pass = answer[key] ? 1 : 0;

      const weightKey = `TC_WEIGHT>${testCaseName}`;
      const weight = answer[weightKey] || 1;

      weightedPassSum += weight * pass;
      totalWeight += weight;
    }
  }

  if (totalWeight === 0) return 0;

  return Math.round((weightedPassSum / totalWeight) * 100);
}

export function calculateScore(key, answer, testCaseResult) {
  const score = {
    score1: SBCAM(key, answer),
    score2: SEDM(answer),
    score3: STCAM(testCaseResult),
  };

  return (score.score1 + score.score1 + score.score1) / 3;
}
