import java.util.*;

public class b1726 {
    private static int M, N;
    private static int[][] map;
    private static boolean[][][] visited = new boolean[100][100][4]; //0123 = 동서남북
    private static Robot dest;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        Robot src = new Robot(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt() - 1);
        dest = new Robot(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt() - 1);

        System.out.println(bfs(src));
    }

    private static int bfs(Robot src) {
        Queue<Robot> q = new LinkedList<>();
        q.add(src);
        src.checkVisit();

        while (!q.isEmpty()) {
            Robot cur = q.poll();
            if (cur.equals(dest)) {
                return cur.depth;
            }
            q.addAll(cur.go());
            q.addAll(cur.turn());
        }
        return 0;
    }

    static class Robot {
        private static final int[][] DIRS = {  //동서남북
                {0, 1}, {0, -1}, {1, 0}, {-1, 0}
        };

        private static final int[][] nextDir = {
                {2, 3}, {2, 3}, {0, 1}, {0, 1}
        };

        private final int x, y, dir;
        private int depth;

        Robot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public void checkVisit() {
            visited[x][y][dir] = true;
        }

        public List<Robot> go() {
            List<Robot> nexts = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                int nx = x + DIRS[dir][0] * i;
                int ny = y + DIRS[dir][1] * i;
                Robot next = new Robot(nx, ny, dir);
                if (next.invalid()) break;
                if (next.isVisited()) continue;
                next.depth = this.depth + 1;
                next.checkVisit();
                nexts.add(next);
            }
            return nexts;
        }

        private boolean isVisited() {
            return visited[x][y][dir];
        }

        private boolean invalid() {
            return x < 0 || y < 0 || x >= M || y >= N || map[x][y] == 1;
        }

        public List<Robot> turn() {
            List<Robot> nexts = new ArrayList<>();

            for (int dir : nextDir[this.dir]) {
                Robot next = new Robot(x, y, dir);
                if (next.isVisited()) continue;
                next.depth = this.depth + 1;
                next.checkVisit();
                nexts.add(next);
            }

            return nexts;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Robot robot = (Robot) o;
            return x == robot.x &&
                    y == robot.y &&
                    dir == robot.dir;
        }
    }
}