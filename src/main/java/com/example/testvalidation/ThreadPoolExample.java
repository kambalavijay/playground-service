package com.example.testvalidation;

import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadPoolExample {
  public static void main(String[] args) {
    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
    Set<String> threadNames = new HashSet<>();
    for (int i = 1; i <= 100; i++) {
      Task task = new Task("Task " + i, threadNames);
      threadPoolExecutor.execute(task);
    }
    shutdownAndAwaitTermination(threadPoolExecutor);
    System.out.println(threadNames.size());
  }
  static void shutdownAndAwaitTermination(ExecutorService pool) {
    // Disable new tasks from being submitted
    pool.shutdown();
    try {
      // Wait a while for existing tasks to terminate
      if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
        // Cancel currently executing tasks forcefully
        pool.shutdownNow();
        // Wait a while for tasks to respond to being cancelled
        if (!pool.awaitTermination(60, TimeUnit.SECONDS))
          System.err.println("Pool did not terminate");
      }
    } catch (InterruptedException ex) {
      // (Re-)Cancel if current thread also interrupted
      pool.shutdownNow();
      // Preserve interrupt status
      Thread.currentThread().interrupt();
    }
  }
}


class Task implements Runnable {
  private final String name;
  private final Set<String> threadNames;

  public Task(String name, Set<String> threadNames) {
    this.name = name;
    this.threadNames = threadNames;
  }

  @SneakyThrows
  @Override
  public void run() {
    //Thread.sleep(1000l);
    threadNames.add(Thread.currentThread().getName());
    //System.out.println("Task [" + name + "] executed on : " + LocalDateTime.now() + " --> " + Thread.currentThread().getName());
  }
}