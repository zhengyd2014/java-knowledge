package com.cloudkitchens.couriermanagement;

import com.cloudkitchens.datamodel.Order;
import com.cloudkitchens.shelfmanagement.ShelfManager;

public interface Courier {

    public Order pickup(ShelfManager shelfs, Order order);
}
