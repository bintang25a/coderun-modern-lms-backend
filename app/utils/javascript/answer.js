import path from "path";
import { parseCode } from "./parser.js";
import { extendSchema } from "./schema.js";

const BASE_DIR = process.cwd();

export function buildAnswer({ assignment, parser, schemaSet }) {
  const rows = [];

  for (const s of assignment.submissions) {
    if (s.grade != null) continue;

    const filePath = path.join(BASE_DIR, s.answer).replace(/\\/g, "/");
    const counter = parseCode(parser, filePath);
    if (!counter) continue;

    extendSchema(schemaSet, counter);

    rows.push({
      row_id: s.submission_number,
      score: 0,
      validation: "not-set",
      counter,
    });
  }

  return rows;
}
