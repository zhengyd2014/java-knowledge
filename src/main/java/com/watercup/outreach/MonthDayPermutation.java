package com.watercup.outreach;

import java.util.ArrayList;
import java.util.List;

public class MonthDayPermutation {
    /*
    Write a function that prints all possible combinations for a lock with the
    following properties: combinations are 4 digits of the form MMDD; no digit
    occurs more than once.

    For a first implementation you can assume all months have 31 days.

    Valid combinations
    * 0123 - January 23
    * 0931 - September 31 - OK even though Sept doesn't have 31 days

    Invalid combinations
    * 0121 - January 21 has a repeated digit (1)
    * 0935 - No month has 35 days

    */

    private static String NUMBERS = "0123456789";

    public List<String> findMonthDayPermutation() {
        List<String> allCombinations = new ArrayList<>();
        boolean[] visited = new boolean[NUMBERS.length()];
        backtrack("", visited, allCombinations);
        return allCombinations;
    }

    private void backtrack(String path, boolean[] visited, List<String> result) {

        for (int i = 0; i < NUMBERS.length(); i++) {
            if (visited[i]) continue;
            if (path.length() >= 1 && path.charAt(0) >= '2') return;
            if (path.length() >= 2) {
                int month = Integer.valueOf(path.substring(0, 2));
                if (month > 12) return;
            }

            if (path.length() >= 3 && path.charAt(2) >= '4') return;
            if (path.length() == 4) {
                int day = Integer.valueOf(path.substring(2));
                if (day > 31) return;

                result.add(path);
                return;
            }

            visited[i] = true;
            backtrack(path + NUMBERS.charAt(i), visited, result);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        MonthDayPermutation combination = new MonthDayPermutation();
        List<String> allMMDD = combination.findMonthDayPermutation();
        for (String s : allMMDD) {
            System.out.println(s);
        }
    }

}