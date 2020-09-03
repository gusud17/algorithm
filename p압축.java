//package com.gusud;
//
//import java.util.*;
//
//class p압축 {
//    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new Solution().solution("KAKAO")));
//        System.out.println(Arrays.toString(new Solution().solution("TOBEORNOTTOBEORTOBEORNOT")));
//        System.out.println(Arrays.toString(new Solution().solution("ABABABABABABABAB")));
//    }
//}
//
//class Solution {
//
//    Map<String, Integer> dictionary = new HashMap<>();
//
//    public int[] solution(String msg) {
//        initDictionary();
//
//        List<Integer> compressed = new ArrayList<>();
//
//        int nextVal = 27;
//        for (int i = 0; i < msg.length(); ) {
//            int from = i, to = i + 2;
//
//            if (to <= msg.length()) {
//                while (to <= msg.length() && dictionary.containsKey(msg.substring(from, to))) {
//                    to++;
//                }
//
//                if (to <= msg.length()) {
//                    dictionary.put(msg.substring(from, to--), nextVal++);
//                }
//            }
//
//            if (to > msg.length()) to = msg.length();
//            compressed.add(dictionary.get(msg.substring(from, to)));
//            i = to;
//        }
//
//        return listToIntArr(compressed);
//    }
//
//    private int[] listToIntArr(List<Integer> compressed) {
//        int[] arr = new int[compressed.size()];
//
//        for (int i = 0; i < compressed.size(); i++) {
//            arr[i] = compressed.get(i);
//        }
//
//        return arr;
//    }
//
//    private void initDictionary() {
//        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//
//        for (int i = 0; i < 26; i++) {
//            dictionary.put(String.valueOf(alphabet.charAt(i)), i + 1);
//        }
//    }
//}
