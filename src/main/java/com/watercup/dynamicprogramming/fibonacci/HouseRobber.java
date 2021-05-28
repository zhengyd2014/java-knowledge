package com.watercup.dynamicprogramming.fibonacci;

public class HouseRobber {

    /*
    Given a number array representing the wealth of ‘n’ houses,
    determine the maximum amount of money the thief can steal without alerting the security system.

    can’t steal from two consecutive houses

    Example 1:

    Input: {2, 5, 1, 3, 6, 2, 4}
    Output: 15
    Explanation: The thief should steal from houses 5 + 6 + 4
    Example 2:

    Input: {2, 10, 14, 8, 1}
    Output: 18
    Explanation: The thief should steal from houses 10 + 8
     */

    public int findMaxSteal(int[] wealth) {
        if(wealth.length == 0) return 0;
        int dp[] = new int[wealth.length+1]; // '+1' to handle the zero house
        dp[0] = 0; // if there are no houses, the thief can't steal anything
        dp[1] = wealth[0]; // only one house, so the thief have to steal from it

        // please note that dp[] has one extra element to handle zero house
        for(int i=1; i < wealth.length; i++)
            dp[i+1] = Math.max(wealth[i] + dp[i-1], dp[i]);

        return dp[wealth.length];
    }

    public static void main(String[] args) {
        HouseRobber ht = new HouseRobber();
        int[] wealth = {2, 5, 1, 3, 6, 2, 4};
        System.out.println(ht.findMaxSteal(wealth));
        wealth = new int[]{2, 10, 14, 8, 1};
        System.out.println(ht.findMaxSteal(wealth));
    }
}
