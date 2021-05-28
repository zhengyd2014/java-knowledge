package com.watercup.dynamicprogramming.unboundedKnapSack;

public class UnboundedKnapSack {

    /*
    Given two integer arrays to represent weights and profits of ‘N’ items,
    we need to find a subset of these items which will give us maximum profit
    such that their cumulative weight is not more than a given number ‘C’.

    We can assume an infinite supply of item quantities; therefore, each item
    can be selected multiple times.
     */

    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        int n = profits.length;

        int[][] dp = new int[n+1][capacity+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                dp[i][j] = dp[i-1][j];    // Exclude the item
                if (j >= weights[i-1]) {  // include the item
                       dp[i][j] = Math.max(dp[i][j], profits[i-1] + dp[i][j - weights[i-1]]);
                    // only difference from bounded knapsack is:    ^^^^^
                    // dp[i][j] = Math.max(dp[i][j], profits[i-1] + dp[i-1][j - weights[i-1]]);
                }
            }
        }

        return dp[n][capacity];
    }

    public static void main(String[] args) {
        UnboundedKnapSack ks = new UnboundedKnapSack();
        int[] profits = {15, 50, 60, 90};
        int[] weights = {1, 3, 4, 5};
        System.out.println(ks.solveKnapsack(profits, weights, 8));
        System.out.println(ks.solveKnapsack(profits, weights, 6));
    }
}
