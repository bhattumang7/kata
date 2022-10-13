package com.javabycomparison.kata.analysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PythonAnalyzer implements Analyzer {

  private final Path file;

  public PythonAnalyzer(Path file) {
    this.file = file;
  }

  private static boolean isAnImportLine(String line) {
    return line.trim().startsWith("import") || line.trim().startsWith("from");
  }

  private static boolean isAFunctionDeclarationLine(String line) {
    // In Python a method is defined with 'def'
    return line.trim().startsWith("def");
  }

  private static boolean isACommentLine(String line) {
    return line.trim().startsWith("#");
  }

  @Override
  public ResultData analyze() throws IOException {
    int number_of_imports = 0;
    int lines_of_code = 0;
    int number_of_methods = 0;
    int comment_lines_of_code = 0;

    List<String> file_contents = Files.readAllLines(this.file);
    for (String line : file_contents) {
      lines_of_code += 1;
      if (isAnImportLine(line)) {
        number_of_imports += 1;
      } else if (isACommentLine(line)) {
        comment_lines_of_code += 1;

      } else if (isAFunctionDeclarationLine(line)) {
        number_of_methods += 1;
      }
    }

    return new ResultData(
        Language.PYTHON.getNumVal(),
        this.file.toString(),
        lines_of_code,
        comment_lines_of_code,
        number_of_methods,
        number_of_imports);
  }
}
