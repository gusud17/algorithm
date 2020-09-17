import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b17822 {
    static int N, M, T;
    static int[][] disks;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        T = sc.nextInt();

        disks = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                disks[i][j] = sc.nextInt();
            }
        }

        int[][] rotations = new int[T][3];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < 3; j++) {
                rotations[i][j] = sc.nextInt();
            }
        }

        for (int[] rotation : rotations) {
            int k = rotation[1] == 0 ? rotation[2] : M - rotation[2];
            rotateClockWise(rotation[0], k);
            checkDisks();
        }

        System.out.println(sumOfDisk()[0]);
    }

    private static final int[][] dirs = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    private static void checkDisks() {
        boolean erased = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (disks[i][j] == 0) continue;
                if (bfs(i, j, disks[i][j])) {
                    erased = true;
                    disks[i][j] = 0;
                }
            }
        }

        if (!erased) {
            int[] sumCnt = sumOfDisk();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (disks[i][j] == 0) continue;
                    if (disks[i][j] * sumCnt[1] > sumCnt[0]) disks[i][j] -= 1;
                    else if (disks[i][j] * sumCnt[1] < sumCnt[0]) disks[i][j] += 1;
                }
            }
        }
    }

    private static boolean bfs(int x, int y, int num) {
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});

        boolean erased = false;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dirs[i][0];
                int ny = (cur[1] + dirs[i][1] + M) % M;

                if (nx < 0 || nx >= N || disks[nx][ny] != num || visited[nx][ny]) continue;

                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                disks[nx][ny] = 0;
                erased = true;
            }
        }
        return erased;
    }

    private static int[] sumOfDisk() {
        int sum = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (disks[i][j] == 0) continue;
                cnt++;
                sum += disks[i][j];
            }
        }
        return new int[]{sum, cnt};
    }

    private static void rotateClockWise(int n, int k) {
        for (int dnum = 0; dnum < N; dnum++) {
            if ((dnum + 1) % n != 0) continue;
            int[] disk = Arrays.copyOf(disks[dnum], M);
            for (int i = 0; i < M; i++) {
                disks[dnum][(i + k) % M] = disk[i];
            }
        }
    }
}
