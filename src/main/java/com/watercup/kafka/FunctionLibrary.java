package com.watercup.kafka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FunctionLibrary {
    TrieNode root = new TrieNode();
    public void register(Set<Function> functionSet)  {
        // implement this method.
        for (Function function : functionSet) {
            register(function);
        }
    }

    private void register(Function function) {
        TrieNode node = root;
        for (String argument: function.argumentTypes) {
            if (!node.children.containsKey(argument)) {
                node.children.put(argument, new TrieNode());
            }
            node = node.children.get(argument);
        }
        node.funcs.add(function);
    }

    public List<Function> findMatches(List<String> argumentTypes) {
        // implement this method.
        List<Function> candidates = new ArrayList<>();
        TrieNode node = root;

        for (String argument : argumentTypes) {
            if (node.children.containsKey(argument)) {
                node = node.children.get(argument);
                candidates.addAll(node.funcs);
            } else {
                break;
            }
        }

        return cleanup(candidates, argumentTypes);
    }

    private List<Function> cleanup(List<Function> functions, List<String> argumentTypes) {
        List<Function> matchs = new ArrayList<>();
        for (Function function : functions) {
            if (match(function, argumentTypes)) {
                matchs.add(function);
            }
        }

        return matchs;
    }

    private boolean match(Function function, List<String> argumentTypes) {
        List<String> funcArgs = function.argumentTypes;
        for (int i = 0; i < argumentTypes.size(); i++) {
            String curr = argumentTypes.get(i);
            String funcArg = null;
            if (i < funcArgs.size()) {
                funcArg = funcArgs.get(i);
            } else if (function.isVariadic) {
                funcArg = funcArgs.get(funcArgs.size() - 1);
            } else {
                return false;
            }

            if (!curr.equals(funcArg)) return false;
        }

        return true;
    }
}


class Function {
    String name;
    List<String> argumentTypes;
    boolean isVariadic;

    Function(String name, List<String> argumentTypes, boolean isVariadic) {
        this.name = name;
        this.argumentTypes = argumentTypes;
        this.isVariadic = isVariadic;
    }
}

class TrieNode {
    Map<String, TrieNode> children = new HashMap<>();
    Set<Function> funcs = new HashSet<>();
}
