package com.rio;

public class Context {

  private final String threadName;
  private final long maxNumber;
  private final long minNumber;

  public Context(String threadName, long maxNumber, long minNumber) {
    this.threadName = threadName;
    this.maxNumber = maxNumber;
    this.minNumber = minNumber;
  }

  public String getThreadName() {
    return threadName;
  }

  public long getMaxNumber() {
    return maxNumber;
  }

  public long getMinNumber() {
    return minNumber;
  }
}
