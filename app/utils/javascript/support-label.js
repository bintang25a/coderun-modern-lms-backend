export const scaling = async (param) => {
  const { stcamScore, sbcamScore } = param;

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
};

export const scoring = async (param) => {
  const { stcamScore, sbcamScore, sedmScore } = param;

  const score = Math.round((sbcamScore * 2 + stcamScore + sedmScore) / 4);

  return score;
};
