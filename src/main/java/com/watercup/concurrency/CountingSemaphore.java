package com.watercup.concurrency;

public class CountingSemaphore {

    int usedPermits = 0;
    int maxCount;

    public CountingSemaphore(int maxCount) {
        this.maxCount = maxCount;
    }

    public synchronized void acquire() throws InterruptedException {
        while(usedPermits == maxCount) {
            wait();
        }

        usedPermits++;
        System.out.println("acquire: " + usedPermits);
        notify();
    }

    public synchronized void release() throws InterruptedException {
        while (usedPermits == 0) {
            wait();
        }

        System.out.println("release: " + usedPermits);
        usedPermits--;
        notify();
    }
}
