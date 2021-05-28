package com.watercup.datastructure.kwaymerge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRange {

    /*
    Given ‘M’ sorted arrays, find the smallest range that includes at least one number from each of the ‘M’ lists.

    Example 1:

    Input: L1=[1, 5, 8], L2=[4, 12], L3=[7, 8, 10]
    Output: [4, 7]
    Explanation: The range [4, 7] includes 5 from L1, 4 from L2 and 7 from L3.
    Example 2:

    Input: L1=[1, 9], L2=[4, 12], L3=[7, 10, 16]
    Output: [9, 12]
    Explanation: The range [9, 12] includes 9 from L1, 12 from L2 and 10 from L3.

    Time:  O(N*logM)
    Space: O(M)
     */

    public static int[] findSmallestRange(List<Integer[]> lists) {
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>((a, b)
                -> lists.get(a.arrayIndex)[a.elementIndex] - lists.get(b.arrayIndex)[b.elementIndex]);

        int currentMax = Integer.MIN_VALUE;
        int rangeStart = 0;                  // make sure rangeEnd - rangeStart not overflow
        int rangeEnd = Integer.MAX_VALUE;

        // put the 1st element of each array in the min heap
        for (int i = 0; i < lists.size(); i++) {
            Integer[] arr = lists.get(i);
            if (arr.length > 0) minHeap.offer(new Node(0, i));
            currentMax = Math.max(currentMax, arr[0]);
        }

        // take the smallest (top) element form the min heap, if it gives us smaller range, update the ranges
        // if the array of the top element has more elements, insert the next element in the heap
        while(minHeap.size() == lists.size()) {
            Node node = minHeap.poll();
            int curr = lists.get(node.arrayIndex)[node.elementIndex];
            if (rangeEnd - rangeStart > currentMax - curr) {
                rangeEnd = currentMax;
                rangeStart = curr;
            }

            node.elementIndex++;
            if (node.elementIndex < lists.get(node.arrayIndex).length) {
                minHeap.offer(node);   // insert the next element in the heap
                currentMax = Math.max(currentMax, lists.get(node.arrayIndex)[node.elementIndex]);
            }
        }

        return new int[] {rangeStart, rangeEnd};
    }

    public static void main(String[] args) {
        Integer[] l1 = new Integer[] { 1, 5, 8 };
        Integer[] l2 = new Integer[] { 4, 12 };
        Integer[] l3 = new Integer[] { 7, 8, 10 };
        List<Integer[]> lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int[] result = SmallestRange.findSmallestRange(lists);
        System.out.print("Smallest range is: [" + result[0] + ", " + result[1] + "]");
    }
}

class Node {
    int elementIndex;
    int arrayIndex;

    public Node(int elementIndex, int arrayIndex) {
        this.elementIndex = elementIndex;
        this.arrayIndex = arrayIndex;
    }
}
