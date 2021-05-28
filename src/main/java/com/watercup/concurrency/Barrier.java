package com.watercup.concurrency;

public class Barrier {

    int count = 0;
    int totalThreads;
    int released = 0;

    public Barrier(int totalThreads) {
        this.totalThreads = totalThreads;
    }

    public synchronized void await() throws InterruptedException {

        // block any new threads from proceeding till,
        // all threads from previous barrier are released
        while(count == totalThreads) {
            wait();
        }

        // increment the counter whenever a thread arrives at the
        // barrier.
        count++;
        if (count == totalThreads) {
            notifyAll();
            // remember to set released to totalThreads
            released = totalThreads;
        } else {
            // wait till all threads reach barrier
            while (count < totalThreads) {
                wait();
            }
        }

        released--;
        if (released == 0) {
            count = 0;
            // remember to wakeup any threads
            // waiting on line #16
            notifyAll();
        }
    }
}
