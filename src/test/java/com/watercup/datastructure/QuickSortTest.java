package com.watercup.datastructure;

import com.watercup.datastructure.array.QuickSort;
import org.junit.Test;

public class QuickSortTest {

    @Test
    public void testQuickSort() {
        QuickSort quickSort = new QuickSort();
        int[] arr = new int[] {3, 5, 4, 2, 1};
        quickSort.sort(arr);
        printArray(arr);
    }

    private void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.print("]");
    }
}
