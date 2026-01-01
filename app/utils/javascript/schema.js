export function extendSchema(schemaSet, counter) {
  Object.keys(counter || {}).forEach((k) => schemaSet.add(k));
}

export function finalizeHeader(schemaSet) {
  return ["row_id", "score", ...Array.from(schemaSet).sort()];
}
