class Solution {
    private static final int MOD = 1000000007;
    private int[][] right, down;
    
    public int solution(int m, int n, int[][] puddles) {
        right = new int[m + 1][n + 1];
        down = new int[m + 1][n + 1];
        
        boolean[][] puddleMap = new boolean[m + 1][n + 1];
        for (int[] puddle : puddles) {
            puddleMap[puddle[0]][puddle[1]] = true;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) {
                    right[i][j] = 1;
                    down[i][j] = 1;
                    continue;
                }
                
                if (!puddleMap[i][j]) {
                    right[i][j] = (right[i][j - 1] + down[i - 1][j]) % MOD;
                    down[i][j] = right[i][j];
                }
                
            }
        }
        return right[m][n];
    }
}
