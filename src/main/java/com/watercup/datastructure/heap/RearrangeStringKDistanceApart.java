package com.watercup.datastructure.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class RearrangeStringKDistanceApart {
    /*
    Given a string and a number ‘K’, find if the string can be rearranged such that the same characters
    are at least ‘K’ distance apart from each other.

    Example 1:

    Input: "mmpp", K=2
    Output: "mpmp" or "pmpm"
    Explanation: All same characters are 2 distance apart.
    Example 2:

    Input: "Programming", K=3
    Output: "rgmPrgmiano" or "gmringmrPoa" or "gmrPagimnor" and a few more
    Explanation: All same characters are 3 distance apart.
    Example 3:

    Input: "aab", K=2
    Output: "aba"
    Explanation: All same characters are 2 distance apart.
    Example 4:

    Input: "aappa", K=3
    Output: ""
    Explanation: We cannot find an arrangement of the string where any two 'a' are 3 distance apart.
     */

    public static String reorganizeString(String str, int k) {
        if (k <= 1) return str;

        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : str.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        PriorityQueue<Character> maxHeap =
                new PriorityQueue<>((c1, c2) ->
                    charFrequencyMap.get(c2)- charFrequencyMap.get(c1)
                );

        StringBuilder resultString = new StringBuilder(str.length());

        maxHeap.addAll(charFrequencyMap.keySet());  // don't forget to add elements in heap

        int len = str.length();
        while (!maxHeap.isEmpty()) {
            ArrayList<Character> waitList = new ArrayList<Character>();
            int cnt = Math.min(k, len);
            for (int i = 0; i < cnt; i++) {
                if (maxHeap.isEmpty()) return "";

                char c = maxHeap.poll();
                resultString.append(c);

                charFrequencyMap.put(c, charFrequencyMap.get(c) - 1);
                if (charFrequencyMap.get(c) > 0) {
                    waitList.add(c);
                }
                len--;
            }

            for (char c : waitList)
                maxHeap.offer(c);
        }

        return resultString.toString();
    }

    public static void main(String[] args) {
        System.out.println("Reorganized string: " +
                RearrangeStringKDistanceApart.reorganizeString("mmpp", 2));
        System.out.println("Reorganized string: " +
                RearrangeStringKDistanceApart.reorganizeString("Programming", 3));
        System.out.println("Reorganized string: " +
                RearrangeStringKDistanceApart.reorganizeString("aab", 2));
        System.out.println("Reorganized string: " +
                RearrangeStringKDistanceApart.reorganizeString("aappa", 3));
    }
}
