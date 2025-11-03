import fs from "fs";
import path from "path";
import Parser from "tree-sitter";
import C from "tree-sitter-c";
import CPP from "tree-sitter-cpp";
import Python from "tree-sitter-python";
import Java from "tree-sitter-java";

const labelling = (req, res) => {
  // const { assignment_number } = req.body;
  const assignment_number = "CS25C3-01";

  const countNodeTypes = (node, counter) => {
    const type = node.type;
    counter[type] = (counter[type] || 0) + 1;

    for (let i = 0; i < node.childCount; i++) {
      countNodeTypes(node.child(i), counter);
    }
  };

  const parseFile = async (filePath) => {
    const ext = path.extname(filePath);
    const parser = new Parser();

    if (ext === ".c") {
      parser.setLanguage(C);
    } else if (ext === ".cpp") {
      parser.setLanguage(CPP);
    } else if (ext === ".java") {
      parser.setLanguage(Java);
    } else if (ext === ".python") {
      parser.setLanguage(Python);
    } else throw new Error(`Unsupported file extension: ${ext}`);

    const code = fs.readFileSync(filePath, "utf8");
    const tree = parser.parse(code);

    const counter = {};
    countNodeTypes(tree.rootNode, counter);

    return {
      submission_number: "220407",
      label_count: counter,
    };
  };

  const main = async () => {
    const basePath = path.resolve("public");
    const inputDir = path.join(basePath, "classrooms", assignment_number);
    const outputDir = path.join(inputDir, "Output");

    if (!fs.existsSync(outputDir)) fs.mkdirSync(outputDir);

    const files = fs
      .readdirSync(inputDir)
      .filter((f) => /\.(c|cpp|java|python)$/.test(f));

    for (const file of files) {
      const filePath = path.join(inputDir, file);
      console.log(`Parsing ${filePath} ...`);
      const jsonTree = await parseFile(filePath);
      const outPath = path.join(outputDir, `${file}.json`);
      fs.writeFileSync(outPath, JSON.stringify(jsonTree, null, 2));
    }

    console.log("✅ Parsing selesai! File JSON tersimpan di folder output/");
  };

  main().catch(console.error);
};

labelling();

// function nodeToJSON(node) {
//   const result = {
//     type: node.type,
//     startPosition: node.startPosition,
//     endPosition: node.endPosition,
//   };

//   if (node.childCount > 0) {
//     result.children = [];
//     for (let i = 0; i < node.childCount; i++) {
//       result.children.push(nodeToJSON(node.child(i)));
//     }
//   } else {
//     result.text = node.text;
//   }
//   return result;
// }
