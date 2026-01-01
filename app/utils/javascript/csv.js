import fs from "fs";

export function writeCSV(filePath, header, rows, delimiter = " ") {
  const sanitizedHeader = header.map((h) => (h === "," ? "coma" : h));

  const content = [
    sanitizedHeader.join(delimiter),
    ...rows.map((r) => r.join(delimiter)),
  ].join("\n");

  fs.writeFileSync(filePath, content);
}
