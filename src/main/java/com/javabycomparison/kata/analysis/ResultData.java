package com.javabycomparison.kata.analysis;

import java.util.StringJoiner;

public class ResultData {

  public int type;
  public String name;
  public int L;
  public int LOC;
  public int commentLOC;
  public int numMethod;
  public int nImports;

  public ResultData(int type, String name, int LOC, int commentLOC, int numMethod, int nImports) {
    this.type = type;
    this.name = name.replaceAll("\\\\", "/");
    this.LOC = LOC;
    this.commentLOC = commentLOC;
    this.numMethod = numMethod;
    this.nImports = nImports;
  }

  public ResultData() {}

  public boolean areInstanceMemberSame(ResultData otherObject) {
    return type == otherObject.type
        && L == otherObject.L
        && LOC == otherObject.LOC
        && commentLOC == otherObject.commentLOC
        && numMethod == otherObject.numMethod
        && nImports == otherObject.nImports
        && name.equals(otherObject.name);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResultData that = (ResultData) o;
    return this.areInstanceMemberSame(that);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ResultData.class.getSimpleName() + "[", "]")
        .add("type=" + type)
        .add("name='" + name + "'")
        .add("L=" + L)
        .add("LOC=" + LOC)
        .add("commentLOC=" + commentLOC)
        .add("numMethod=" + numMethod)
        .add("nImports=" + nImports)
        .toString();
  }
}
