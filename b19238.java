import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b19238 {
    private static final int[][] DIRS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    static int N, M, fuel;
    static int[][] map;
    static Pos[][] passengers;
    static Pos taxi;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        fuel = sc.nextInt();

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        taxi = new Pos(sc.nextInt() - 1, sc.nextInt() - 1);

        passengers = new Pos[N][N];
        for (int i = 0; i < M; i++) {
            passengers[sc.nextInt() - 1][sc.nextInt() - 1] = new Pos(sc.nextInt() - 1, sc.nextInt() - 1);
        }

        solve();

        if (M == 0) System.out.println(fuel);
        else System.out.println(-1);
    }

    private static void solve() {
        while (M > 0 && fuel > 0) {
            QNode psgInfo = getPassenger();
            if (psgInfo == null) return;

            int dist = runTaxi(psgInfo);
            if (dist < 0 || fuel < 0) break;

            fuel += dist * 2;
            M--;
        }
    }

    private static int runTaxi(QNode psgInfo) {
        boolean[][] visited = new boolean[N][N];
        Queue<QNode> q = new LinkedList<>();
        q.add(new QNode(taxi, 0));
        visited[taxi.x][taxi.y] = true;

        Pos dst = passengers[psgInfo.x()][psgInfo.y()];

        while (!q.isEmpty()) {
            QNode cur = q.poll();

            for (int[] dir : DIRS) {
                QNode next = cur.next(dir);

                if (!next.canGo(visited)) {
                    continue;
                }

                if (next.isPos(dst)) {
                    passengers[psgInfo.x()][psgInfo.y()] = null;
                    fuel -= next.cnt;
                    taxi = dst;
                    return next.cnt;
                }

                visited[next.x()][next.y()] = true;
                q.add(next);
            }
        }

        return -1;
    }

    private static QNode getPassenger() {
        QNode passenger = null;
        boolean[][] visited = new boolean[N][N];
        Queue<QNode> q = new LinkedList<>();
        q.add(new QNode(taxi, 0));
        if (q.peek().hasPassenger()) return q.peek();

        visited[taxi.x][taxi.y] = true;

        while (!q.isEmpty()) {
            QNode cur = q.poll();
            if (passenger != null && cur.cnt >= passenger.cnt) {
                break;
            }
            for (int[] dir : DIRS) {
                QNode next = cur.next(dir);

                if (!next.canGo(visited)) {
                    continue;
                }

                if (next.hasPassenger()) {
                    if (passenger == null) passenger = next;
                    else {
                        passenger = findMinPos(passenger, next);
                    }
                    continue;
                }

                visited[next.x()][next.y()] = true;
                q.add(next);
            }
        }

        if (passenger != null) {
            fuel -= passenger.cnt;
            taxi = passenger.pos;
        }
        return passenger;
    }

    private static QNode findMinPos(QNode n1, QNode n2) {
        if (n1.x() == n2.x()) {
            return n1.y() < n2.y() ? n1 : n2;
        }
        return n1.x() < n2.x() ? n1 : n2;
    }

    static class QNode {
        private final Pos pos;
        private final int cnt;

        public QNode(Pos pos, int cnt) {
            this.pos = pos;
            this.cnt = cnt;
        }

        public QNode next(int[] dir) {
            return new QNode(pos.next(dir), cnt + 1);
        }

        public int x() {
            return pos.x;
        }

        public int y() {
            return pos.y;
        }

        public boolean canGo(boolean[][] visited) {
            return pos.canGo(visited);
        }

        public boolean hasPassenger() {
            return passengers[pos.x][pos.y] != null;
        }

        public boolean isPos(Pos pos) {
            return this.pos.equals(pos);
        }
    }

    static class Pos {
        private final int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos next(int[] dir) {
            return new Pos(x + dir[0], y + dir[1]);
        }

        public boolean canGo(boolean[][] visited) {
            return !(x < 0 || y < 0 || x >= N || y >= N
                    || visited[x][y] || map[x][y] == 1);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return x == pos.x && y == pos.y;
        }
    }
}