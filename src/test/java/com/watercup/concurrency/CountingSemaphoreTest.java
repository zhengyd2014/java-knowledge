package com.watercup.concurrency;

import org.junit.Test;

public class CountingSemaphoreTest {

    @Test
    public void testCountSemaphore() throws InterruptedException {

        CountingSemaphore semaphore = new CountingSemaphore(1);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        semaphore.acquire();
                        System.out.println("ping " + i);
                    }
                } catch (InterruptedException ex) {

                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        semaphore.release();
                        System.out.println("pong " + i);
                    }
                } catch (InterruptedException ex) {

                }
            }
        });

        t2.start();
        t1.start();



        t1.join();
        t2.join();
    }
}
