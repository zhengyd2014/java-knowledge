package com.watercup.datastructure.array;

import java.util.*;

public class Permutations {

    public static List<List<Integer>> findPermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // TODO: Write your code here
        boolean[] visited = new boolean[nums.length];
        dfs(nums, new ArrayList<Integer>(), visited, result);
        return result;
    }

    public static void dfs(int[] nums, List<Integer> path, boolean[] visited, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;

            path.add(nums[i]);
            visited[i] = true;
            dfs(nums, path, visited, result);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result = Permutations.findPermutations(new int[] { 1, 3, 5 });
        System.out.print("Here are all the permutations: " + result);
    }
}
