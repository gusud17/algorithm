import java.util.Scanner;

public class b3085 {
    private static int N;
    static char[][] map;

    private static int[][] dirs = {
            {1, 0}, {0, 1}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = sc.next().toCharArray();
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 2; k++) {
                    int nx = i + dirs[k][0];
                    int ny = j + dirs[k][1];
                    if (!canSwap(nx, ny)) continue;
                    swap(i, j, nx, ny);
                    ans = Math.max(check(i, j), ans);
                    ans = Math.max(check(nx, ny), ans);
                    swap(i, j, nx, ny);
                }
            }
        }
        System.out.println(ans);
    }

    private static boolean canSwap(int nx, int ny) {
        return nx < N && ny < N;
    }

    static void swap(int x, int y, int nx, int ny) {
        char tmp = map[x][y];
        map[x][y] = map[nx][ny];
        map[nx][ny] = tmp;
    }

    private static int check(int x, int y) {
        int cntCol = 1 + go(x, y, -1, dirs[0]) + go(x, y, 1, dirs[0]);
        return Math.max(cntCol, 1 + go(x, y, -1, dirs[1]) + go(x, y, 1, dirs[1]));
    }

    private static int go(int x, int y, int i, int[] dir) {
        int dx = dir[0] * i;
        int dy = dir[1] * i;
        int nx = x;
        int ny = y;
        int cnt = 0;
        while (true) {
            nx += dx;
            ny += dy;

            if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[x][y] != map[nx][ny]) break;
            cnt++;
        }
        return cnt;
    }
}