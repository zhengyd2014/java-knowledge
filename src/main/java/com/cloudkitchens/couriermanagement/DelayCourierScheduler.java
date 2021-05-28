package com.cloudkitchens.couriermanagement;

import com.cloudkitchens.util.Utils;

import java.util.Random;

public class DelayCourierScheduler implements CourierScheduler {

    Random random = new Random();
    int low = 2;
    int high = 6;

    public DelayCourierScheduler(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public Courier dispatchCourier() {

        long delay = random.nextInt(high - low + 1);
        delay = (delay + low) * 1000;
        System.out.printf("%s: start dispatch a courier, sleep for %d milliseconds \n", Utils.currentTime(), delay);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ie) {

        }

        System.out.printf("%s: dispached a courier\n", Utils.currentTime());
        return new FlashCourier();
    }
}
