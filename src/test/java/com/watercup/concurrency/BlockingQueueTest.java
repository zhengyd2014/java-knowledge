package com.watercup.concurrency;


import org.junit.Test;

public class BlockingQueueTest {


    @Test
    public void TestBlockingQueue() throws InterruptedException {

        BlockingQueue blockingQueue = new BlockingQueue(5);

        Thread p1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 50; i++) {
                        blockingQueue.enqueue(new Integer(i));
                        System.out.println("enqueued item #" + i);
                    }
                } catch (InterruptedException ex) {

                }
            }
        });

        Thread c1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 25; i++) {
                        Integer item = (Integer)blockingQueue.dequeue();
                        System.out.println("consumer 1 dequeue item #" + item);
                    }
                } catch (InterruptedException ex) {

                }
            }
        });

        Thread c2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 25; i++) {
                        Integer item = (Integer)blockingQueue.dequeue();
                        System.out.println("consumer 2 dequeue item #" + item);
                    }
                } catch (InterruptedException ex) {

                }
            }
        });

        p1.start();
        Thread.sleep(4000);

        c1.start();
        c2.start();
        p1.join();
        c1.join();
        c2.join();
    }
}
