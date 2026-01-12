export async function scaling({ stcamScore, sbcamScore }) {
  const THRESHOLD_STCAM = 30;
  const THRESHOLD_SBCAM = 70;

  const isStcamHigh = stcamScore >= THRESHOLD_STCAM;
  const isSbcamHigh = sbcamScore >= THRESHOLD_SBCAM;

  let scale = "";
  if (isSbcamHigh && isStcamHigh) {
    scale = "high";
  } else if (isStcamHigh) {
    scale = "medium";
  } else {
    scale = "low";
  }

  return scale;
}

export async function scoring({ stcamScore, sbcamScore, sedmScore }) {
  const score = Math.round((sbcamScore * 2 + stcamScore + sedmScore) / 4);

  return score;
}
