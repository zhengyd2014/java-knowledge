package com.watercup.dynamicprogramming.fibonacci;

public class MinimumJumps {
    /*
    Given an array of positive numbers, where each element represents the max number of steps
    that can be made forward from that element, write a program to find the minimum number of jumps
    needed to reach the end of the array (starting from the first element).

    If an element is 0, then we cannot move through that element.

    Example 1:

    Input = {2,1,1,1,4}
    Output = 3
    Explanation: Starting from index '0', we can reach the last index through: 0->2->3->4
    Example 2:

    Input = {1,1,3,6,9,3,0,1,3}
    Output = 4
    Explanation: Starting from index '0', we can reach the last index through: 0->1->2->3->8
     */

    public int countMinJumps(int[] jumps) {
        int n = jumps.length;
        int[] dp = new int[n];

        for (int i = 1; i < n; i++)
            dp[i] = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (jumps[j] + j >= i)
                    dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }

        return dp[n-1];
    }

    public static void main(String[] args) {
        MinimumJumps aj = new MinimumJumps();
        int[] jumps = {2, 1, 1, 1, 4};
        System.out.println(aj.countMinJumps(jumps));
        jumps = new int[]{1, 1, 3, 6, 9, 3, 0, 1, 3};
        System.out.println(aj.countMinJumps(jumps));
    }
}
