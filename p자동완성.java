package com.company;

import java.util.Arrays;

public class p자동완성 {

    public static void main(String[] args) {
        System.out.println((int) 'a');
        System.out.println((int) 'z');
        System.out.println(new Solution().solution(new String[]{"go", "gone", "guild"}));
        System.out.println(new Solution().solution(new String[]{"abc", "def", "ghi", "jklm"}));
        System.out.println(new Solution().solution(new String[]{"word", "war", "warrior", "world"}));
    }
}

class Solution {
    public int solution(String[] words) {
        Trie trie = new Trie();

        Arrays.stream(words)
                .forEach(trie::insert);

        return Arrays.stream(words)
                .mapToInt(trie::find)
                .sum();
    }
}

class Trie {
    TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            cur.descendantsCnt++;
            if (cur.getChild(c) == null) {
                cur = cur.addChild(c);
            } else {
                cur = cur.getChild(c);
            }
        }
        cur.setLast();
    }

    public int find(String word) {
        TrieNode cur = root;
        int cnt = 0;
        for (char c : word.toCharArray()) {
            cur = cur.getChild(c);
            cnt++;
            if (!cur.isLast && cur.descendantsCnt == 1) {
                break;
            }
        }
        return cnt;
    }

    class TrieNode {
        private TrieNode[] children = new TrieNode[26];
        private int descendantsCnt = 0;
        private boolean isLast = false;

        public TrieNode getChild(char c) {
            return children[c - 'a'];
        }

        public TrieNode addChild(char c) {
            children[c - 'a'] = new TrieNode();
            return children[c - 'a'];
        }

        public void setLast() {
            isLast = true;
        }
    }
}
