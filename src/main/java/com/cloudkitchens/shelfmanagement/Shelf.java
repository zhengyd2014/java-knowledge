package com.cloudkitchens.shelfmanagement;

import com.cloudkitchens.datamodel.Order;

import java.util.Iterator;
import java.util.List;

public interface Shelf {

    public String getName();

    public boolean store(Order order);

    public boolean remove(String orderId);

    // remove all the decayed orders in the shelf
    public List<Order> cleanup();

    public boolean isFull();

    public int availableSoltNumber();

    public Iterator<Order> getOrderIterator();
}
