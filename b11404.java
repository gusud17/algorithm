import java.util.Arrays;
import java.util.Scanner;

public class b11404 {
    private static final int INF = 987654321;
    private static int N, M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            int s = sc.nextInt() - 1;
            int d = sc.nextInt() - 1;
            graph[s][d] = Math.min(graph[s][d], sc.nextInt());
        }

        int[][] d = floydWarshall(graph);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (d[i][j] == INF) d[i][j] = 0;
                System.out.print(d[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] floydWarshall(int[][] graph) {
        int[][] d = new int[N][];
        for (int i = 0; i < N; i++) {
            d[i] = Arrays.copyOf(graph[i], N);
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (d[i][j] > d[i][k] + d[k][j]) {
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }

        return d;
    }
}