package com.cloudkitchens.shelfmanagement;

import com.cloudkitchens.datamodel.Order;
import com.cloudkitchens.util.Utils;

import java.util.Iterator;
import java.util.Map;

public class HashMapShelfManager implements ShelfManager {

    Map<String, Shelf> shelfs;

    public HashMapShelfManager(Map<String, Shelf> shelfs) {
        this.shelfs = shelfs;
    }

    public synchronized boolean putOrderOnShelf(Order order) {
        Shelf shelf = shelfs.get(order.temp.name());
        if (shelf == null) {
            System.out.println("no shelf find for store the order with temp: " + order.temp.name());
            return false;
        }

        if (!putOrderOnShelf(shelf, order)) {
            return putOrderOnShelf(shelfs.get("overflow"), order);
        }

        return true;
    }

    public synchronized Order removeOrderFromShelf(Order order) {
        Shelf shelf = shelfs.get(order.temp.name());
        if (shelf == null) {
            System.out.println("no shelf find for store the order with temp: " + order.temp.name());
            return null;
        }

        Order theOrder = removeOrderFromShelf(shelf, order);
        if (theOrder == null) {
            return removeOrderFromShelf(shelfs.get("overflow"), order);
        }

        return theOrder;
    }

    @Override
    public int availableSlotNumber(String shelfName) {
        Shelf shelf = shelfs.get(shelfName);
        if (shelf == null) return 0;
        return shelf.availableSoltNumber();
    }


    private boolean putOrderOnShelf(Shelf shelf, Order order) {
        // List<Order> decayOrders = shelf.cleanup();

        if (shelf.isFull()) {
            if (shelf.getName().equals("overflow")) {
                Iterator<Order> iter = shelf.getOrderIterator();
                Order to_be_relocate = null;
                while (iter.hasNext()) {
                    Order relocate_candidate_order = iter.next();
                    Shelf to_check_shelf = shelfs.get(relocate_candidate_order.temp.name());
                    if (to_check_shelf.availableSoltNumber() > 0) {
                        to_be_relocate = relocate_candidate_order;
                        break;
                    }
                }

                if (to_be_relocate != null) {
                    shelf.remove(to_be_relocate.id);
                    putOrderOnShelf(shelfs.get(to_be_relocate.temp.name()), to_be_relocate);
                }
            } else {
                return false;
            }
        }
        shelf.store(order);
        System.out.printf("%s: put one order on shelf %s, order: %s\n", Utils.currentTime(), shelf.getName(), order);
        return true;
    }

    private Order removeOrderFromShelf(Shelf shelf, Order order) {
        boolean remove = shelf.remove(order.id);
        if (remove) {
            System.out.printf("%s: remove one order from shelf %s, order: %s\n", Utils.currentTime(), shelf.getName(), order);
            return order;
        }
        else return null;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (String key : shelfs.keySet()) {
            sb.append(shelfs.get(key)).append("\n");
        }
        return sb.toString();
    }
}