package com.watercup.newsbreak;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiPartition {

    /*
    Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
    Each person may dislike some other people, and they should not go into the same group.
    Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
    Return true if and only if it is possible to split everyone into two groups in this way.

    Example 1:

    Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
    Output: true
    Explanation: group1 [1,4], group2 [2,3]
    Example 2:

    Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
    Output: false
    Example 3:

    Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
    Output: false
     */

    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, List<Integer>> graph = buildGraph(dislikes);
        boolean[] visited = new boolean[N + 1];
        boolean[] color = new boolean[N + 1];

        for (int p = 1; p <= N; p++) {
            if (visited[p]) continue;
            if (!dfs(graph, visited, color, p)) return false;
        }
        return true;
    }

    private Map<Integer, List<Integer>> buildGraph(int[][] dislikes) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] pair : dislikes) {
            int p1 = pair[0];
            int p2 = pair[1];
            graph.putIfAbsent(p1, new ArrayList<Integer>());
            graph.putIfAbsent(p2, new ArrayList<>());
            graph.get(p1).add(p2);
            graph.get(p2).add(p1);
        }
        return graph;
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, boolean[] visited, boolean[] color, int p) {
        visited[p] = true;

        if (!graph.containsKey(p)) return true;

        for (int neighbor : graph.get(p)) {
            if (visited[neighbor]) {
                if (color[neighbor] == color[p]) return false;
            } else {
                color[neighbor] = !color[p];
                if (!dfs(graph, visited, color, neighbor)) return false;
            }
        }

        return true;
    }
}
