import fs from "fs";

function escapeCSV(value, delimiter) {
  const str = String(value);

  if (str.includes(delimiter)) {
    return `${str.replace(/,/g, "coma")}`;
  }

  return str;
}

export function writeCSV(filePath, header, rows, delimiter = ",") {
  const content = [
    header.map((h) => escapeCSV(h, delimiter)).join(delimiter),
    ...rows.map((row) =>
      row.map((cell) => escapeCSV(cell, delimiter)).join(delimiter)
    ),
  ].join("\n");

  fs.writeFileSync(filePath, content);
}

export function writeCSV1(filePath, header, rows, delimiter = ",") {
  const sanitizedHeader1 = header.map((h) => (h === "," ? "coma" : h));
  const sanitizedHeader = header.map((header) =>
    header.map((h) => (h.includes(",") ? "coma" : h))
  );

  const content = [
    sanitizedHeader.join(delimiter),
    ...rows.map((r) => r.join(delimiter)),
  ].join("\n");

  fs.writeFileSync(filePath, content);
}
