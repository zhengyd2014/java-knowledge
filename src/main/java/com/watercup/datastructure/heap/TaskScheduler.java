package com.watercup.datastructure.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TaskScheduler {

    /*
    You are given a list of tasks that need to be run, in any order, on a server.
    Each task will take one CPU interval to execute but once a task has finished,
    it has a cooling period during which it can’t be run again. If the cooling
    period for all tasks is ‘K’ intervals, find the minimum number of CPU intervals
    that the server needs to finish all tasks.

    If at any time the server can’t execute any task then it must stay idle.

    Example 1:

    Input: [a, a, a, b, c, c], K=2
    Output: 7
    Explanation: a -> c -> b -> a -> c -> idle -> a

    Example 2:

    Input: [a, b, a], K=3
    Output: 5
    Explanation: a -> b -> idle -> idle -> a
     */

    public static int scheduleTasks(char[] tasks, int k) {
        int intervalCount = 0;
        Map<Character, Integer> taskFreqMap = new HashMap<>();

        for (char chr : tasks) {
            taskFreqMap.put(chr, taskFreqMap.getOrDefault(chr, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<Character>((a, b) -> taskFreqMap.get(b) - taskFreqMap.get(b));
        maxHeap.addAll(taskFreqMap.keySet());

        while(!maxHeap.isEmpty()) {
            List<Character> waitList = new ArrayList<>();
            int n = k+1;    // try to execute as many as 'k+1' tasks from the max-heap
            while (n > 0 && !maxHeap.isEmpty()) {
                char currTask = maxHeap.poll();
                intervalCount++;
                n--;

                if (taskFreqMap.get(currTask) > 1) {
                    taskFreqMap.put(currTask, taskFreqMap.get(currTask) - 1);
                    waitList.add(currTask);
                } else {
                    taskFreqMap.remove(currTask);
                }
            }

            maxHeap.addAll(waitList);  // put all the waiting list back on the heap
            if (!maxHeap.isEmpty())
                intervalCount += n;    // we'll be having 'n' idle intervals for the next iteration
        }

        return intervalCount;
    }

    public static void main(String[] args) {
        char[] tasks = new char[] { 'a', 'a', 'a', 'b', 'c', 'c' };
        System.out.println("Minimum intervals needed to execute all tasks: " + TaskScheduler.scheduleTasks(tasks, 2));

        tasks = new char[] { 'a', 'b', 'a' };
        System.out.println("Minimum intervals needed to execute all tasks: " + TaskScheduler.scheduleTasks(tasks, 3));
    }
}
