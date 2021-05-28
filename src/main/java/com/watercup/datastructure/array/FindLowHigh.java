package com.watercup.datastructure.array;

import java.util.Arrays;
import java.util.List;

public class FindLowHigh {

    /*
    Given a sorted array of integers, return the low and high index of the given key.

    You must return -1 if the indexes are not found.
     */
    static int findLowIndex(List<Integer> arr, int key) {
        //TODO: Write - Your - Code
        if (arr.size() == 0) return -1;

        int low = 0;
        int high = arr.size() - 1;

        while (low + 1 < high) {
            int mid = low + (high - low) / 2;

            if (arr.get(mid) < key) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        if (arr.get(low) == key) return low;
        else if (arr.get(high) == key) return high;
        else return -1;
    }

    static int findHighIndex(List<Integer> arr, int key) {
        //TODO: Write - Your - Code
        if (arr.size() == 0) return -1;

        int low = 0;
        int high = arr.size() - 1;

        while (low + 1 < high) {
            int mid = low + (high - low) / 2;

            if (arr.get(mid) > key) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }

        if (arr.get(high) == key) return high;
        else if (arr.get(low) == key) return low;
        else return -1;
    }


    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4,4, 5, 5, 5, 6, 6, 6, 6, 6, 6);
        int key = 5;
        int low = findLowIndex(array, key);
        int high = findHighIndex(array, key);
        System.out.println("Low Index of " + key + ": " + low);
        System.out.println("High Index of " + key + ": " + high);

        key = -2;
        low = findLowIndex(array, key);
        high = findHighIndex(array, key);
        System.out.println("Low Index of " + key + ": " + low);
        System.out.println("High Index of " + key + ": " + high);
    }
}
