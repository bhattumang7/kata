package com.javabycomparison.kata.analysis;

enum Language {
  JAVA(0),
  PYTHON(1),
  OTHER(2);

  private int numVal;

  Language(int numVal) {
    this.numVal = numVal;
  }

  public int getNumVal() {
    return numVal;
  }
}
