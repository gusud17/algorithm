package com.gusud;


import java.util.Scanner;

public class b15684 {
    static boolean[][] ladder;
    static int verticalNum, horizontalNum, maxHeight;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        verticalNum = sc.nextInt();
        horizontalNum = sc.nextInt();
        maxHeight = sc.nextInt();
        ladder = new boolean[maxHeight + 1][verticalNum];

        for (int i = 1; i <= horizontalNum; i++) {
            int h = sc.nextInt();
            int leftVertical = sc.nextInt();

            ladder[h][leftVertical] = true;
        }

        for (int i = 0; i <= 3; i++) {
            if (addLine(i, 0, 1, 0)) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }

    private static boolean addLine(int maxLine, int depth, int startHeight, int startHorizontal) {
        if (depth == maxLine) {
            return play();
        }

        for (int i = startHeight; i <= maxHeight; i++) {
            int j = (startHeight == i) ? startHorizontal + 1 : 0;

            for (; j < verticalNum; j++) {
                if (ladder[i][j]) continue;

                ladder[i][j] = true;

                boolean res = addLine(maxLine, depth + 1, i, j);
                if (res) return true;

                ladder[i][j] = false;
            }
        }

        return false;
    }

    static boolean play() {
        for (int i = 1; i <= verticalNum; i++) {
            if (i != go(i, 1)) {
                return false;
            }
        }
        return true;
    }

    static int go(int vertical, int height) {
        if (height > maxHeight) {
            return vertical;
        }

        if (vertical < verticalNum && ladder[height][vertical]) {
            return go(vertical + 1, height + 1);  //right
        }

        if (vertical > 1 && ladder[height][vertical - 1]) {
            return go(vertical - 1, height + 1);  //left
        }

        return go(vertical, height + 1);  //down
    }
}
