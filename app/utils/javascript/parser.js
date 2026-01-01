import fs from "fs";

export function parseCode(parser, filePath) {
  if (!fs.existsSync(filePath)) return null;

  const code = fs.readFileSync(filePath, "utf8");
  const tree = parser.parse(code);

  const counter = {};

  function countNode(node) {
    counter[node.type] = (counter[node.type] || 0) + 1;
    for (let i = 0; i < node.childCount; i++) {
      countNode(node.child(i));
    }
  }

  countNode(tree.rootNode);

  return counter;
}
