package com.watercup.concurrency;

public class SpawnThread {

    public static void main(String[] args) {

        Thread innerThread = new Thread(new Runnable() {

            public void run() {

                for (int i = 0; i < 100; i++) {
                    System.out.println("I am a new thread !");
                }
            }
        });

        innerThread.start();
        System.out.println("Main thread exiting");
    }
}
