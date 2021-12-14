package com.rio;

public class ThreadLocalContextStorage {

  private static final ThreadLocal<Context> contextHolder = new ThreadLocal<>();

  public static void setContext(Context context) {
    contextHolder.set(context);
  }

  public static Context getContext() {
    return contextHolder.get();
  }
}
