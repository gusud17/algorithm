package com.gusud;

import java.util.Arrays;
import java.util.function.BiConsumer;

class p다트게임 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution("1S2D*3T"));
        System.out.println(new Solution().solution("1D2S#10S"));
        System.out.println(new Solution().solution("1D2S0T"));
        System.out.println(new Solution().solution("1S*2T*3S"));
        System.out.println(new Solution().solution("1D#2S*3S"));
        System.out.println(new Solution().solution("1T2D3D#"));
        System.out.println(new Solution().solution("1D2S3T*"));
    }

}

class Solution {
    private static final String SDT = "SDT";

    private final int[] scores = new int[3];

    public int solution(String dartResult) {
        int len = dartResult.length();
        int p = -1;
        for (int i = 0; i < len; i++) {
            char c = dartResult.charAt(i);

            if (Option.isOption(c)) {
                Option option = Option.find(c);
                option.operator.accept(scores, p);
                continue;
            }

            int num = c - '0';

            if (dartResult.charAt(++i) == '0') {
                num = 10;
                i++;
            }

            scores[++p] = extractScore(num, dartResult.charAt(i));
        }

        return Arrays.stream(scores).sum();
    }


    private int extractScore(int num, char SDT) {
        int e = this.SDT.indexOf(SDT) + 1;
        return (int) Math.pow(num, e);
    }

    enum Option {
        STAR('*', Option::star), ACHA('#', Option::acha);

        private final char symbol;
        private final BiConsumer<int[], Integer> operator;

        Option(char symbol, BiConsumer<int[], Integer> operator) {
            this.symbol = symbol;
            this.operator = operator;
        }

        private static void acha(int[] scores, int p) {
            scores[p] *= -1;
        }

        private static void star(int[] scores, int p) {
            for (int i = 0; i < 2; i++) {
                if (p - i < 0) return;
                scores[p - i] *= 2;
            }
        }

        public static boolean isOption(char c) {
            return Arrays.stream(values())
                    .anyMatch(o -> o.symbol == c);
        }

        public static Option find(char c) {
            return Arrays.stream(values())
                    .filter(o -> o.symbol == c)
                    .findAny()
                    .orElse(null);
        }
    }
}

