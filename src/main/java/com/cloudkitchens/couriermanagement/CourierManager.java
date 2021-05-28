package com.cloudkitchens.couriermanagement;

import com.cloudkitchens.KitchenManager;
import com.cloudkitchens.datamodel.Order;
import com.cloudkitchens.shelfmanagement.ShelfManager;

public class CourierManager implements Runnable {

    private Order order;
    private ShelfManager shelfManager;
    public CourierManager(ShelfManager shelfManager, Order order) {
        this.shelfManager = shelfManager;
        this.order = order;
    }

    @Override
    public void run() {
        CourierScheduler courierScheduler = new DelayCourierScheduler(2, 6);
        Courier courier = courierScheduler.dispatchCourier();
        courier.pickup(shelfManager, order);
        KitchenManager.printShelfStatus(shelfManager);
    }
}
