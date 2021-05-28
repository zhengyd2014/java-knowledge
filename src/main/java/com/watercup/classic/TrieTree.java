package com.watercup.classic;

import java.util.HashMap;
import java.util.Map;

public class TrieTree {
    TrieNode root;
    public TrieTree() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
        }

        node.isWord = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return false;
            }
            node = node.children.get(c);
        }

        return node.isWord;
    }

    public boolean searchWithWildCard(String word) {
        return search(root, word, 0);
    }

    private boolean search(TrieNode node, String word, int index) {
        if (node == null) return false;
        if (index == word.length()) return node.isWord;

        char c = word.charAt(index);
        if (c == '.') {
            for (TrieNode child : node.children.values()) {
                if (search(child, word, index + 1)) return true;
            }
        } else if (node.children.containsKey(c)) {
            node = node.children.get(c);
            return search(node, word, index+1);
        }

        return false;
    }

    // Driver
    public static void main(String args[])
    {
        // Input keys (use only 'a' through 'z' and lower case)
        String keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};

        String output[] = {"Not present in trie", "Present in trie"};


        TrieTree tree = new TrieTree();

        // Construct trie
        int i;
        for (i = 0; i < keys.length ; i++)
            tree.insert(keys[i]);

        // Search for different keys
        if(tree.search("the") == true)
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);

        if(tree.search("these") == true)
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);

        if(tree.search("their") == true)
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);

        if(tree.search("thaw") == true)
            System.out.println("thaw --- " + output[1]);
        else System.out.println("thaw --- " + output[0]);


        if(tree.searchWithWildCard("t.eir") == true)
            System.out.println("t.eir --- " + output[1]);
        else System.out.println("t.eir --- " + output[0]);

    }
}

class TrieNode {
    public Map<Character, TrieNode> children;
    public boolean isWord;

    public TrieNode() {
        this.children = new HashMap<>();
        this.isWord = false;
    }
}
