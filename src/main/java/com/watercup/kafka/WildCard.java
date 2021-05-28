package com.watercup.kafka;
import org.junit.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WildCard {

    public static boolean isMatch(String s, String p) {
        String[] parts = p.split("\\*");
        System.out.println("p size: " + parts.length + ":" +  Arrays.toString((Object[])parts));
        int i = 0;
        if (p.charAt(0) != '*' && !s.startsWith(parts[0])) return false;
        if (p.charAt(p.length()-1) != '*' && !s.endsWith(parts[parts.length-1])) return false;

        for (String part : parts) {
            int index = s.indexOf(part, i);
            if (index == -1) return false;
            i = index + part.length();
        }

        return true;
    }

    public static boolean isMatch4(String s, String p) {
        if (s == null || p == null) return s == p;

        int m = s.length();
        int n = p.length();

        boolean[][] match = new boolean[m+1][n+1];
        match[0][0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j-1) == '*') match[0][j] = match[0][j-1];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
                    match[i][j] = match[i-1][j-1];
                } else if (p.charAt(j-1) == '*') {
                    match[i][j] = match[i-1][j] || match[i][j-1];
                } else {
                    match[i][j] = false;
                }
            }
        }

        return match[m][n];
    }


    public static boolean isMatch3(String s, String p) {
        int state = 0, R = 256, M = p.length();
        // construct the NFA based on pattern p
        int[][] nfa = new int[R][M];
        for (int i = 0; i < R; i ++) {
            for (int j = 0; j < M; j ++) {
                nfa[i][j] = -1; // -1 means the transition ends
            }
        }
        for (char c : p.toCharArray()) {
            nfa[c][state] = c == '*' ? state : ++state;
        }
        // running the NFA on string s
        Set<Integer> states = new HashSet<>();
        states.add(0);
        for (char c : s.toCharArray()) {
            Set<Integer> tmp = new HashSet<>();
            for (Integer at : states) {
                // if (at >= M) continue; // in case we cross the boundary of the array
//                tmp.add(nfa[c][at]);
//                tmp.add(nfa['*'][at]);
//                tmp.add(nfa['?'][at]);
                if (nfa[c][at] != -1) tmp.add(nfa[c][at]);
                if (nfa['*'][at] != -1) tmp.add(nfa['*'][at]);
            }
//            if (tmp.contains(-1)) {
//                tmp.remove(-1);
//            }
            states = tmp;
        }
        return states.contains(state);
    }

    public static boolean isMatch2(String v, String pattern) {
        int s=0, p=0, newS = -1, newP = -1;

        while(s<v.length()){
            if(p<pattern.length() && v.charAt(s) == pattern.charAt(p)){
                s++;
                p++;
                continue;
            }
            if(p<pattern.length() && pattern.charAt(p) == '*')      {
                newS = s; newP=p++;
                continue;
            }
            if(newP != -1){ // we have *
                p = newP++;
                s = ++newS;
                continue;
            }
            return false;

        }
        // System.out.println("s = "+s+", v.length() = "+v.length()+", p = "+p+", pattern.length() "+pattern.length());
        while(p<pattern.length() && pattern.charAt(p) == '*') p++;
        return s== v.length() && p==pattern.length();
    }




    public static void main(String[] args) {
        boolean res = isMatch("cat", "c*t");
        System.out.println("cat c*t " + res);
        Assert.assertTrue(res);

        res = isMatch("cat", "*t");
        System.out.println("cat *t " + res);
        Assert.assertTrue(res);

        res = isMatch("dog", "c*t");
        System.out.println("dog c*t " + res);
        Assert.assertFalse(res);


        res = isMatch("cat", "*t*a*c*");
        System.out.println("cat  *t*a*c* " + res);
        Assert.assertFalse(res);


        res = isMatch("catcatcat", "cat*cat*cat***");
        System.out.println("catcatcat  cat*cat*cat*** " + res);
        Assert.assertTrue(res);

        res = isMatch("fdajhfjdsacatcatcatlsaflk", "****cat*cat*cat***");
        System.out.println("fdajhfjdsacatcatcatlsaflk  ****cat*cat*cat*** " + res);
        Assert.assertTrue(res);


        res = isMatch("catdog", "cat*cat*");
        System.out.println("catdog  cat*cat* " + res);
        Assert.assertFalse(res);


        res = isMatch("", "*");
        System.out.println(" *" + res);
        Assert.assertTrue(res);


        res = isMatch("cat", "cat*cat");
        System.out.println("cat cat*cat " + res);
        Assert.assertFalse(res);


        res = isMatch("catat", "ca*t");
        System.out.println("catat ca*t " + res);
        Assert.assertTrue(res);
    }
}
