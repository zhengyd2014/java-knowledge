package com.watercup.classic;

public class ReadNGivenRead4MultipCalls {

    /*
    Given a file and assume that you can only read the file using a given method read4,
    implement a method read to read n characters. Your method read may be called multiple times.
     */

    char[] cache = new char[4];
    int start = 0;
    int end = 0;

    public int read(char[] buf, int n) {
        int bufIdx = 0;

        while(true) {
            if (start == end) {
                end = read4(cache);
                start = 0;
            }

            while (start < end && bufIdx < n) {
                buf[bufIdx++] = cache[start++];
            }

            if (end == 0 || bufIdx == n) break;
        }

        return bufIdx;
    }


    public int read4(char[] temp) {
        return 0;
    }
}
