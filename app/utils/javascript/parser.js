import fs from "fs";

export function parseCode(parser, filePath) {
  if (!fs.existsSync(filePath)) return null;

  const code = fs.readFileSync(filePath, "utf8");
  const tree = parser.parse(code);

  const counter = {};

  function walk(node, path = []) {
    const type = node.type;

    if (type.includes("comment")) return;

    const newPath = [...path, type];

    if (newPath.length >= 2) {
      const pathKey = newPath.slice(-2).join(">");
      counter[pathKey] = (counter[pathKey] || 0) + 1;
    }

    for (let i = 0; i < node.childCount; i++) {
      walk(node.child(i), newPath);
    }
  }

  walk(tree.rootNode);

  const totalLines =
    tree.rootNode.endPosition.row - tree.rootNode.startPosition.row + 1;

  counter["TOTAL_LINES"] = totalLines;

  return counter;
}
