import java.util.Arrays;

public class p삼각달팽이 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(1)));
        System.out.println(Arrays.toString(new Solution().solution(2)));
        System.out.println(Arrays.toString(new Solution().solution(3)));
        System.out.println(Arrays.toString(new Solution().solution(4)));
        System.out.println(Arrays.toString(new Solution().solution(5)));
        System.out.println(Arrays.toString(new Solution().solution(6)));
    }
}

class Solution {
    private static final int[][] DIRS = {
            {1, 0}, {0, 1}, {-1, -1}
    };
    private int n;
    private int size;
    private int[][] memo;

    public int[] solution(int n) {
        this.n = n;
        size = n * (n + 1) / 2;
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], 0);
        }

        int x = 0, y = 0;
        int dirIndex = 0;
        int k = 1;
        while (k <= size) {
            if (x < n && y < n && memo[x][y] == 0) {
                memo[x][y] = k;
                k++;
            } else {
                x -= DIRS[dirIndex][0];
                y -= DIRS[dirIndex][1];
                dirIndex = (dirIndex + 1) % 3;
            }
            x += DIRS[dirIndex][0];
            y += DIRS[dirIndex][1];
        }

        int[] answer = new int[size];
        int p = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[p] = memo[i][j];
                p++;
            }
        }

        return answer;
    }

    private void go(int x, int y, int depth, int dirIndex) {
        if (depth > size) return;

        int nx = x + DIRS[dirIndex][0];
        int ny = y + DIRS[dirIndex][1];

        if (nx < n && ny < n && memo[nx][ny] == 0) {
            memo[nx][ny] = depth;
            go(nx, ny, depth + 1, dirIndex);
        } else {
            go(x, y, depth, (dirIndex + 1) % 3);
        }
    }
}

