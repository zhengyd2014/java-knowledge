package com.watercup.datastructure;

import com.watercup.Utils;
import com.watercup.datastructure.array.ProductsExceptSelf;
import org.junit.Test;

import static com.watercup.Utils.arrayToString;

public class ProductsExceptSelfTest {

    @Test
    public void testFindProducts() {
        ProductsExceptSelf productsExceptSelf = new ProductsExceptSelf();
        int[] arr = {-1, 2, -3, 4, -5};

        System.out.println("Array before product: " + Utils.arrayToString(arr));

        int[] prodArray = productsExceptSelf.findProducts(arr);

        System.out.println("Array after product: " + arrayToString(prodArray));
    }
}
