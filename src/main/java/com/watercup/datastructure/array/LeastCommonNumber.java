package com.watercup.datastructure.array;

public class LeastCommonNumber {

    /*
      Given three integer arrays sorted in ascending order,
      return the smallest number that is common in all three arrays.
     */
    static Integer findLeastCommonNumber(int[] arr1, int[] arr2, int[] arr3) {
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] == arr2[j] && arr1[i] == arr3[k]) return arr1[i];

            int min = Math.min(arr1[i], Math.min(arr2[j], arr3[k]));
            if (arr1[i] == min) i++;
            if (arr2[j] == min) j++;
            if (arr3[k] == min) k++;
        }
        return -1;
    }

    public static void main(String []args){
        int[] v1 = new int[]{6, 7, 10, 25, 30, 63, 64};
        int[] v2 = new int[]{1, 4, 5, 6, 7, 8, 50};
        int[] v3 = new int[]{1, 6, 10, 14};

        Integer result = findLeastCommonNumber(v1, v2, v3);
        System.out.println("Least Common Number: " + result);
    }
}
