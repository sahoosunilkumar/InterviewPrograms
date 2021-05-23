package com.sunilsahoo.trie;

public class LongestCommonPrefix {

    public static void main(String[] args) {
        LongestCommonPrefix lcp = new LongestCommonPrefix();
        String[] strs = {"flower","flow","flight"};
        System.out.println(lcp.longestCommonPrefix(strs));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        if (strs.length == 1)
            return strs[0];
        String q = strs[0];
        Trie trie = new Trie();
        for (int i = 1; i < strs.length ; i++) {
            trie.insert(strs[i]);
        }
        return trie.searchLongestPrefix(q);
    }

    class TrieNode {

        // R links to node children

        private final int ALPHABET_SIZE = 26;
        private TrieNode[] links = new TrieNode[ALPHABET_SIZE];


        private boolean isEnd;

        // number of children non null links
        private int size;
        public void put(char ch, TrieNode node) {
            links[ch -'a'] = node;
            size++;
        }

        public int getLinks() {
            return size;
        }

        public boolean containsKey(char ch) {
            return links[ch -'a'] != null;
        }
        public TrieNode get(char ch) {
            return links[ch -'a'];
        }

        public void setEnd() {
            isEnd = true;
        }
        public boolean isEnd() {
            return isEnd;
        }

    }

     class Trie {

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        private String searchLongestPrefix(String word) {
            TrieNode node = root;
            StringBuilder prefix = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char curLetter = word.charAt(i);
                if (node.containsKey(curLetter) && (node.getLinks() == 1) && (!node.isEnd())) {
                    prefix.append(curLetter);
                    node = node.get(curLetter);
                }
                else
                    return prefix.toString();

            }
            return prefix.toString();
        }

         public void insert(String word) {
             TrieNode node = root;
             for (int i = 0; i < word.length(); i++) {
                 char currentChar = word.charAt(i);
                 if (!node.containsKey(currentChar)) {
                     node.put(currentChar, new TrieNode());
                 }
                 node = node.get(currentChar);
             }
             node.setEnd();
         }
    }
}
