package com.watercup.newsbreak;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HasCycle {
}

class Graph {
    private final int V;
    private final List<List<Integer>> adj;

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>(V);

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int dest) {
        adj.get(source).add(dest);
    }

    public boolean hasCycle() {
        boolean[] visited = new boolean[V];
        boolean[] onStack = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (visited[i]) continue;
            if (dfs(i, visited, onStack)) return true;
        }

        return false;
    }

    private boolean dfs(int i, boolean[] visited, boolean[] onStack) {
        visited[i] = true;
        onStack[i] = true;

        for (int w : adj.get(i)) {
            if (!visited[w]) {
                if (dfs(w, visited, onStack)) return true;
            } else {
                if (onStack[w]) return true;
            }
        }
        onStack[i] = false;
        return false;
    }

    public int countCycles() {
        boolean[] visited = new boolean[V];
        boolean[] onStack = new boolean[V];
        int[] edgeTo = new int[V];
        // Arrays.fill(edgeTo, -1);

        int count = 0;
        for (int i = 0; i < V; i++) {
            if (visited[i]) continue;
            count += countDFS(i, visited, onStack, edgeTo);
        }

        return count;
    }

    private int countDFS(int i, boolean[] visited, boolean[] onStack, int[] edgeTo) {
        int count = 0;
        visited[i] = true;
        onStack[i] = true;

        for (int w : adj.get(i)) {
            if (!visited[w]) {
                edgeTo[w] = i;
                count += countDFS(w, visited, onStack, edgeTo);
            } else {
                if (onStack[w]) {
                    count++;
                    printCycle(i, w, edgeTo);
                }
            }
        }
        onStack[i] = false;
        return count;
    }

    private void printCycle(int v, int w, int[] edgeTo) {
        Stack<Integer> stack = new Stack<>();

        for (int x = v; x != w; x = edgeTo[x])
            stack.push(x);
        stack.push(w);
        stack.push(v);

        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " -> ");
        }
        System.out.println();
    }

    // Driver code
    public static void main(String[] args)
    {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        //graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);
        graph.addEdge(2, 2);

        if(graph.hasCycle())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't "
                    + "contain cycle");

        System.out.println("cycle number: " + graph.countCycles());
    }
}