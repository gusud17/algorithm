import java.util.Scanner;

public class b1405 {
    private static int[][] dir = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}    //동서남북
    };

    private static int N;
    private static double[] percentages = new double[4];
    private static boolean[][] map = new boolean[29][29];
    private static double answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int i = 0; i < 4; i++) {
            percentages[i] = (double) sc.nextInt() / 100;
        }

        int x = 14, y = 14;
        map[x][y] = true;

        go(x, y, 0, 1);

        System.out.println(answer);
    }

    private static void go(int x, int y, int depth, double percentage) {
        if (depth == N) {
            answer += percentage;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if (map[nx][ny]) continue;

            map[nx][ny] = true;
            go(nx, ny, depth + 1, percentage * percentages[i]);
            map[nx][ny] = false;
        }
    }
}