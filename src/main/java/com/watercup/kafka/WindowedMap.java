package com.watercup.kafka;

import java.util.Map;
import java.util.HashMap;

public class WindowedMap {

    Map<Long, Node> map;
    DoubleLinkedList cache;
    long windowSize;
    long runningSum;

    public WindowedMap(long windowSize) {
        map = new HashMap<>();
        cache = new DoubleLinkedList();
        this.windowSize = windowSize;
    }

    /** puts or replaces a previous key value pairing */
    void put(long key, long value) {

        Node node = null;
        if (map.containsKey(key)) {
            node = getNode(key);

            // update runningsum
            runningSum += value;
            runningSum -= node.value;

            node.value = value;
            cache.remove(node);
            cache.appendToTail(node);
            System.out.println("update " + node.key);
        } else {
            node = new Node(key, value);
            cache.appendToTail(node);
            map.put(key, node);
            runningSum += value;
            System.out.println("put new " + node.key);
        }

        System.out.println("int put: running sum: " + runningSum + ", size: " + map.size());
    }

    /** gets the most recent value for the key */
    long get(long key) {
        Node node = getNode(key);
        return node.value;
    }


    /** gets the average for all values within the window */
    double getAverage() {
        cleanup();
        // if (map.size() <= 2) return 0.0;
        System.out.println("in getAverage: running sum: " + runningSum + ", size: " + map.size());
        return runningSum * 1.0 / map.size();
    }

    private Node getNode(long key) {
        cleanup();
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + " is not exist.");
        }

        Node node = map.get(key);
        return node;
    }

    // evict all expired keys
    private void cleanup() {
        while(true) {
            //if (map.size() <= 2) break;

            Node node = cache.head.next;
            System.out.println("cleanup " + node.key);
            if (node.timestamp < System.currentTimeMillis() - windowSize) {
                cache.remove(node);
                map.remove(node.key);

                // update sum
                runningSum -= node.value;
            } else {
                break;
            }
        }
    }


class DoubleLinkedList {
    Node head;
    Node tail;

    public DoubleLinkedList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public void appendToTail(Node node) {
        node.timestamp = System.currentTimeMillis();
        node.next = tail;
        node.prev = tail.prev;

        node.prev.next = node;
        tail.prev = node;
    }

    public Node remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;

        node.prev = null;
        node.next = null;
        return node;
    }

    public Node removeFromHead() {
        return remove(head.next);
    }
}

class Node {
    long key;
    long value;
    long timestamp;

    Node next, prev;

    public Node(long key, long value) {
        this.key = key;
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }
}
}