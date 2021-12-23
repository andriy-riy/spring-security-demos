package com.rio;

import java.util.ArrayList;
import java.util.List;

public class RandomGenerator {

  public List<Long> generateNRandomNumbers(int n) {
    List<Long> randomNumbers = new ArrayList<>(n);
    Context context = SharedMapContextStorage.getContext();

    for (int i = 0; i < n; i++) {
      long rand = context.getMinNumber() + (long) (Math.random() * ((context.getMaxNumber() - context.getMinNumber()) + 1));
      randomNumbers.add(rand);
    }

    return randomNumbers;
  }
}
