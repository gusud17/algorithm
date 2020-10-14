import java.util.*;
import java.util.stream.Collectors;

public class b9328 {
    private static int h, w;
    private static char[][] map;

    private static String key;
    private static int[][] dirs = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            h = sc.nextInt();
            w = sc.nextInt();
            map = new char[h][w];
            for (int j = 0; j < h; j++) {
                map[j] = sc.next().toCharArray();
            }
            key = sc.next();

            System.out.println(solve());
        }
    }

    private static int solve() {
        int cnt = 0;
        boolean[][] visited = new boolean[h][w];
        List<Point> startPoints = StartPoint.getStartPoints(h, w).stream()
                .map(StartPoint::getPoints)
                .flatMap(Collection::stream)
                .filter(Point::isNotWall)
                .distinct()
                .collect(Collectors.toList());

        Queue<Point> q = new LinkedList<>(startPoints);

        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (cur.locked()) continue;
            if (cur.isNewKey()) {
                cur.addKey();
                visited = new boolean[h][w];
                q.clear();
                q.addAll(startPoints);
                continue;
            }
            if (cur.success()) {
                cnt++;
                map[cur.x][cur.y] = '.';
            }

            for (int[] dir : dirs) {
                Point next = cur.next(dir[0], dir[1]);
                if (next.invalid() || next.isVisited(visited) || !next.isNotWall()) continue;

                next.checkVisit(visited);
                q.add(next);
            }
        }
        return cnt;
    }

    static class StartPoint {
        private final Point point;
        private final int dx, dy;

        private StartPoint(Point point, int dx, int dy) {
            this.point = point;
            this.dx = dx;
            this.dy = dy;
        }

        static List<StartPoint> getStartPoints(int h, int w) {
            return Arrays.asList(
                    new StartPoint(new Point(0, 0), 0, 1),
                    new StartPoint(new Point(0, 0), 1, 0),
                    new StartPoint(new Point(0, w - 1), 1, 0),
                    new StartPoint(new Point(h - 1, 0), 0, 1)
            );
        }

        List<Point> getPoints() {
            List<Point> points = new ArrayList<>();

            Point cur = point;
            while (true) {
                points.add(cur);
                cur = cur.next(dx, dy);
                if (cur.invalid()) break;
            }

            return points;
        }
    }

    static class Point {
        private final int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point next(int dx, int dy) {
            return new Point(x + dx, y + dy);
        }

        public boolean invalid() {
            return x < 0 || y < 0 || x >= h || y >= w;
        }

        boolean isNotWall() {
            return map[x][y] != '*';
        }

        public boolean isVisited(boolean[][] visited) {
            return visited[x][y];
        }

        public boolean locked() {
            char c = map[x][y];
            return c >= 'A' && c <= 'Z' && noKey((char) (map[x][y] - 'A' + 'a'));
        }

        private boolean noKey(char c) {
            return !key.contains(String.valueOf(c));
        }

        public void checkVisit(boolean[][] visited) {
            visited[x][y] = true;
        }

        public boolean isNewKey() {
            char c = map[x][y];
            return c >= 'a' && c <= 'z' && noKey(c);
        }

        public void addKey() {
            key += String.valueOf(map[x][y]);
        }

        public boolean success() {
            return map[x][y] == '$';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}