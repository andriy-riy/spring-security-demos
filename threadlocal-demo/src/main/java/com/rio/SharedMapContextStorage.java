package com.rio;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SharedMapContextStorage {

  public static final Map<Long, Context> contextPerThread = new ConcurrentHashMap<>();

  public static void setContext(Context context) {
    long threadId = Thread.currentThread().getId();
    contextPerThread.put(threadId, context);
  }

  public static Context getContext() {
    long threadId = Thread.currentThread().getId();

    return contextPerThread.get(threadId);
  }
}
