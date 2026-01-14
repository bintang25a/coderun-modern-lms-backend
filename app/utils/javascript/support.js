import fs from "fs";
import path from "path";
import pty from "node-pty";

const BASE_DIR = process.cwd();

export const extendSchema = async (schemaSet, counter) => {
  Object.keys(counter || {}).forEach((k) => schemaSet.add(k));
};

export const escapeCSV = (value, delimiter) => {
  const str = String(value);

  if (str.includes(delimiter)) {
    return `${str.replace(/,/g, "comma")}`;
  }

  if (str.includes('"')) {
    return `${str.replace(/"/g, "double_quotes")}`;
  }

  return str;
};

export const normalizeCounter = async (counter) => {
  return JSON.stringify(
    Object.keys(counter)
      .sort()
      .reduce((acc, key) => {
        acc[key] = counter[key];
        return acc;
      }, {})
  );
};

export const parseCode = async (parser, filePath) => {
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
};

export const executeCode = async (param) => {
  const {
    uid,
    language,
    codePath,
    input = "",
    timeLimit = 5000,
    executeName = "sandbox",
  } = param;

  if (!language || !codePath || !uid) {
    return {
      success: false,
      message: "Running code failed, field cannot be empty",
    };
  }

  const normalizedPath = codePath.replace(/\\/g, "/");
  const absoluteCodePath = path.resolve(BASE_DIR, normalizedPath);
  const tempDir = path.resolve(BASE_DIR, "temp", uid);

  if (!fs.existsSync(absoluteCodePath)) {
    return {
      success: false,
      message: "Running code failed, Source code not found",
    };
  }

  const codeContent = fs.readFileSync(absoluteCodePath, "utf8");
  if (!codeContent.trim()) {
    return {
      success: false,
      message: "Running code failed, Source code is empty",
    };
  }

  fs.mkdirSync(tempDir, { recursive: true });

  let filename, command;

  switch (language) {
    case "c":
      filename = `${executeName}.c`;
      command = `gcc ${filename} -o ${executeName} && stdbuf -i0 -o0 -e0 ./${executeName}`;
      break;

    case "cpp":
      filename = `${executeName}.cpp`;
      command = `g++ ${filename} -o ${executeName} && stdbuf -i0 -o0 -e0 ./${executeName}`;
      break;

    case "java": {
      const className = executeName;
      filename = `${className}.java`;

      let javaCode = codeContent;

      if (/public\s+class\s+\w+/.test(javaCode)) {
        javaCode = javaCode.replace(
          /public\s+class\s+\w+/,
          `public class ${className}`
        );
      } else {
        javaCode = `public class ${className} {\n${javaCode}\n}`;
      }

      fs.writeFileSync(path.join(tempDir, filename), javaCode);
      command = `javac ${filename} && java -Dsun.stdout.buffered=false ${className}`;
      break;
    }

    case "python":
      filename = `${executeName}.py`;
      command = `python3 -u ${filename}`;
      break;

    default:
      return {
        success: false,
        message: "Running code failed, Unsupported language",
      };
  }

  if (language !== "java") {
    fs.writeFileSync(path.join(tempDir, filename), codeContent);
  }

  return new Promise((resolve) => {
    const ptyProcess = pty.spawn("bash", ["-c", command], {
      cwd: tempDir,
      env: process.env,
      name: "xterm-color",
      cols: 80,
      rows: 30,
    });

    let output = "";
    let finished = false;

    const cleanOutput = (s) =>
      s
        .replace(/\x1B\[[0-9;]*m/g, "")
        .replace(/\r\n/g, "\n")
        .trim();

    const inputQueue = input ? input.trim().split(/\s+/) : [];
    let idx = 0;

    const inputDelay =
      language !== "java" ? 0 : Math.min(Math.max(timeLimit / 100, 50), 150);

    ptyProcess.onData((data) => {
      output += data;

      setTimeout(() => {
        if (idx < inputQueue.length && !finished) {
          ptyProcess.write(`${inputQueue[idx]}\r`);
          idx++;
        }
      }, inputDelay);
    });

    const timer = setTimeout(() => {
      if (finished) return;
      finished = true;

      ptyProcess.kill();

      resolve({
        success: false,
        message: "Running code failed, Execution timed out",
        output: cleanOutput(output),
      });
    }, Math.min(Number(timeLimit), 20000));

    ptyProcess.onExit(() => {
      if (finished) return;
      finished = true;
      clearTimeout(timer);

      resolve({
        success: true,
        message: "Running code successfully",
        output: cleanOutput(output),
      });
    });
  });
};
