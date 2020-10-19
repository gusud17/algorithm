class Solution {
    private static final int MOD = 20170805;

    private int[][] right, down;

    public int solution(int m, int n, int[][] cityMap) {
        right = new int[m + 1][n + 1];
        down = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) {
                    right[i][j] = 1;
                    down[i][j] = 1;
                    continue;
                }

                int state = cityMap[i - 1][j - 1];
                if (state == 0) {
                    right[i][j] = (right[i][j-1] + down[i-1][j]) % MOD;
                    down[i][j] = right[i][j];
                } else if (state == 2) {
                    right[i][j] = right[i][j - 1];
                    down[i][j] = down[i-1][j];
                }
            }
        }
        return right[m][n];
    }
}
