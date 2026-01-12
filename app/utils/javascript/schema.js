import fs from "fs";

export async function extendSchema(schemaSet, counter) {
  Object.keys(counter || {}).forEach((k) => schemaSet.add(k));
}

export function finalizeHeader(schemaSet) {
  return ["row_id", "score", "scale", ...Array.from(schemaSet).sort()];
}

export async function normalizeCounter(counter) {
  return JSON.stringify(
    Object.keys(counter)
      .sort()
      .reduce((acc, key) => {
        acc[key] = counter[key];
        return acc;
      }, {})
  );
}

export async function deduplicateDataset(datasetRows) {
  const seen = new Map();
  const result = [];

  for (const row of datasetRows) {
    if (row.row_id === "key") {
      result.push(row);
      continue;
    }

    const signature = await normalizeCounter(row.counter);

    if (seen.has(signature)) {
      if (row.filePath && fs.existsSync(row.filePath)) {
        fs.unlinkSync(row.filePath);
      }
      continue;
    }

    seen.set(signature, true);
    result.push(row);
  }

  return result;
}
