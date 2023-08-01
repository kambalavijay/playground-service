package com.explore.playground;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MyTest {

    @Test
    public void test() {

        ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        service.setMaximumPoolSize(2);
        Set<String> threads = new HashSet<>();
        // Creating 5 threads using loops
        for (int i = 0; i < 2; i++) {
            service.execute(() -> {
                try {
                    Thread.sleep(1000);
                    threads.add(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        System.out.println(threads.size());
        service.shutdown();
    }
}
