package com.watercup.datastructure.array;

public class ProductsExceptSelf {

    public int[] findProducts(int[] arr) {
        int n = arr.length;

        int[] result = new int[n];
        int tmp = 1;

        // Product of elements on left side excluding arr[i]
        for (int i = 0; i < arr.length; i++) {
            result[i] = tmp;
            tmp *= arr[i];
        }

        // Initializing temp to 1 for product on right side
        tmp = 1;

        // Product of elements on right side excluding arr[i]
        for (int i = n-1; i >= 0; i--) {
            result[i] *= tmp;
            tmp *= arr[i];
        }

        return result;
    }
}
