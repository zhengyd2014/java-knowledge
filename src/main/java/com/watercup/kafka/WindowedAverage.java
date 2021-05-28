package com.watercup.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class WindowedAverage {
    /*
        1.) Windowed Average. Your are given a set of key, value pair.
        Each key, value expires after k millisec.

        I can ask you to get me a specific key. Also, I can ask you to return me the average.
        The catch was to make sure to pruce the DLL before each call.

        For ex: if <"foo", 100> is saved at t = 1 and time expiry is 3ms
        then after 3 ms get("foo") should return key not found.
     */

    PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> Long.compare(a.timestamp, b.timestamp));
    Map<String, Node> map = new HashMap<>();
    int runningSum = 0;
    int windowSize;

    public WindowedAverage(int windowSize) {
        this.windowSize = windowSize;
    }

    public void put(String key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            runningSum = runningSum + value - node.value;
            node.value = value;
            return;
        }

        Node node = new Node(key, value);
        pq.add(node);
        map.put(key, node);
        runningSum += value;
    }

    public int get(String key) {
        cleanup();
        Node node = map.get(key);
        if (node == null) return -1;
        return node.value;
    }

    public int getAvg() {
        cleanup();
        return runningSum / pq.size();
    }

    public void cleanup() {
        while (pq.peek().timestamp < System.currentTimeMillis() - windowSize) {
            Node node = pq.poll();
            runningSum -= node.value;
            map.remove(node.key);
        }
    }
}

class Node {
    String key;
    int value;
    long timestamp;

    public Node(String key, int value) {
        this.key = key;
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }
}
