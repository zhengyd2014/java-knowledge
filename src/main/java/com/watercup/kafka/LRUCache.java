package com.watercup.kafka;

import java.util.concurrent.ConcurrentHashMap;

public class LRUCache {

    /*
        concurrency discussion:
        - sharding  - hash(key) % n to find shard, then lock
        - read / write lock
        - windowing:  recently promoted nodes don't need to promote, to reduce promote frequency.
     */

    ConcurrentHashMap<Integer, DLNode> map;
    DoubleLinkedList cache;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new ConcurrentHashMap<>();
        cache = new DoubleLinkedList();
    }

    public void put(int key, int val) {
        if (get(key) != -1) {
            map.get(key).val = val;
        } else {
            if (map.size() >= capacity) {
                DLNode toRemove = cache.removeHead();
                map.remove(toRemove.key);
            }

            DLNode newNode = new DLNode(key, val);
            cache.appendToTail(newNode);
            map.put(newNode.key, newNode);
        }
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        DLNode node = map.get(key);
        cache.remove(node);
        cache.appendToTail(node);
        return node.val;
    }
}

class DoubleLinkedList {
    DLNode head;
    DLNode tail;

    public DoubleLinkedList() {
        head = new DLNode(0,0);
        tail = new DLNode(0,0);
        head.next = tail;
        tail.prev = head;
    }

    public void appendToTail(DLNode node) {
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }

    public DLNode remove(DLNode node) {
        DLNode prev = node.prev;
        prev.next = node.next;
        node.next.prev = prev;
        node.prev = null;
        node.next = null;
        return node;
    }

    public DLNode removeHead() {
        return remove(head.next);
    }

    public static void main(String[] args) {
        /*
        ["LRUCache","put","put","get","put","get","put","get","get","get"]
[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
         */
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}

class DLNode {
    int key;
    int val;
    DLNode prev, next;

    public DLNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
