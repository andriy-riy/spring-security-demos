package com.rio;

import java.util.List;

public class MyRunnable implements Runnable {

  private final RandomGenerator randomGenerator;

  public MyRunnable(RandomGenerator randomGenerator) {
    this.randomGenerator = randomGenerator;
  }

  @Override
  public void run() {
    // Some stupid logic for set context
    long max = Thread.currentThread().getId() % 2 == 0 ? 1000 : 0;
    long min = max - 1000;
    Context context = new Context(Thread.currentThread().getName(), max, min);

    SharedMapContextStorage.setContext(context);

    List<Long> randoms = randomGenerator.generateNRandomNumbers(10);

    randoms.forEach(random -> System.out.println(context.threadName() + " generated number: " + random));
  }
}
