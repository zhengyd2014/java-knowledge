package com.watercup.kafka;

import java.util.PriorityQueue;

public class TaskScheduler implements Runnable {

    /*
    	有一個 class TaskScheduler which implements runnable, 加上一個 api schedule(task, delay)
        實現這個 API
     */

    PriorityQueue<TaskWrapper> taskQueue =
            new PriorityQueue<>((a, b) -> Long.compare(a.executeAtMilliSecond, b.executeAtMilliSecond));

    public synchronized void schedule(Task task, long delayInSeconds) {
        taskQueue.offer(new TaskWrapper(task, delayInSeconds));
        notifyAll();
    }

    public void run() {
        while (true) {
            synchronized(this) {
                try {
                    while (taskQueue.isEmpty()) {
                        wait();
                    }

                    long sleepFor = taskQueue.peek().executeAtMilliSecond - System.currentTimeMillis();
                    if (sleepFor <= 0) {
                        TaskWrapper taskWrapper = taskQueue.poll();
                        taskWrapper.task.call();
                    } else {
                        wait(sleepFor);
                    }
                } catch (InterruptedException ex) {
                    // do nothing
                }
            }
        }
    }
}

interface Task {
    public void call();
}

class TaskWrapper {
    Task task;
    long executeAtMilliSecond;

    public TaskWrapper(Task task, long delayInSeconds) {
        this.task = task;
        this.executeAtMilliSecond = System.currentTimeMillis() + delayInSeconds * 1000;
    }
}
