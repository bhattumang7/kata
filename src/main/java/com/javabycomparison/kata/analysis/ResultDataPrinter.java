package com.javabycomparison.kata.analysis;

import java.util.Collections;

public class ResultDataPrinter {

  private static String getLanguage(ResultData data) {
    String language;
    if (data.type == Language.JAVA.getNumVal()) {
      language = "Java";
    } else if (data.type == Language.PYTHON.getNumVal()) {
      language = "Python";
    } else {
      language = "other";
    }
    return language;
  }

  public String print(ResultData data) {
    String language;
    language = getLanguage(data);
    return data.name
        + "\t"
        + language
        + "\t"
        + data.L
        + "\t"
        + data.LOC
        + "\t"
        + data.commentLOC
        + "\t"
        + data.numMethod
        + "\t"
        + data.nImports;
  }

  public String printFileName(ResultData data, int length) {
    return String.join("", Collections.nCopies(Math.max(length - data.name.length(), 0), " "))
        + data.name;
  }

  public String printLanguage(ResultData data, int length) {
    String language = getLanguage(data);
    return String.join("", Collections.nCopies(Math.max(length - language.length(), 0), " "))
        + language;
  }

  public String printLOC(ResultData data, int length) {
    return String.join(
            "", Collections.nCopies(Math.max(length - String.valueOf(data.LOC).length(), 0), " "))
        + data.LOC;
  }

  public String printCommentLOC(ResultData data, int length) {
    return String.join(
            "",
            Collections.nCopies(
                Math.max(length - String.valueOf(data.commentLOC).length(), 0), " "))
        + data.commentLOC;
  }

  public String printNumMethodLOC(ResultData data, int length) {
    return String.join(
            "",
            Collections.nCopies(Math.max(length - String.valueOf(data.numMethod).length(), 0), " "))
        + data.numMethod;
  }

  public String printNImportsLOC(ResultData data, int length) {
    return String.join(
            "",
            Collections.nCopies(Math.max(length - String.valueOf(data.nImports).length(), 0), " "))
        + data.nImports;
  }
}
