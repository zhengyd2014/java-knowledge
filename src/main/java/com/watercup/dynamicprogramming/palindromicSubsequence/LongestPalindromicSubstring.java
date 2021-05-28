package com.watercup.dynamicprogramming.palindromicSubsequence;

public class LongestPalindromicSubstring {
    /*
    Given a string, find the length of its Longest Palindromic Substring (LPS). In a palindromic string, elements read the same backward and forward.

    Example 1:

    Input: "abdbca"
    Output: 3
    Explanation: LPS is "bdb".
    Example 2:

    Input: = "cddpd"
    Output: 3
    Explanation: LPS is "dpd".
    Example 3:

    Input: = "pqr"
    Output: 1
    Explanation: LPS could be "p", "q" or "r".

    solution:
    for every endIndex and startIndex in the given string, we need to check the following thing:

    If the element at the startIndex matches the element at the endIndex, we will further
    check if the remaining substring (from startIndex+1 to endIndex-1) is a substring too.
     */

    public int findLPSLength(String st) {
        // dp[i][j] will be 'true' if the string from index 'i' to index 'j' is a
        // palindrome
        boolean[][] dp = new boolean[st.length()][st.length()];

        // every string with one character is a palindrome
        for (int i = 0; i < st.length(); i++)
            dp[i][i] = true;

        int maxLength = 1;
        for (int startIndex = st.length() - 1; startIndex >= 0; startIndex--) {
            for (int endIndex = startIndex + 1; endIndex < st.length(); endIndex++) {
                if (st.charAt(startIndex) == st.charAt(endIndex)) {
                    // if it's a two character string or if the remaining string is a palindrome too
                    if (endIndex - startIndex == 1 || dp[startIndex + 1][endIndex - 1]) {
                        dp[startIndex][endIndex] = true;
                        maxLength = Math.max(maxLength, endIndex - startIndex + 1);
                    }
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
        System.out.println(lps.findLPSLength("abdbca"));
        System.out.println(lps.findLPSLength("cdpdd"));
        System.out.println(lps.findLPSLength("pqr"));
    }
}
