import java.util.*;

public class b1400 {
    private static int M, N;
    private static char[][] map;
    private static Pos src;
    private static List<Intersection> intersections;
    private static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            M = sc.nextInt();
            N = sc.nextInt();

            if (M == 0) return;

            intersections = new ArrayList<>();
            int intersectionCnt = 0;
            map = new char[M][N];
            visited = new boolean[M][N];
            for (int i = 0; i < M; i++) {
                map[i] = sc.next().toCharArray();
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 'A') {
                        src = new Pos(i, j);
                    } else if (isIntersection(map[i][j])) {
                        intersectionCnt++;
                    }
                }
            }

            for (int i = 0; i < intersectionCnt; i++) {
                sc.nextInt();
                intersections.add(Intersection.of(sc.next(), sc.nextInt(), sc.nextInt()));
            }

            int ret = solve();
            if (ret == -1) System.out.println("impossible");
            else System.out.println(ret);
        }
    }

    private static int solve() {
        Queue<Node> q = new LinkedList<>();
        Node cur = new Node(src, 0);
        q.add(cur);
        cur.visit();

        while (!q.isEmpty()) {
            cur = q.poll();
            if (cur.success()) return cur.depth;
            q.addAll(cur.nexts());
        }

        return -1;
    }

    private static boolean isIntersection(char c) {
        return c >= '0' && c <= '9';
    }

    static class Node {
        private static final int[][] dirs = {
                {0, -1}, {0, 1}, {1, 0}, {-1, 0}
        };

        private final Pos pos;
        private final int depth;

        public Node(Pos pos, int depth) {
            this.pos = pos;
            this.depth = depth;
        }

        public void visit() {
            visited[pos.x][pos.y] = true;
        }

        public boolean success() {
            return pos.symbol() == 'B';
        }

        public List<Node> nexts() {
            List<Node> nexts = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Pos np = pos.next(dirs[i]);
                if (np.invalid()) continue;

                Node next = new Node(np, depth + 1);
                if (isIntersection(np.symbol())
                        && !intersections.get(np.symbol() - '0').canGo(depth + 1, i / 2)) {
                    next = new Node(pos, depth + 1);
                }

                nexts.add(next);
                next.visit();
            }
            return nexts;
        }
    }

    static class Intersection {
        private static final char[] DIR_SYMBOLS = {
                '-', '|'
        };
        private final String signal;

        public Intersection(String signal) {
            this.signal = signal;
        }

        static Intersection of(String next, int ew, int sn) {
            StringBuffer sb = new StringBuffer();
            char symbol = next.charAt(0);
            sb.append(getSignal(symbol, ew, sn));
            sb.append(getSignal(nextSymbol(symbol), ew, sn));
            return new Intersection(sb.toString());
        }

        private static char nextSymbol(char symbol) {
            return symbol == '-' ? '|' : '-';
        }

        private static char[] getSignal(char symbol, int ew, int sn) {
            int cnt = symbol == '-' ? ew : sn;
            char[] signal = new char[cnt];
            Arrays.fill(signal, symbol);
            return signal;
        }

        public boolean canGo(int time, int symbolIndex) {
            int i = (time - 1) % (signal.length());
            return signal.charAt(i) == DIR_SYMBOLS[symbolIndex];
        }
    }

    static class Pos {
        private final int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public char symbol() {
            return map[x][y];
        }

        public Pos next(int[] dir) {
            return new Pos(x + dir[0], y + dir[1]);
        }

        public boolean invalid() {
            return x < 0 || y < 0 || x >= M || y >= N || visited[x][y] || symbol() == '.';
        }
    }
}

