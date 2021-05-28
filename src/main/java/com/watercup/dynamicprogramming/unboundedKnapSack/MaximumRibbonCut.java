package com.watercup.dynamicprogramming.unboundedKnapSack;

public class MaximumRibbonCut {
    /*
    We are given a ribbon of length ‘n’ and a set of possible ribbon lengths.
    We need to cut the ribbon into the maximum number of pieces that comply with
    the above-mentioned possible lengths. Write a method that will return the count of pieces.

    Example 1:

    n: 5
    Ribbon Lengths: {2,3,5}
    Output: 2
    Explanation: Ribbon pieces will be {2,3}.
    Example 2:

    n: 7
    Ribbon Lengths: {2,3}
    Output: 3
    Explanation: Ribbon pieces will be {2,2,3}.
    Example 3:

    n: 13
    Ribbon Lengths: {3,5,7}
    Output: 3
    Explanation: Ribbon pieces will be {3,3,7}.

    Problem Statement #
    Given a number array to represent possible ribbon lengths and a total ribbon length ‘n,’
    we need to find the maximum number of pieces that the ribbon can be cut into.

    This problem follows the Unbounded Knapsack pattern and is quite similar to Minimum Coin Change (MCC).
    The only difference is that in MCC, we were asked to find the minimum number of coin changes,
    whereas, in this problem, we need to find the maximum number of pieces.
     */

    public int countRibbonPieces(int[] ribbonLengths, int total) {

        // skip parameter validation

        int n = ribbonLengths.length;
        int[][] dp = new int[n+1][total+1];

        //base case
        for(int i=0; i < n; i++)
            for(int j=0; j <= total; j++)
                dp[i][j] = Integer.MIN_VALUE;
        // populate the total=0 columns, as we don't need any ribbon to make zero total
        for(int i=0; i < n; i++)
            dp[i][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= total; j++) {
                dp[i][j] = dp[i-1][j];
                if (j >= ribbonLengths[i-1] && dp[i][j - ribbonLengths[i-1]] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - ribbonLengths[i-1]] + 1);
                }
            }
        }

        return dp[n][total] == Integer.MIN_VALUE ? -1 : dp[n][total];
    }

    public static void main(String[] args) {
        MaximumRibbonCut cr = new MaximumRibbonCut();
        int[] ribbonLengths = {2,3,5};
        System.out.println(cr.countRibbonPieces(ribbonLengths, 5));
        ribbonLengths = new int[]{2,3};
        System.out.println(cr.countRibbonPieces(ribbonLengths, 7));
        ribbonLengths = new int[]{5,3,7};
        System.out.println(cr.countRibbonPieces(ribbonLengths, 13));
        ribbonLengths = new int[]{3,5};
        System.out.println(cr.countRibbonPieces(ribbonLengths, 7));
    }
}
