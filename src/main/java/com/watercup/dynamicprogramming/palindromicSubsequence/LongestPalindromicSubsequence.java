package com.watercup.dynamicprogramming.palindromicSubsequence;

import java.util.Arrays;

public class LongestPalindromicSubsequence {
    /*
    Given a sequence, find the length of its Longest Palindromic Subsequence (LPS).
    In a palindromic subsequence, elements read the same backward and forward.

    A subsequence is a sequence that can be derived from another sequence
    by deleting some or no elements without changing the order of the remaining elements.

    Example 1:
    Input: "abdbca"
    Output: 5
    Explanation: LPS is "abdba".

    Example 2:
    Input: = "cddpd"
    Output: 3
    Explanation: LPS is "ddd".

    Example 3:
    Input: = "pqr"
    Output: 1
    Explanation: LPS could be "p", "q" or "r".

    solution:
    So for every startIndex and endIndex in the given string, we will choose one of the following two options:

    1. If the element at the startIndex matches the element at the endIndex,
        the length of LPS would be two plus the length of LPS till startIndex+1 and endIndex-1.
    2. If the element at the startIndex does not match the element at the endIndex,
        we will take the maximum LPS created by either skipping element at the startIndex or the endIndex.
     */

    public int findLPSLength(String st) {

        int n = st.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++)
            dp[i][i] = 1;

        for (int i = n-1; i >= 0; i--) {
            for (int j = i+1; j < n; j++) {
                if (st.charAt(i) == st.charAt(j)) {
                    dp[i][j] = 2 + dp[i+1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }

        return dp[0][n-1];
    }

    public static void main(String[] args) {
        LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
        System.out.println(lps.findLPSLength("abdbca"));
        System.out.println(lps.findLPSLength("cddpd"));
        System.out.println(lps.findLPSLength("pqr"));
    }
}
