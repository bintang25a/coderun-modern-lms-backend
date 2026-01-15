import fs from "fs";
import path from "path";
import Parser from "tree-sitter";
import C from "tree-sitter-c";

const parser = new Parser();
parser.setLanguage(C);

/* =======================
   AST PARSER
======================= */
async function parseCode(filePath) {
  if (!fs.existsSync(filePath)) return null;

  const code = fs.readFileSync(filePath, "utf8");
  const tree = parser.parse(code);
  const counter = {};

  function walk(node, path = []) {
    const type = node.type;
    if (type.includes("comment")) return;

    const newPath = [...path, type];
    if (newPath.length >= 2) {
      const key = newPath.slice(-2).join(">");
      counter[key] = (counter[key] || 0) + 1;
    }

    for (let i = 0; i < node.childCount; i++) {
      walk(node.child(i), newPath);
    }
  }

  walk(tree.rootNode);

  counter["TOTAL_LINES"] =
    tree.rootNode.endPosition.row - tree.rootNode.startPosition.row + 1;

  return counter;
}

/* =======================
   LOAD & BUILD DATA
======================= */
function loadFiles(dir) {
  return fs
    .readdirSync(dir)
    .filter((f) => f.endsWith(".c"))
    .map((f) => path.join(dir, f));
}

async function buildCSV(inputDir, label, output) {
  const rows = [];
  const featureSet = new Set();

  for (const file of loadFiles(inputDir)) {
    const ast = await parseCode(file);
    if (!ast) continue;

    Object.keys(ast).forEach((k) => featureSet.add(k));
    rows.push({ file, label, ast });
  }

  const features = Array.from(featureSet);

  let csv = ["id,score," + features.join(",")];

  rows.forEach((r, i) => {
    const values = features.map((f) => r.ast[f] || 0);
    csv.push([i, r.label, ...values].join(","));
  });

  fs.writeFileSync(output, csv.join("\n"));
}

/* =======================
   RUN
======================= */
(async () => {
  await buildCSV("./code/key", 100, "./data/dataset.csv");
  await buildCSV("./code/jawaban", 0, "./data/jawaban.csv");

  console.log("CSV berhasil dibuat.");
})();
