package com.cloudkitchens;

import com.cloudkitchens.couriermanagement.CourierManager;
import com.cloudkitchens.couriermanagement.CourierScheduler;
import com.cloudkitchens.datamodel.Order;
import com.cloudkitchens.ingestor.FileOrderIngester;
import com.cloudkitchens.ingestor.OrderIngestor;
import com.cloudkitchens.kitchen.Chef;
import com.cloudkitchens.kitchen.FlashChef;
import com.cloudkitchens.shelfmanagement.HashMapShelfManager;
import com.cloudkitchens.shelfmanagement.MemoryListShelf;
import com.cloudkitchens.shelfmanagement.Shelf;
import com.cloudkitchens.shelfmanagement.ShelfManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class KitchenManager {

    private OrderIngestor ingestor;
    private ShelfManager shelfManager;
    private CourierScheduler courierScheduler;

    public void setIngestor(OrderIngestor ingestor) {
        this.ingestor = ingestor;
    }

    public void setShelfManager(ShelfManager shelfManager) {
        this.shelfManager = shelfManager;
    }

    public void setCourierScheduler(CourierScheduler courierScheduler) {
        this.courierScheduler = courierScheduler;
    }


    public static void main(String[] args) throws Exception {

        KitchenManager kitchenManager = new KitchenManager();

        File file = new File("orders.json");
        System.out.println(file.getAbsolutePath());
        kitchenManager.setIngestor(new FileOrderIngester("orders.json", 500));
        //kitchenManager.setIngestor(new StaticDelayOrderGenerator());

        Map<String, Shelf> shelfs = new HashMap<>();
        shelfs.put("cold", new MemoryListShelf("cold", 10));
        shelfs.put("hot", new MemoryListShelf("hot", 10));
        shelfs.put("frozen", new MemoryListShelf("frozen", 10));
        shelfs.put("overflow", new MemoryListShelf("overflow", 15));
        kitchenManager.setShelfManager(new HashMapShelfManager(shelfs));

        // kitchenManager.setCourierScheduler(new DelayCourierScheduler(2, 6));

        Order order = null;
        do {
            order = kitchenManager.ingestor.poll();
            if (order == null) break;

            Chef chef = new FlashChef();
            Order cookedOrder = chef.cook(order);
            kitchenManager.shelfManager.putOrderOnShelf(cookedOrder);
            printShelfStatus(kitchenManager.shelfManager);
//            Courier courier = kitchenManager.courierScheduler.dispatchCourier();
//            courier.pickup(kitchenManager.shelfManager, cookedOrder);
//            printShelfStatus(kitchenManager.shelfManager);

            CourierManager courierManager = new CourierManager(kitchenManager.shelfManager, order);
            new Thread(courierManager).start();
        } while (true);
    }

    public static void printShelfStatus(ShelfManager shelfManager) {
        System.out.println("=== shelf status ===");
        System.out.print(shelfManager);
        System.out.println("====================");
    }
}
