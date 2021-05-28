package com.cloudkitchens.kitchen;

import com.cloudkitchens.datamodel.Order;
import com.cloudkitchens.util.Utils;

public class FlashChef implements Chef{
    @Override
    public Order cook(Order order) {
        if (order == null) return null;
        System.out.printf("%s: cooking a order\n", Utils.currentTime());
        order.created_time = System.currentTimeMillis();
        return order;
    }
}
