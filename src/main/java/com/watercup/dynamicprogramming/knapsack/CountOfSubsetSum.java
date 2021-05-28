package com.watercup.dynamicprogramming.knapsack;

public class CountOfSubsetSum {

    /*
    Given a set of positive numbers, find the total number of subsets whose sum is equal to a given number ‘S’.

    Example 1: #
    Input: {1, 1, 2, 3}, S=4
    Output: 3
    The given set has '3' subsets whose sum is '4': {1, 1, 2}, {1, 3}, {1, 3}
    Note that we have two similar sets {1, 3}, because we have two '1' in our input.
    Example 2: #
    Input: {1, 2, 7, 1, 5}, S=9
    Output: 3
    The given set has '3' subsets whose sum is '9': {2, 7}, {1, 7, 1}, {1, 2, 1, 5}
     */

    public int countSubsets(int[] num, int sum) {
        int n = num.length;
        int[][] dp = new int[n+1][sum+1];

        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i-1][j];
                if (j >= num[i-1]) {
                    dp[i][j] += dp[i-1][j-num[i-1]];
                }
            }
        }

        return dp[n][sum];
    }

    public static void main(String[] args) {
        CountOfSubsetSum ss = new CountOfSubsetSum();
        int[] num = {1, 1, 2, 3};
        System.out.println(ss.countSubsets(num, 4));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(ss.countSubsets(num, 9));
    }
}
