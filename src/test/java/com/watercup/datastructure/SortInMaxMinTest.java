package com.watercup.datastructure;

import com.watercup.datastructure.array.SortInMaxMinForm;
import org.junit.Test;

public class SortInMaxMinTest {

    @Test
    public void testMaxMin() {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        System.out.print("Array before min/max: ");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();

        new SortInMaxMinForm().maxMin(arr);

        System.out.print("Array after min/max: ");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
