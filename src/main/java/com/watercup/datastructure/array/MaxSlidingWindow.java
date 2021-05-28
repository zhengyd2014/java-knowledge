package com.watercup.datastructure.array;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class MaxSlidingWindow {

    public static ArrayDeque<Integer> findMaxSlidingWindow(int[] arr, int windowSize) {

        ArrayDeque<Integer> result = new ArrayDeque<>();
        if (arr == null || arr.length < windowSize) return result;

        Deque<Integer> window = new LinkedList<>();
        for (int right = 0; right < arr.length; right++) {
            while(!window.isEmpty() && arr[window.peekLast()] < arr[right]) window.pollLast();
            window.offer(right);

            if (right >= windowSize - 1) {
                result.offer(arr[window.peekFirst()]);

                // keep items in window be one element less of the window should contains
                if (window.peekFirst() <= right - windowSize + 1) window.pollFirst();
            }
        }
        //Write your code
        return result;
    }

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Array = " + Arrays.toString(array));
        System.out.println("Max = " + findMaxSlidingWindow(array, 3));

        int[] array2 = {10, 6, 9, -3, 23, -1, 34, 56, 67, -1, -4, -8, -2, 9, 10, 34, 67};
        System.out.println("Array = " + Arrays.toString(array2));
        System.out.println("Max = " + findMaxSlidingWindow(array2, 3));
    }
}
