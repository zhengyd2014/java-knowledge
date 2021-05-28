package com.watercup.dynamicprogramming.knapsack;

public class MinimumSubsetSum {

    /*
    Given a set of positive numbers, partition the set into two subsets with minimum difference between their subset sums.

    Example 1: #
    Input: {1, 2, 3, 9}
    Output: 3
    Explanation: We can partition the given set into two subsets where minimum absolute difference
    between the sum of numbers is '3'. Following are the two subsets: {1, 2, 3} & {9}.
    Example 2: #
    Input: {1, 2, 7, 1, 5}
    Output: 0
    Explanation: We can partition the given set into two subsets where minimum absolute difference
    between the sum of number is '0'. Following are the two subsets: {1, 2, 5} & {7, 1}.
    Example 3: #
    Input: {1, 3, 100, 4}
    Output: 92
    Explanation: We can partition the given set into two subsets where minimum absolute difference
    between the sum of numbers is '92'. Here are the two subsets: {1, 3, 4} & {100}.
     */

    public int canPartition(int[] num) {
        int sum = 0;
        for (int v : num) sum += v;
        int targetSum = sum / 2;

        int n = num.length;
        boolean[][] dp = new boolean[n+1][targetSum+1];

        // populate the sum=0 columns, as we can always form '0' sum with an empty set
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= targetSum; j++) {
                dp[i][j] = dp[i-1][j];
                if (j >= num[i-1]) {
                    dp[i][j] |= dp[i-1][j - num[i-1]];
                }
            }
        }

        // Find the largest index in the last row which is true
        int s1 = 0;
        for (int j = targetSum; j >= 0; j--) {
            if (dp[n][j]) {
                s1 = j;
                break;
            }
        }

        int s2 = sum - s1;
        return Math.abs(s2 - s1);
    }


    public static void main(String[] args) {
        MinimumSubsetSum ps = new MinimumSubsetSum();
        int[] num = {1, 2, 3, 9};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 3, 100, 4};
        System.out.println(ps.canPartition(num));
    }
}
