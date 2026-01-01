export function calculateScore(key, answer) {
  let score = 0;
  let total = 0;

  for (const k of Object.keys(key)) {
    score += Math.min(key[k], answer[k] || 0);
    total += key[k];
  }

  return Math.round((score / total) * 100);
}
