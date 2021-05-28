package com.cloudkitchens.ingestor;

import com.cloudkitchens.datamodel.Order;
import com.cloudkitchens.util.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * read order from a file
 */
public class FileOrderIngester implements OrderIngestor {

    // control latency in poll, to determine how quick to return Orders.
    long latencyInMillis;

    List<Order> orders;
    int index = 0;

    public FileOrderIngester(String fileName, long latencyInMillis) throws FileNotFoundException {
        this.latencyInMillis = latencyInMillis;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        Gson gson = new Gson();
        // Order[] orders = gson.fromJson(bufferedReader, Order[].class);

        orders = gson.fromJson(br, new TypeToken<List<Order>>(){}.getType());
    }

    public Order poll() {
        try {
            Thread.sleep(latencyInMillis);
        } catch (InterruptedException is) {

        }

        Order order = null;
        if (index < orders.size()) {
            order = orders.get(index++);
            System.out.printf("%s: creating order: %s\n", Utils.currentTime(), order);
            return order;
        }

        return order;
    }
}
