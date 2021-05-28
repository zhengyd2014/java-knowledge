package com.watercup.concurrency.jenkov;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue<T> {

    private List<T> queue = new LinkedList<>();
    private int limit = 0;

    public BlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while (queue.size() == limit) {
            wait();
        }

        queue.add(item);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while(queue.size() == 0) {
            wait();
        }

        T obj = queue.remove(0);
        notifyAll();
        return obj;
    }
}
