package com.cloudkitchens.couriermanagement;

import com.cloudkitchens.datamodel.Order;
import com.cloudkitchens.shelfmanagement.ShelfManager;

public class FlashCourier implements Courier {

    @Override
    public Order pickup(ShelfManager shelfs, Order order) {
        Order theOrder = shelfs.removeOrderFromShelf(order);
        return theOrder;
    }
}
