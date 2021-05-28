package com.cloudkitchens.shelfmanagement;

import com.cloudkitchens.datamodel.Order;

public interface ShelfManager {

    public boolean putOrderOnShelf(Order order);

    public Order removeOrderFromShelf(Order order);

    public int availableSlotNumber(String shelfName);
}
