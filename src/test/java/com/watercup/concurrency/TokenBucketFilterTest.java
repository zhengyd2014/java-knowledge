package com.watercup.concurrency;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TokenBucketFilterTest {

    @Test
    public void TestTokenBucketFilter() throws InterruptedException {
        Set<Thread> allThreads = new HashSet<>();
        TokenBucketFilter tokenBucketFilter = new TokenBucketFilter(5);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        tokenBucketFilter.getToken();
                    } catch (InterruptedException ex) {

                    }
                }
            });

            thread.setName("Thread_" + i);
            allThreads.add(thread);
        }

        Thread.sleep(3000);
        for (Thread t : allThreads) t.start();
        for (Thread t : allThreads) t.join();
    }
}
