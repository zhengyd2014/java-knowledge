package com.watercup.datastructure.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FrequencyStack {

    /*
    Design a class that simulates a Stack data structure, implementing the following two operations:

    push(int num): Pushes the number ‘num’ on the stack.
    pop(): Returns the most frequent number in the stack. If there is a tie,
           return the number which was pushed later.

    Example:
    After following push operations: push(1), push(2), push(3), push(2), push(1), push(2), push(5)

    1. pop() should return 2, as it is the most frequent number
    2. Next pop() should return 1
    3. Next pop() should return 2
     */

    int sequence = 0;
    PriorityQueue<Element> maxHeap = new PriorityQueue<Element>((a, b) -> {
        if (a.freq != b.freq) {
            return Integer.compare(b.freq, a.freq);
        }

        return Integer.compare(b.seq, a.seq);
    });
    Map<Integer, Integer> numFreqMap = new HashMap<>();

    public void push(int num) {
        numFreqMap.put(num, numFreqMap.getOrDefault(num, 0) + 1);
        maxHeap.offer(new Element(num, numFreqMap.get(num), sequence++));
    }

    public int pop() {
        int num = maxHeap.poll().num;
        numFreqMap.put(num, numFreqMap.get(num) - 1);
        if (numFreqMap.get(num) == 0) numFreqMap.remove(num);
        return num;
    }

    public static void main(String[] args) {
        FrequencyStack frequencyStack = new FrequencyStack();
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(3);
        frequencyStack.push(2);
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(5);
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
    }

}

class Element {
    int num;
    int freq;
    int seq;

    public Element(int num, int freq, int seq) {
        this.num = num;
        this.freq = freq;
        this.seq = seq;
    }
}