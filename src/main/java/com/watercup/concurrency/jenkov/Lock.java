package com.watercup.concurrency.jenkov;

public class Lock {

    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }

        isLocked = true;
    }

    public synchronized void unlock() throws InterruptedException {
        isLocked = false;
        notify();
    }
}
