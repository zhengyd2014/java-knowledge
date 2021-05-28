package com.watercup.dynamicprogramming.knapsack;

public class SubsetSum {
    /*
    Given a set of positive numbers, determine if a subset exists whose sum is equal to a given number ‘S’.

    Example 1: #
    Input: {1, 2, 3, 7}, S=6
    Output: True
    The given set has a subset whose sum is '6': {1, 2, 3}
    Example 2: #
    Input: {1, 2, 7, 1, 5}, S=10
    Output: True
    The given set has a subset whose sum is '10': {1, 2, 7}
    Example 3: #
    Input: {1, 3, 4, 8}, S=6
    Output: False
    The given set does not have any subset whose sum is equal to '6'.
     */

    public boolean canPartition(int[] num, int sum) {

        int n = num.length;
        boolean[] dp = new boolean[sum+1];

        dp[0] = true;
        for (int v : num) {
            for (int j = sum; j >= 0; j--) {
                if (j >= v) {
                    dp[j] |= dp[j-v];
                }
            }
        }

        return dp[sum];
    }

    public boolean canPartition2(int[] num, int sum) {

        int n = num.length;
        boolean[][] dp = new boolean[n+1][sum+1];

        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i-1][j];
                if (j >= num[i-1]) {
                    dp[i][j] |= dp[i-1][j-num[i-1]];
                }
            }
        }

        return dp[n][sum];
    }

    public static void main(String[] args) {
        SubsetSum ss = new SubsetSum();
        int[] num = { 1, 2, 3, 7 };
        System.out.println(ss.canPartition(num, 6));
        num = new int[] { 1, 2, 7, 1, 5 };
        System.out.println(ss.canPartition(num, 10));
        num = new int[] { 1, 3, 4, 8 };
        System.out.println(ss.canPartition(num, 6));
    }
}
