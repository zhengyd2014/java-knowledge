package com.watercup.datastructure.array;

public class SortInMaxMinForm {

    /*
        Re-arrange Sorted Array in Max/Min Form

        Array before min/max: 1 2 3 4 5 6 7 8 9
        Array after min/max: 9 1 8 2 7 3 6 4 5

        This solution is very smart. We actually store two elements at one index mathematically.
        The original element is stored as the *remainder*,
        while the max/min element is stored as the *multiplier*.

        The following line achieves this:
            arr[i] += (arr[maxIdx] % maxElem ) * maxElem;

        Here, arr[maxId] is stored as the multiplier. Whereas, arr[i] is stored as the remainder.
        For example in the array, [1, 2, 3, 4, 5, 6, 7, 8, 9], the maxElem which is any element
        greater than the maximum element in the array, in this case, is 10 and 91 is stored at index 0.
        With 91, we can get the original element, 1, using the expression 91 % 10
        as well as the new element, 9, using the expression 91/10.

        This allows us to swap the numbers in place without losing any data or using any extra space.
        To get the final array, we simply divide each element by maxElem as done in the last for loop.
     */

    //  Using O(1) Extra Space
    public void maxMin(int[] arr) {
        int maxIdx = arr.length - 1;
        int minIdx = 0;
        int maxElem = arr[maxIdx] + 1; // store any element that is greater than the maximum element

        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                arr[i] += (arr[maxIdx] % maxElem) * maxElem;
                maxIdx--;
            } else {
                arr[i] += (arr[minIdx] % maxElem) * maxElem;
                minIdx++;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] / maxElem;
        }
    }


    // create a new array
    public static void maxMin2(int[] arr) {
        //Create a result array to hold re-arranged version of given arr
        int[] result = new int[arr.length];

        int pointerSmall = 0;     //PointerSmall => Start of arr
        int pointerLarge = arr.length - 1;   //PointerLarge => End of arr

        //Flag which will help in switching between two pointers
        boolean switchPointer = true;

        for (int i = 0; i < arr.length; i++) {
            if (switchPointer)
                result[i] = arr[pointerLarge--]; // copy large values
            else
                result[i] = arr[pointerSmall++]; // copy small values

            switchPointer = !switchPointer;   // switching between samll and large
        }

        for (int j = 0; j < arr.length; j++) {
            arr[j] = result[j];    // copying to original array
        }
    }
}
