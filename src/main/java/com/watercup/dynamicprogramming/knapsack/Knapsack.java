package com.watercup.dynamicprogramming.knapsack;

public class Knapsack {

    /*
    Given two integer arrays to represent weights and profits of ‘N’ items,
    we need to find a subset of these items which will give us maximum profit
    such that their cumulative weight is not more than a given number ‘C.’
    Each item can only be selected once, which means either we put an item in the knapsack or we skip it.

    Weights: { 2, 3, 1, 4 }
    Profits: { 4, 5, 3, 7 }
    Knapsack capacity: 5
     */

    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        // basic checks
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
            return 0;

        int n = profits.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                dp[i][w] = dp[i-1][w];     // not include item[i-1]
                if (weights[i-1] <= w) {   // include item[i-1]
                    dp[i][w] = Math.max(dp[i][w], profits[i-1] + dp[i-1][w - weights[i-1]]);
                }
            }
        }

        return dp[n][capacity];
    }


    public static void main(String[] args) {
        Knapsack ks = new Knapsack();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = ks.solveKnapsack(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }
}
