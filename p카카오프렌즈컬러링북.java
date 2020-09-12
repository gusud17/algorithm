package com.gusud;

import java.util.Arrays;

class p카카오프렌즈컬러링북 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                new Solution().solution(6, 4, new int[][]{{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}}
                )));

    }
}

class Solution {
    private static final int[][] dirs = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };
    private boolean[][] visited;
    private int[][] picture;
    private int m, n;

    public int[] solution(int m, int n, int[][] picture) {
        visited = new boolean[m][n];
        this.m = m;
        this.n = n;
        this.picture = picture;

        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] || picture[i][j] == 0) continue;
                numberOfArea++;
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, dfs(i, j));
            }
        }

        return new int[]{numberOfArea, maxSizeOfOneArea};
    }

    private int dfs(int x, int y) {
        visited[x][y] = true;
        int ans = 1;

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny] || picture[x][y] != picture[nx][ny]) continue;

            ans += dfs(nx, ny);
        }

        return ans;
    }

}

