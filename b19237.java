package com.gusud;

import java.util.Scanner;

public class b19237 {

    private static int[][][] map;
    private static int n, smellDuration;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int sharkNum = sc.nextInt();
        smellDuration = sc.nextInt();
        map = new int[n][n][2];
        Shark[] sharks = new Shark[sharkNum + 1]; //0번은 null, 1 ~ sharkNum

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sharkId = sc.nextInt();
                if (sharkId != 0) {
                    map[i][j][0] = sharkId;
                    map[i][j][1] = 1;
                    sharks[sharkId] = new Shark(sharkId, i, j);
                }
            }
        }

        for (int i = 1; i <= sharkNum; i++) {
            sharks[i].curDir = sc.nextInt() - 1;
        }

        for (int i = 1; i <= sharkNum; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    sharks[i].dirPriority[j][k] = sc.nextInt() - 1;
                }
            }
        }

        int remainShark = sharkNum;
        int t = 1;
        int[] nextDir = new int[sharkNum + 1];

        while (remainShark > 1) {
            if (++t >= 1000) {
                System.out.println(-1);
                return;
            }

            for (int i = 1; i <= sharkNum; i++) {
                if (sharks[i].isDead()) continue;
                nextDir[i] = sharks[i].explore(t);
            }

            for (int i = 1; i <= sharkNum; i++) {
                if (sharks[i].isDead()) continue;
                if (!sharks[i].move(t, nextDir[i])) {
                    remainShark--;
                }
            }
        }
    }

    static class Shark {
        private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        private final int id;
        private final int[][] dirPriority = new int[4][4];
        private int x, y;
        private int curDir;

        Shark(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        boolean move(int t, int dir) {
            if (dir == -1) {
                kill();
                return false;
            }

            this.x = x + dirs[dir][0];
            this.y = y + dirs[dir][1];

            if (map[x][y][1] == t) {
                kill();
                return false;
            }

            this.curDir = dir;
            map[x][y][0] = id;
            map[x][y][1] = t;
            return true;
        }

        int explore(int t) {
            //1. 빈칸 찾기
            for (int dir : this.dirPriority[curDir]) {
                int nx = x + dirs[dir][0];
                int ny = y + dirs[dir][1];

                if (outOfRange(nx, ny)) continue;
                int[] block = map[nx][ny];

                if (block[1] == 0 || block[1] + smellDuration < t) {
                    return dir;
                }
            }

            //2. 내 냄새 찾기
            for (int dir : this.dirPriority[curDir]) {
                int nx = x + dirs[dir][0];
                int ny = y + dirs[dir][1];

                if (outOfRange(nx, ny)) continue;
                int[] block = map[nx][ny];

                if (block[0] == id) {
                    return dir;
                }
            }

            return -1;
        }

        private boolean outOfRange(int nx, int ny) {
            return nx < 0 || ny < 0 || nx >= n || ny >= n;
        }

        private void kill() {
            x = -1;
            y = -1;
        }

        public boolean isDead() {
            return x < 0 || y < 0;
        }
    }
}
