package com.cloudkitchens.ingestor;

import com.cloudkitchens.datamodel.Order;
import com.cloudkitchens.datamodel.OrderTemperature;
import com.cloudkitchens.util.Utils;

import java.util.UUID;

public class StaticDelayOrderGenerator implements OrderIngestor{
    @Override
    public Order poll() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException is) {

        }

        Order order = new Order();
        order.id = UUID.randomUUID().toString();
        order.name = "Cheese Pizza";
        order.temp = OrderTemperature.hot;
        order.shelf_life = 300;
        order.decay_rate = 0.45f;
        System.out.printf("%s: creating order: %s\n", Utils.currentTime(), order);
        return order;
    }
}
