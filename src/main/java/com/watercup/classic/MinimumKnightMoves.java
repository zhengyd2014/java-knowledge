package com.watercup.classic;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinimumKnightMoves {
    /*
    In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

    A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction,
    then one square in an orthogonal direction.

    Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.

    Example 1:

    Input: x = 2, y = 1
    Output: 1
    Explanation: [0, 0] → [2, 1]
    Example 2:

    Input: x = 5, y = 5
    Output: 4
    Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
     */

    int[][] directions = new int[][]{{-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {-2, -1}, {-2, 1}, {2, -1}, {2, 1}};

    public int minKnightMoves(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer(new int[]{0, 0});
        visited.add("0-0");

        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                if (curr[0] == x && curr[1] == y) {
                    return step;
                }

                for (int[] dir : directions) {
                    int nx = curr[0] + dir[0];
                    int ny = curr[1] + dir[1];
                    String s = nx + "-" + ny;
                    if (visited.contains(s)) continue;

                    visited.add(s);
                    q.offer(new int[] {nx, ny});
                }
            }

            step++;
        }

        return -1;
    }


    public int minKnightMovesBidirection(int x, int y) {

        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        Set<String> visited = new HashSet<>();

        q1.add(encode(new int[] {0, 0}));
        q2.add(encode(new int[] {x, y}));
        visited.add(encode(new int[] {0, 0}));
        visited.add(encode(new int[] {x, y}));

        int step = 0;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            Set<String> temp = new HashSet<>();
            for (String item : q1) {
                if (q2.contains(item)) return step;

                int[] curr = decode(item);
                for (int[] dir : directions) {
                    int nx = curr[0] + dir[0];
                    int ny = curr[1] + dir[1];
                    String s = encode(new int[]{nx, ny});
                    if (visited.contains(s)) continue;
                    visited.add(s);
                    temp.add(s);
                }

                step++;
                q1 = q2;
                q2 = temp;
            }
        }

        return -1;
    }

    private int[] decode(String s) {
        String[] item = s.split("-");
        return new int[] {Integer.valueOf(item[0]), Integer.valueOf(item[1])};
    }

    private String encode(int[] point) {
        return point[0] + "-" + point[1];
    }
}
