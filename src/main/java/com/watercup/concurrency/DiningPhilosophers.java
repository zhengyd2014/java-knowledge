package com.watercup.concurrency;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class DiningPhilosophers {

    /*
    Imagine you have five philosopher's sitting on a roundtable. The philosopher's do only two kinds of activities.
    One they contemplate, and two they eat. However, they have only five forks between themselves to eat their food with.
    Each philosopher requires both the fork to his left and the fork to his right to eat his food.

    The arrangement of the philosophers and the forks are shown in the diagram.

    Design a solution where each philosopher gets a chance to eat his food without causing a deadlock

    Solution:
    Each fork represents a resource that two of the philosophers on either side can attempt to acquire.
    This intuitively suggests using a semaphore with a permit value of 1 to represent a fork.

    Each philosopher can then be thought of as a thread that tries to acquire the forks to the left and right of it.
     */

    Semaphore fork[] = new Semaphore[5];
    Semaphore maxDinner = new Semaphore(4);

    public DiningPhilosophers() {
        for (int i = 0; i < fork.length; i++) {
            fork[i] = new Semaphore(1);
        }
    }

    public static void main(String[] args) throws Exception{
        DiningPhilosophers dp = new DiningPhilosophers();

        Set<Thread> philosophers = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            philosophers.add(new Thread(new Philosopher(i, dp)));
        }

        for (Thread philosopher : philosophers) {
            philosopher.start();
        }

        Thread.sleep(60000);

        for (Thread philosopher : philosophers) {
            philosopher.join();
        }
    }

}

class Philosopher implements Runnable {

    int id;
    DiningPhilosophers dp;
    Random random = new Random();

    public Philosopher(int id, DiningPhilosophers dp) {
        this.id = id;
        this.dp = dp;
    }

    public void run() {
        while (true) {
            try {
                contemplate();
                eat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void contemplate() throws InterruptedException {
        System.out.println("Philosopher " + id + " is contemplating.");
        Thread.sleep(random.nextInt(5000));
    }

    private void eat() throws InterruptedException {
        dp.maxDinner.acquire();

        dp.fork[id].acquire();
        dp.fork[ (id + 1) % dp.fork.length].acquire();
        System.out.println("Philosopher " + id + " is eating.");
        dp.fork[ (id + 1) % dp.fork.length].release();
        dp.fork[id].release();

        dp.maxDinner.release();
    }

}
