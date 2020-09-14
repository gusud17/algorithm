package com.gusud;

import java.util.Scanner;

public class b17143 {
    static int R, C, M;
    static Shark[][] board;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        M = sc.nextInt();
        board = new Shark[R][C];

        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            board[x - 1][y - 1] = new Shark(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        int ans = 0;
        for (int i = 0; i < C; i++) {
            ans += catchShark(i);
            moveShark();
        }

        System.out.println(ans);
    }

    private static void moveShark() {
        Shark[][] nboard = new Shark[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == null) continue;
                board[i][j].go(nboard, i, j);
            }
        }
        board = nboard;
    }

    private static int catchShark(int row) {
        for (int i = 0; i < R; i++) {
            if (board[i][row] != null) {
                int sharkSize = board[i][row].size;
                board[i][row] = null;
                return sharkSize;
            }
        }
        return 0;
    }

    static class Shark {
        private static final int[][] DIR = { //위, 아래, 오, 왼
                {-1, 0}, {1, 0}, {0, 1}, {0, -1}
        };

        private final int speed;
        private int dx, dy;
        private int size;

        public Shark(int speed, int dir, int size) {
            this.speed = speed;
            this.dx = DIR[dir - 1][0];
            this.dy = DIR[dir - 1][1];
            this.size = size;
        }

        public void go(Shark[][] board, int x, int y) {
            for (int i = 0; i < speed; i++) {
                int nx = x + dx;
                int ny = y + dy;

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                    dx *= -1;
                    dy *= -1;
                    i--;
                    continue;
                }

                x = nx;
                y = ny;
            }

            if (board[x][y] == null || board[x][y].size < size)
                board[x][y] = this;
        }
    }
}
