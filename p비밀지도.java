package com.gusud;

import java.util.Arrays;

class p비밀지도 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28})));
        System.out.println(Arrays.toString(new Solution().solution(6, new int[]{46, 33, 33, 22, 31, 50}, new int[]{27, 56, 19, 14, 14, 10})));
    }

}

class Solution {

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            StringBuffer line = new StringBuffer();

            int num = arr1[i] | arr2[i];
            for (int j = 0; j < n; j++) {
                line.insert(0, convert2Symbol(num, j));
            }

            answer[i] = line.toString();
        }

        return answer;
    }

    private char convert2Symbol(int num, int j) {
        if (((num >> j) & 1) == 1) {
            return '#';
        }
        return ' ';
    }
}

