package com.watercup.classic;

public class HashMap<K, V> {

    int capacity = 16;
    int size = 0;
    float loadFactor = 0.75f;
    Entry<K, V>[] table;

    public HashMap() {
    }

    public HashMap(int capacity) {
        this.capacity = capacity;
        table = new Entry[capacity];
    }

    public void put(K key, V value) {
        int index = key.hashCode() % capacity;
        Entry e = table[index];
        for (; e != null; e = e.next) {
            if (e.key.equals(key)) {
                e.value = value;
                break;
            }
        }

        // not in table
        if (size > capacity * loadFactor) {
            resize();
        }

        //
        if (e == null) {
            Entry<K, V> n = new Entry(key, value);
            n.next = table[index];
            table[index] = n;
            size++;
        }
    }

    public V get(K key) {
        int index = key.hashCode() % capacity;
        Entry<K, V> e = table[index];
        for (; e != null; e = e.next) {
            if (e.key.equals(key)) {
                break;
            }
        }

        if (e == null) {
            return null;
        } else {
            return e.value;
        }
    }

    private void resize() {
        int newsize = capacity * 2;
        Entry<K, V>[] newTable = new Entry[newsize];

        for (int i = 0; i < table.length; i++) {
            Entry<K, V> e = table[i];
            if (e != null) {
                int newIdx = e.key.hashCode() % newsize;
                newTable[newIdx] = e;
            }
        }

        table = newTable;
    }
}


class Entry<K, V> {
    K key;
    V value;
    Entry next;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
