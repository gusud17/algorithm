package com.gusud;

class pN진수게임 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(2, 4, 2, 1));
        System.out.println(new Solution().solution(16, 16, 2, 1));
        System.out.println(new Solution().solution(16, 16, 2, 2));
    }
}

class Solution {
    public String solution(int n, int ansSize, int m, int p) {
        StringBuffer answer = new StringBuffer();
        StringBuffer numSeq = new StringBuffer();

        int num = 0;
        int numSeqIndex = p - 1;
        while (answer.length() < ansSize) {
            while (numSeqIndex >= numSeq.length()) {
                numSeq.append(toN(n, num));
                num += 1;
            }
            answer.append(numSeq.charAt(numSeqIndex));
            numSeqIndex += m;
        }

        return answer.toString();
    }

    private String toN(int n, int value) {
        if (value == 0) return "0";
        StringBuffer sb = new StringBuffer();

        while (value != 0) {
            int rem = value % n;

            if (rem > 9) {
                sb.insert(0, (char) (rem + 55));
            } else {
                sb.insert(0, rem);
            }

            value /= n;
        }
        return sb.toString();
    }
}
