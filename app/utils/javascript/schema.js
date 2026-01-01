export function extendSchema(schemaSet, counter) {
  Object.keys(counter || {}).forEach((k) => schemaSet.add(k));
}

export function finalizeHeader(schemaSet) {
  return ["row_id", "score", ...Array.from(schemaSet).sort()];
}

export function normalizeCounter(counter) {
  return JSON.stringify(
    Object.keys(counter)
      .sort()
      .reduce((acc, key) => {
        acc[key] = counter[key];
        return acc;
      }, {})
  );
}

export function deduplicateDataset(datasetRows) {
  const seen = new Map();
  const result = [];

  for (const row of datasetRows) {
    if (row.row_id === "key") {
      result.push(row);
      continue;
    }

    const signature = normalizeCounter(row.counter);

    if (!seen.has(signature)) {
      seen.set(signature, row);
      result.push(row);
    }
  }

  return result;
  return Array.from(seen.values());
}
