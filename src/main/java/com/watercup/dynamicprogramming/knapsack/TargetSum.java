package com.watercup.dynamicprogramming.knapsack;

public class TargetSum {

    /*
    You are given a set of positive numbers and a target sum ‘S’.
    Each number should be assigned either a ‘+’ or ‘-’ sign.
    We need to find the total ways to assign symbols to make the
    sum of the numbers equal to the target ‘S’.

    Example 1: #
    Input: {1, 1, 2, 3}, S=1
    Output: 3
    Explanation: The given set has '3' ways to make a sum of '1': {+1-1-2+3} & {-1+1-2+3} & {+1+1+2-3}
    Example 2: #
    Input: {1, 2, 7, 1}, S=9
    Output: 2
    Explanation: The given set has '2' ways to make a sum of '9': {+1+2+7-1} & {-1+2+7+1}
     */

    public int findTargetSubsets(int[] num, int s) {

        /*
            equals to find 2 subsets s1 and s2 that:
            #1. sum(s1) - sum(s2) = s
            #2. sum(s1) - sum(s2) = sum(num)

            => #1 + #2 => sum(s1) = (s + sum(num)) / 2
            find count of subset with sum is (s + sum(num)) / 2
         */

        int sum = 0;
        for (int v : num) sum += v;
        if ((s + sum) % 2 == 1) return 0;
        int targetSum = (s + sum) / 2;

        int n = num.length;
        int[] dp = new int[targetSum+1];

        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = targetSum; j >= 0; j--) {
                if (j >= num[i-1]) {
                    dp[j] += dp[j-num[i-1]];
                }
            }
        }

        return dp[targetSum];
    }

    public static void main(String[] args) {
        TargetSum ts = new TargetSum();
        int[] num = {1, 1, 2, 3};
        System.out.println(ts.findTargetSubsets(num, 1));
        num = new int[]{1, 2, 7, 1};
        System.out.println(ts.findTargetSubsets(num, 9));
    }

}
