package com.watercup.dynamicprogramming.knapsack;

public class EqualSubsetSum {

    /*
    Given a set of positive numbers, find if we can partition it into two subsets such that the sum of elements in both subsets is equal.

    Example 1:

    Input: {1, 2, 3, 4}
    Output: True
    Explanation: The given set can be partitioned into two subsets with equal sum: {1, 4} & {2, 3}
    Example 2:

    Input: {1, 1, 3, 4, 7}
    Output: True
    Explanation: The given set can be partitioned into two subsets with equal sum: {1, 3, 4} & {1, 7}
    Example 3:

    Input: {2, 3, 4, 6}
    Output: False
    Explanation: The given set cannot be partitioned into two subsets with equal sum.
     */

    static boolean canPartition(int[] num) {
        int total = 0;
        if (num.length <= 1) return false;
        for (int n : num) total += n;
        if (total % 2 != 0) return false;

        int targetSum = total / 2;
        boolean[][] dp = new boolean[num.length+1][targetSum+1];

        // populate the sum=0 columns, as we can always for '0' sum with an empty set
        for (int i = 0; i <= num.length; i++)
            dp[i][0] = true;

        for (int i = 1; i <= num.length; i++) {
            for (int j = 1; j <= targetSum; j++) {
                dp[i][j] = dp[i-1][j];
                if (num[i-1] <= j) {
                    dp[i][j] |= dp[i-1][j-num[i-1]];
                }
            }
        }

        return dp[num.length][targetSum];
    }

    public static void main(String[] args) {
        EqualSubsetSum ps = new EqualSubsetSum();
        int[] num = {1, 2, 3, 4};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 1, 3, 4, 7};
        System.out.println(ps.canPartition(num));
        num = new int[]{2, 3, 4, 6};
        System.out.println(ps.canPartition(num));
    }
}
