package com.watercup.concurrency;

import org.junit.Test;

public class TestCalculator {

    @Test
    public void testCalculation() {
        Calculator windowedMap = new Calculator();

        System.out.println(windowedMap.calculate(
                "(1+(4+5+2)-3)+(6+8)"));
    }
}
