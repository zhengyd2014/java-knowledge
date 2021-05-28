package com.watercup.kafka;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestFunctionLibrary {

    @Test
    public void testFindMatch() {

        FunctionLibrary functionLibrary = new FunctionLibrary();

        Set<Function> functionSet = new HashSet<>();
        functionSet.add(new Function("funcA", Arrays.asList("String", "Integer", "Integer"), false));
        functionSet.add(new Function("funcB", Arrays.asList("String", "Integer"), true));
        functionSet.add(new Function("funcC", Arrays.asList("Integer"), true));
        functionSet.add(new Function("funcD", Arrays.asList("Integer", "Integer"), true));
        functionSet.add(new Function("funcE", Arrays.asList("Integer", "Integer", "Integer"), false));
        functionSet.add(new Function("funcF", Arrays.asList("String"), false));
        functionSet.add(new Function("funcG", Arrays.asList("Integer"), false));
        functionLibrary.register(functionSet);

        printList(functionLibrary.findMatches(Arrays.asList("String")));    // funcF
        printList(functionLibrary.findMatches(Arrays.asList("Integer")));   //  [FuncC, FuncG]
        printList(functionLibrary.findMatches(Arrays.asList("Integer","Integer", "Integer", "Integer"))); // funcC, funcD
        printList(functionLibrary.findMatches(Arrays.asList("Integer","Integer", "Integer")));            // [FuncC, FuncD, FuncE]
        printList(functionLibrary.findMatches(Arrays.asList("String","Integer", "Integer", "Integer")));  // [FuncB]
        printList(functionLibrary.findMatches(Arrays.asList("String","Integer", "Integer")));             // [FuncA, FuncB]
    }

    private void printList(List<Function> list) {
        System.out.println(" ======== ");
        for (Function func : list) {
            System.out.println(func.name);
        }
    }
}
