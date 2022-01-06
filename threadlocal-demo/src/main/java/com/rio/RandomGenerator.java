package com.rio;

import java.util.ArrayList;
import java.util.List;

public class RandomGenerator {

  public List<Long> generateNRandomNumbers(int n) {
    List<Long> randomNumbers = new ArrayList<>(n);
    Context context = SharedMapContextStorage.getContext();

    for (int i = 0; i < n; i++) {
      long rand = context.minNumber() + (long) (Math.random() * ((context.maxNumber() - context.minNumber()) + 1));
      randomNumbers.add(rand);
    }

    return randomNumbers;
  }
}
