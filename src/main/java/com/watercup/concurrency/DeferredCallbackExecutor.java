package com.watercup.concurrency;

import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DeferredCallbackExecutor implements Runnable{

    PriorityQueue<Callback> q = new PriorityQueue<Callback>(
            (c1, c2) -> Long.compare(c1.executeAtInMs, c2.executeAtInMs));

    ReentrantLock lock = new ReentrantLock();
    Condition newCallbackInQueue = lock.newCondition();

    public void registerCallback(Callback callback) {
        lock.lock();
        q.offer(callback);
        System.out.println("register callback: " + callback.executeAtInMs + ", " + callback.message);
        newCallbackInQueue.signal();
        lock.unlock();
    }

    public void run() {
        while (true) {
            try {
                lock.lock();
                while (q.size() == 0) {
                    newCallbackInQueue.await();
                }

                while (q.size() != 0) {
                    System.out.println("top callback: " + q.peek().executeAtInMs + ", " + q.peek().message);
                    long sleepFor = q.peek().executeAtInMs - System.currentTimeMillis();
                    if (sleepFor <= 0) break;

                    System.out.println("sleep for: " + sleepFor + " ms");
                    newCallbackInQueue.await(sleepFor, TimeUnit.MILLISECONDS);
                }

                Callback cb = q.poll();
                System.out.println("Executed at " + System.currentTimeMillis() + " required at " + cb.executeAtInMs
                        + ": message:" + cb.message);

            } catch (InterruptedException ex) {

            }
        }
    }
}

class Callback {
    long executeAtInMs;
    String message;

    public Callback(long afterInSeconds, String message) {
        this.executeAtInMs = System.currentTimeMillis() + afterInSeconds * 1000;
        this.message = message;
    }
}
