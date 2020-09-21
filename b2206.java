import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b2206 {
    private static int N, M;
    private static String[] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new String[N];
        for (int i = 0; i < N; i++) {
            map[i] = sc.next();
        }

        System.out.println(BFS());
    }

    static int[][] dirs = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    private static int BFS() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1, false));

        boolean[][][] visited = new boolean[N][M][2];
        visited[0][0][0] = true;
        visited[0][0][1] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == N - 1 && cur.y == M - 1) {
                return cur.depth;
            }

            for (int[] dir : dirs) {
                int nx = cur.x + dir[0];
                int ny = cur.y + dir[1];

                int broken = cur.broken ? 1 : 0;
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny][broken]) continue;
                if (map[nx].charAt(ny) == '1' && cur.broken) continue;

                visited[nx][ny][broken] = true;
                if (map[nx].charAt(ny) == '0') {
                    q.add(cur.next(dir, cur.broken));
                } else {
                    q.add(cur.next(dir, true));
                }
            }
        }
        return -1;
    }

    static class Node {
        private int x, y;
        private int depth;
        private boolean broken;

        public Node(int x, int y, int depth, boolean broken) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.broken = broken;
        }

        public Node next(int[] dir, boolean broken) {
            return new Node(x + dir[0], y + dir[1], depth + 1, broken);
        }
    }
}