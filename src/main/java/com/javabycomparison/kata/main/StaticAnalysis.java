package com.javabycomparison.kata.main;

import com.javabycomparison.kata.analysis.ResultData;
import com.javabycomparison.kata.analysis.ResultDataPrinter;
import com.javabycomparison.kata.printing.CSVPrinter;
import com.javabycomparison.kata.printing.ResultPrinter;
import com.javabycomparison.kata.search.SearchClient;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedList;

public class StaticAnalysis {

  public static void main(String... args) {
    analyze(args.length == 0 ? null : args[0], args.length == 2 ? args[1].equals("smry") : false);
  }

  private static void analyze(String path, boolean smry) {
    StaticAnalysis analyzer = new StaticAnalysis();
    ResultData[] overallResult = analyzer.run(getDefaultIfNull(path), smry);

    if (overallResult == null) {
      System.err.println("overall result is null from static analysis");
    }

    ResultPrinter.printOverallResults(overallResult);
    try {
      new CSVPrinter("output.csv").writeCSV(overallResult);
    } catch (IOException e) {
      System.err.println("Something went a bit wrong");
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      String exceptionAsString = sw.toString();
      System.err.println(e.getMessage() + " " + exceptionAsString);
    }
  }

  private static String getDefaultIfNull(String path) {
    return path == null ? "./src/" : path;
  }

  private ResultData[] run(String directoryPath, boolean smry) {
    LinkedList<ResultData> results = new SearchClient(smry).collectAllFiles(directoryPath);
    if (results != null) {
      if (results.size() != 0) {
        int javaLOC = 0;
        int javaCommentLOC = 0;
        int javaNumMethod = 0;
        int javanImports = 0;

        int pyLOC = 0;
        int pyCommentLOC = 0;
        int pyNumMethod = 0;
        int pynImports = 0;

        int LOC = 0;
        int commentLOC = 0;
        int numMethod = 0;
        int nImports = 0;

        for (int l = 0; l < results.size(); l = l + 1) {
          ResultData resultData = results.get(l);
          if (!smry) {
            System.out.println(new ResultDataPrinter().print(resultData));
          }
          if (resultData.type == 0) {
            javaLOC += resultData.LOC;
            javaCommentLOC += resultData.commentLOC;
            javaNumMethod += resultData.numMethod;
            javanImports += resultData.nImports;
          } else if (resultData.type == 1) {
            pyLOC += resultData.LOC;
            pyCommentLOC += resultData.commentLOC;
            pyNumMethod += resultData.numMethod;
            pynImports += resultData.nImports;
          } else {
            LOC += resultData.LOC;
            commentLOC += resultData.commentLOC;
            numMethod += resultData.numMethod;
            nImports += resultData.nImports;
          }
        }
        return new ResultData[] {
          new ResultData(0, "Overall Java", javaLOC, javaCommentLOC, javaNumMethod, javanImports),
          new ResultData(1, "Overall Python", pyLOC, pyCommentLOC, pyNumMethod, pynImports),
          new ResultData(2, "Overall Other", LOC, commentLOC, numMethod, nImports),
        };
      } else {
        return new ResultData[] {new ResultData()};
      }
    }
    System.err.println("There was a problem with the result!");

    return null;
  }
}
