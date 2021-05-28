package com.watercup.datastructure.array;

import java.util.Arrays;

public class MoveZerosToLeft {

    /*
        Move all zeros to the left of an array while maintaining its order.
     */
    static void moveZerosToLeft(int[] A) {
        //TODO: Write - Your - Code
        if (A == null || A.length == 0) return;

        int writeIdx = A.length - 1;
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] != 0) A[writeIdx--] = A[i];
        }

        while(writeIdx >= 0) A[writeIdx--] = 0;
    }

    public static void main(String[] args) {
        int[] v = new int[]{1, 10, 20, 0, 59, 63, 0, 88, 0};
        System.out.println("Original Array: " + Arrays.toString(v));

        moveZerosToLeft(v);

        System.out.println("After Moving Zeroes to Left: " + Arrays.toString(v));
    }
}
