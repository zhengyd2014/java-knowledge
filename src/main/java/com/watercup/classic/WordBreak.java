package com.watercup.classic;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class WordBreak {

    /*
    Given a string s and a dictionary of strings wordDict, return true if s can be segmented into
    a space-separated sequence of one or more dictionary words.

    Note that the same word in the dictionary may be reused multiple times in the segmentation.

    Example 1:

    Input: s = "leetcode", wordDict = ["leet","code"]
    Output: true
    Explanation: Return true because "leetcode" can be segmented as "leet code".
     */

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return false;
        Set<String> words = new HashSet<String>(wordDict);

        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && words.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }

        return dp[s.length()];
    }

    public boolean wordBreakRecursive(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return false;

        Set<String> words = new HashSet<String>(wordDict);
        Map<String, Boolean> memo = new HashMap<>();
        return check(s, words, memo);
    }

    private boolean check(String s, Set<String> words, Map<String, Boolean> memo) {
        if (s.length() == 0) return true;

        if (memo.containsKey(s)) return memo.get(s);

        for (int i = 1; i <= s.length(); i++) {
            String segment = s.substring(0, i);
            if (words.contains(segment) && check(s.substring(i), words, memo)) return true;
        }

        memo.put(s, false);
        return false;
    }
}
