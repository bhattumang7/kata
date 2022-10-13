package com.javabycomparison.kata.analysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JavaAnalyzer implements Analyzer {

  private final Path file;

  public JavaAnalyzer(Path file) {
    this.file = file;
  }

  @Override
  public ResultData analyze() throws IOException {
    if (file != null) {
      int imports = 0;
      int lineOfCode = 0;
      int commentsLoC = 0;

      try {
        BufferedReader reader = Files.newBufferedReader(this.file);

        String line;
        while ((line = reader.readLine()) != null) {
          lineOfCode += 1;
          if (isAnImportLine(line)) {
            imports += 1;
          } else if (isCommentLine(line)) {
            commentsLoC += 1;
          }
        }
        // It is impossible to detect the number of methods at the moment.
        return new ResultData(0, this.file.toString(), lineOfCode, commentsLoC, 0, imports);
      } catch (IOException ioe) {
        throw new IOException("There was a problem reading a file!");
      }
    } else {
      return null;
    }
  }

  private static boolean isAnImportLine(String line) {
    return line.trim().startsWith("import");
  }

  private static boolean isCommentLine(String line) {
    return line.trim().startsWith("//")
        || line.trim().startsWith("*")
        || line.trim().startsWith("/*");
  }
}
