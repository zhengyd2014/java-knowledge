package com.watercup.dynamicprogramming.unboundedKnapSack;

public class CoinChange {
    /*
    Given an infinite supply of ‘n’ coin denominations and a total money amount,
    we are asked to find the total number of distinct ways to make up that amount.

    Example:

    Denominations: {1,2,3}
    Total amount: 5
    Output: 5
    Explanation: There are five ways to make the change for '5', here are those ways:
      1. {1,1,1,1,1}
      2. {1,1,1,2}
      3. {1,2,2}
      4. {1,1,3}
      5. {2,3}

    Problem Statement #
    Given a number array to represent different coin denominations and a total amount ‘T’,
    we need to find all the different ways to make a change for ‘T’ with the given coin denominations.
     We can assume an infinite supply of coins, therefore, each coin can be chosen multiple times.

    This problem follows the Unbounded Knapsack pattern.
     */

    public int countChange(int[] denominations, int total) {
        int n = denominations.length;
        int[][] dp = new int[n + 1][total + 1];

        // populate the total=0 columns, as we will always have an empty set for zero total
        for(int i=0; i <= n; i++)
            dp[i][0] = 1;

        // process all sub-arrays for all capacities
        for(int i=1; i <= n; i++) {
            for(int t=1; t <= total; t++) {
                dp[i][t] = dp[i-1][t];
                if(t >= denominations[i-1])
                    dp[i][t] += dp[i][t-denominations[i-1]];
            }
        }

        // total combinations will be at the bottom-right corner.
        return dp[n][total];
    }

    public static void main(String[] args) {
        CoinChange cc = new CoinChange();
        int[] denominations = {1, 2, 3};
        System.out.println(cc.countChange(denominations, 5));
    }
}
