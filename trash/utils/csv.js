import fs from "fs";

function escapeCSV(value, delimiter) {
  const str = String(value);

  if (str.includes(delimiter)) {
    return `${str.replace(/,/g, "comma")}`;
  }

  if (str.includes('"')) {
    return `${str.replace(/"/g, "double_quotes")}`;
  }

  return str;
}

export async function writeCSV(filePath, header, rows, delimiter = ",") {
  const content = [
    header.map((h) => escapeCSV(h, delimiter)).join(delimiter),
    ...rows.map((row) =>
      row.map((cell) => escapeCSV(cell, delimiter)).join(delimiter)
    ),
  ].join("\n");

  fs.writeFileSync(filePath, content);
}
