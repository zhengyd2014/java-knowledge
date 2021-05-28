package com.watercup.concurrency;

import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DeferredCallbackExecutorWithSynchronized implements Runnable{

    PriorityQueue<Callback> q = new PriorityQueue<Callback>(
            (c1, c2) -> Long.compare(c1.executeAtInMs, c2.executeAtInMs));

    public synchronized void registerCallback(Callback callback) {
        q.offer(callback);
        System.out.println("register callback: " + callback.executeAtInMs + ", " + callback.message);
        notify();
    }

    public void run() {
        while (true) {
            synchronized (this) {
                try {
                    while (q.size() == 0) {
                        wait();
                    }

                    System.out.println("top callback: " + q.peek().executeAtInMs + ", " + q.peek().message);
                    long sleepFor = q.peek().executeAtInMs - System.currentTimeMillis();
                    if (sleepFor <= 0) {
                        Callback cb = q.poll();
                        System.out.println("Executed at " + System.currentTimeMillis() + " required at " + cb.executeAtInMs
                                + ": message:" + cb.message);
                    } else {
                        System.out.println("sleep for: " + sleepFor + " ms");
                        wait(sleepFor);
                    }
                } catch(InterruptedException ex){

                }
            }
        }
    }
}
