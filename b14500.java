package com.gusud;

import java.util.Scanner;

public class b14500 {
    private static final int[][] dir = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    private static int n, m;
    private static int[][] paper;
    private static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int k = 0; k < 20; k++) {
            n = sc.nextInt();
            m = sc.nextInt();
            paper = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    paper[i][j] = sc.nextInt();
                }
            }
            System.out.println(solution());
        }

    }

    private static int solution() {
        visited = new boolean[n][m];

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int tetrominoSum = Math.max(tetrominoSum(i, j), tetrominoSum(i, j, 1));
                ans = Math.max(tetrominoSum, ans);
            }
        }

        return ans;
    }

    private static int tetrominoSum(int row, int col) {  // ㅗ
        int sum = paper[row][col];
        int minVal = Integer.MAX_VALUE;

        int check = 0;
        for (int i = 0; i < 4; i++) {
            int nr = row + dir[i][0];
            int nc = col + dir[i][1];

            if (outOfRange(nr, nc)) {
                check++;
                continue;
            }

            sum += paper[nr][nc];
            minVal = Math.min(minVal, paper[nr][nc]);
        }

        if (check > 2) return 0;
        if (check == 0) sum -= minVal;

        return sum;
    }

    private static int tetrominoSum(int row, int col, int depth) {  // ㅣ,ㄱ,ㅁ,z
        if (depth == 4) return paper[row][col];

        visited[row][col] = true;

        int ans = 0;

        for (int i = 0; i < 4; i++) {
            int nr = row + dir[i][0];
            int nc = col + dir[i][1];

            if (outOfRange(nr, nc) || visited[nr][nc]) continue;

            ans = Math.max(tetrominoSum(nr, nc, depth + 1), ans);
        }

        visited[row][col] = false;
        return ans + paper[row][col];
    }

    private static boolean outOfRange(int r, int c) {
        return r < 0 || c < 0 || r >= n || c >= m;
    }
}
