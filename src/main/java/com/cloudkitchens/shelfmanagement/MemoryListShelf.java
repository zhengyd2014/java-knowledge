package com.cloudkitchens.shelfmanagement;

import com.cloudkitchens.datamodel.Order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemoryListShelf implements Shelf {

    String name;
    int capacity;
    List<Order> orders;

    public String getName() {
        return this.name;
    }

    public MemoryListShelf(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.orders = new ArrayList<>(capacity);
    }

    @Override
    public boolean store(Order order) {
        if (isFull()) {
            return false;
        }

        orders.add(order);
        return true;
    }

    @Override
    public boolean remove(String orderId) {
        for (Order order : orders) {
            if (order.id.equals(orderId)) {
                orders.remove(order);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isFull() {
        if (orders.size() < capacity) {
            return false;
        }
        return true;
    }

    @Override
    public int availableSoltNumber() {
        return capacity - orders.size();
    }

    @Override
    public Iterator<Order> getOrderIterator() {
        return orders.iterator();
    }

    @Override
    public List<Order> cleanup() {
        List<Order> decayedOrders = new ArrayList<>();

        List<Integer> to_be_removed = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if ((int)order.getValue(this.name) == 0) {
                to_be_removed.add(i);
            }
        }

        // remove
        for (int i = orders.size()-1; i >= 0; i--) {
            decayedOrders.add(orders.remove(i));
        }

        return decayedOrders;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{").append("name: ").append(name)
                .append(", size: ").append(orders.size()).append("}");
        return sb.toString();
    }
}
