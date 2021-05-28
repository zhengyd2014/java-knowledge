package com.watercup.dynamicprogramming.unboundedKnapSack;

public class MinimumCoinChange {
    /*
    Given an infinite supply of ‘n’ coin denominations and a total money amount,
    we are asked to find the minimum number of coins needed to make up that amount.

    Example 1:

    Denominations: {1,2,3}
    Total amount: 5
    Output: 2
    Explanation: We need minimum of two coins {2,3} to make a total of '5'
    Example 2:

    Denominations: {1,2,3}
    Total amount: 11
    Output: 4
    Explanation: We need minimum four coins {2,3,3,3} to make a total of '11'
     */

    public int countChange(int[] denominations, int total)
    {
        int n = denominations.length;
        int[][] dp = new int[n+1][total + 1];

        for(int i=0; i < n; i++)
            for(int j=0; j <= total; j++)
                dp[i][j] = Integer.MAX_VALUE;

        // populate the total=0 columns, as we don't need any coin to make zero total
        for(int i=0; i < n; i++)
            dp[i][0] = 0;

        for(int i = 1; i <= n; i++) {
            for(int t = 1; t <= total; t++) {
                dp[i][t] = dp[i-1][t]; //exclude the coin
                if(t >= denominations[i-1]) {
                    if(dp[i][t-denominations[i-1]] != Integer.MAX_VALUE)
                        dp[i][t] = Math.min(dp[i][t], dp[i][t-denominations[i-1]]+1); // include the coin
                }
            }
        }

        // total combinations will be at the bottom-right corner.
        return (dp[n][total] == Integer.MAX_VALUE ? -1 : dp[n][total]);
    }

    public static void main(String[] args) {
        MinimumCoinChange cc = new MinimumCoinChange();
        int[] denominations = {1, 2, 3};
        System.out.println(cc.countChange(denominations, 5));
        System.out.println(cc.countChange(denominations, 11));
        System.out.println(cc.countChange(denominations, 7));
        denominations = new int[]{3, 5};
        System.out.println(cc.countChange(denominations, 7));
    }
}
