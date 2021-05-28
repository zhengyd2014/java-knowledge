package com.watercup.concurrency;

import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DeferredCallbackExecutorTest {

    @Test
    public void testDeferredCallbackExecutor() throws InterruptedException{
        //DeferredCallbackExecutor executor = new DeferredCallbackExecutor();
        DeferredCallbackExecutorWithSynchronized executor = new DeferredCallbackExecutorWithSynchronized();

        Thread exeutorThread = new Thread(executor);
        exeutorThread.start();

        Set<Thread> allThreads = new HashSet<>();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Callback cb = new Callback(random.nextInt(10), "Hello this is " + Thread.currentThread().getName());
                    executor.registerCallback(cb);
                }
            });

            thread.setName("Thread_" + i);
            thread.start();
            allThreads.add(thread);
            Thread.sleep(100);
        }

        Thread.sleep(10000);
        for (Thread thread : allThreads) thread.join();

    }
}
