import java.util.*;

public class b1938 {
    private static int N;
    private static char[][] map;
    private static boolean[][][] visited = new boolean[50][50][2];
    private static List<Pos> dest = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        map = new char[N][N];
        List<Pos> src = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String tmp = sc.next();
            map[i] = tmp.toCharArray();

            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'B') {
                    src.add(new Pos(i, j));
                    map[i][j] = '0';
                }
                if (map[i][j] == 'E') {
                    dest.add(new Pos(i, j));
                    map[i][j] = '0';
                }
            }
        }

        System.out.println(bfs(new Tree(src)));
    }

    private static int bfs(Tree tree) {
        Queue<Tree> q = new LinkedList<>();
        q.add(tree);
        tree.checkVisit();

        while (!q.isEmpty()) {
            Tree cur = q.poll();
            if (cur.isEnd()) return cur.depth;
            q.addAll(cur.nexts());
        }
        return 0;
    }

    static class Tree {
        private static final int[][][] dirs = {
                {{-1, 0}, {-1, 0}, {-1, 0}},    //up
                {{1, 0}, {1, 0}, {1, 0}},       //down
                {{0, -1}, {0, -1}, {0, -1}},    //left
                {{0, 1}, {0, 1}, {0, 1}},       //right
                {{-1, 1}, {0, 0}, {1, -1}},     //turn2  -- -> |
                {{1, -1}, {0, 0}, {-1, 1}}      //turn1  | -> --
        };

        private static final int[][] around = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };
        private final List<Pos> treePos;
        private int depth = 0;

        public Tree(List<Pos> treePos) {
            this.treePos = treePos;
        }

        List<Tree> nexts() {
            List<Integer> index = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
            if (canTurn()) {
                index.add(isVertical() ? 5 : 4);
            }

            List<Tree> nexts = new ArrayList<>();

            for (int i : index) {
                Tree tree = nextTree(dirs[i]);
                if (tree == null || tree.isVisited()) continue;
                tree.checkVisit();
                nexts.add(tree);
            }

            return nexts;
        }

        private Tree nextTree(int[][] dir) {
            List<Pos> treePos = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                Pos np = this.treePos.get(i).next(dir[i]);
                if (np.invalid()) return null;
                treePos.add(np);
            }

            Tree tree = new Tree(treePos);
            tree.depth = this.depth + 1;
            return tree;
        }

        private boolean isVisited() {
            Pos center = treePos.get(1);
            return visited[center.x][center.y][isVertical() ? 0 : 1];
        }

        public void checkVisit() {
            Pos center = treePos.get(1);
            visited[center.x][center.y][isVertical() ? 0 : 1] = true;
        }

        private boolean isVertical() {
            return this.treePos.get(0).y == this.treePos.get(1).y;
        }

        private boolean canTurn() {
            Pos center = treePos.get(1);
            for (int i = 0; i < 8; i++) {
                if (center.next(around[i]).invalid()) return false;
            }
            return true;
        }

        public boolean isEnd() {
            for (int i = 0; i < 3; i++) {
                if (!treePos.get(i).equals(dest.get(i))) return false;
            }
            return true;
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

        public boolean invalid() {
            return x < 0 || y < 0 || x >= N || y >= N || map[x][y] == '1';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return x == pos.x &&
                    y == pos.y;
        }
    }
}