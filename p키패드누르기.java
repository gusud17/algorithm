package com.gusud;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class p키패드누르기 {
    private Position[] keypad = {
            new Position(3, 1), //0
            new Position(0, 0), new Position(0, 1), new Position(0, 2),//1,2,3
            new Position(1, 0), new Position(1, 1), new Position(1, 2),//4,5,6
            new Position(2, 0), new Position(2, 1), new Position(2, 2),//7.8.9
            new Position(3, 0), new Position(3, 2)//*,#
    };

    private Set<Integer> lefts = new HashSet<>(Arrays.asList(1, 4, 7));
    private Set<Integer> rights = new HashSet<>(Arrays.asList(3, 6, 9));
    private char[] LR = {'L', 'R'};

    private Position[] fingers = new Position[2]; // 0:left, 1:right
    private int handType; //L R

    public String solution(int[] numbers, String hand) {
        init(hand);

        String answer = "";

        for (int number : numbers) {
            int push;

            if (lefts.contains(number)) {
                push = 0;
            } else if (rights.contains(number)) {
                push = 1;
            } else {
                int diffLeft = fingers[0].diff(keypad[number]);
                int diffRight = fingers[1].diff(keypad[number]);

                if (diffLeft < diffRight) {
                    push = 0;
                } else if (diffLeft > diffRight) {
                    push = 1;
                } else {
                    push = handType;
                }
            }

            fingers[push] = keypad[number];
            answer += LR[push];
        }

        return answer;
    }

    private void init(String hand) {
        fingers[0] = keypad[10];
        fingers[1] = keypad[11];
        handType = hand.equals("left") ? 0 : 1;
    }

    class Position {
        private int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int diff(Position other) {
            return Math.abs(other.x - x) + Math.abs(other.y - y);
        }
    }
}
