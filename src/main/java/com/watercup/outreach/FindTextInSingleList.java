package com.watercup.outreach;

import org.junit.Assert;

public class FindTextInSingleList {

    /*
        给一个单链表，每一个node都含有一个word。另外给一段长的text，问能不能在这个单链表中顺序找到给定的这个text。
     */

    public static boolean textInList(ListNode head, String text) {
        boolean[] dp = new boolean[text.length() + 1];
        dp[0] = true;

        while (head != null) {
            int fromIndex = 0;
            int idx = -1;
            while(true) {
                idx = indexOf(text, head.val, fromIndex);
                if (idx == -1) break;

                int end = idx + head.val.length();
                if (dp[idx]) {
                    dp[end] = true;
                }
                fromIndex = end;
            }
            head = head.next;
        }

        printBooleanArray(dp);
        return dp[text.length()];
    }

    public static int indexOf(String text, String sub, int fromIndex) {
        // return text.indexOf(sub, fromIndex);

        int idx = search(text.substring(fromIndex), sub);
        if (idx == -1) return -1;
        return fromIndex + idx;
    }

    public static int search(String text, String pattern) {
        int M = pattern.length();
        int N = text.length();
        for (int i = 0; i <= N - M; i++) {
            int j = 0;
            for (; j < M; j++) {
                if (text.charAt(i+j) != pattern.charAt(j)) {
                    break;
                }
            }

            if (j == M) return i;
        }

        return -1;
    }

    public static void main(String[] args) {
        ListNode words = ListNode.buildList(new String[] {"this", "that", "th", "isa", "book"});
        Assert.assertTrue(textInList(words, "thisabook"));

        words = ListNode.buildList(new String[] {"is", "this", "that", "th", "is", "a", "book"});
        Assert.assertTrue(textInList(words, "thisisabook"));
    }

    public static void printBooleanArray(boolean[] dp) {
        System.out.print("[");
        for (boolean b : dp) {
            System.out.print(b + " ");
        }
        System.out.println("]");
    }

}

class ListNode {
    String val;
    ListNode next;

    public ListNode(String val) {
        this.val = val;
    }

    public static ListNode buildList(String[] words) {
        ListNode dummy = new ListNode("");
        ListNode node = dummy;
        for (String w : words) {
            node.next = new ListNode(w);
            node = node.next;
        }

        return dummy.next;
    }
}
