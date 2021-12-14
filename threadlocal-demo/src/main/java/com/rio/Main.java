package com.rio;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    Runnable myRunnable = new MyRunnable(new RandomGenerator());

    Thread thread1 = new Thread(myRunnable);
    Thread thread2 = new Thread(myRunnable);

    thread1.start();
    thread2.start();

    thread1.join(); //wait for thread 1 to terminate
    thread2.join(); //wait for thread 2 to terminate
  }
}
