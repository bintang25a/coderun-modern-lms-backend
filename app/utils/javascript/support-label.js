export const scaling = async (param) => {
  const { stcamScore, sbcamScore, sedmScore } = param;

  const THRESHOLD = 70;

  const isStcamHigh = stcamScore >= THRESHOLD;
  const isSbcamHigh = sbcamScore >= THRESHOLD;
  const isError = sedmScore != 100;

  let scale = "";
  if (isError) {
    scale = "low";
  } else if (isSbcamHigh && isStcamHigh) {
    scale = "high";
  } else if (isSbcamHigh || isStcamHigh) {
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
