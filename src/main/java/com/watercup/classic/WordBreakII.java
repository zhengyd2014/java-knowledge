package com.watercup.classic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class WordBreakII {

    /*
    Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence
     where each word is a valid dictionary word. Return all such possible sentences in any order.

    Note that the same word in the dictionary may be reused multiple times in the segmentation.

    Example 1:

    Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
    Output: ["cats and dog","cat sand dog"]
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return new ArrayList<String>();
        Map<String, List<String>> memo = new HashMap<>();
        Set<String> dict = new HashSet<String>(wordDict);
        return dfs(s, dict, memo);
    }

    public List<String> dfs(String s, Set<String> dict, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) return memo.get(s);
        List<String> result = new ArrayList<>();
        if (s.length() == 0) return result;

        if (dict.contains(s)) result.add(s);

        for (int i = 0; i < s.length(); i++) {
            String w = s.substring(0, i);
            if (dict.contains(w)) {
                String rest = s.substring(i);
                List<String> subList = dfs(rest, dict, memo);
                for (String sub : subList) {
                    result.add(w + " " + sub);
                }
            }
        }

        return result;
    }
}
