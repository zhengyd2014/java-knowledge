package com.watercup.classic;

public class ReadNGivenRead4 {

    /*
    The API: int read4(char *buf) reads 4 characters at a time from a file.
    The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
    By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
     */

    public int read(char[] buf, int n) {
        char[] temp = new char[4];
        int bufIdx = 0;

        while (true) {
            int read = read4(temp);
            int start = 0;
            while (start < read && bufIdx < n) {
                buf[bufIdx++] = temp[start++];
            }

            if (read == 0 || bufIdx == n) break;
        }

        return bufIdx;
    }


    public int read4(char[] temp) {
        return 0;
    }
}
