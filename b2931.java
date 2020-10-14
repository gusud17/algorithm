import java.util.*;

public class b2931 {
    private static int R, C;
    static int[][] visited;
    static char[][] map;

    static int x, y, symbol;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();

        visited = new int[R][C];
        map = new char[R][C];
        Coordinate src = null;
        for (int i = 0; i < R; i++) {
            map[i] = sc.next().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'M') {
                    src = new Coordinate(i, j);
                }
            }
        }

        src.checkVisit();
        for (int[] d : dirs) {
            Coordinate next = src.next(d);

            if (next.invalid() || next.getSymbol() == '.') continue;

            next.checkVisit();
            if (!new Block(next).go()) break;
            next.uncheckVisit();
        }

        System.out.println(String.format("%d %d %c", x, y, symbol));
    }

    private static final int[][] dirs = {  //위 아래 왼 오
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    static class Block {
        private static final Map<Character, List<Integer>> specialBlock = new HashMap<Character, List<Integer>>() {{
            put('|', Arrays.asList(0, 1));
            put('-', Arrays.asList(2, 3));
            put('+', Arrays.asList(0, 1, 2, 3));
            put('1', Arrays.asList(1, 3));
            put('2', Arrays.asList(0, 3));
            put('3', Arrays.asList(0, 2));
            put('4', Arrays.asList(1, 2));
        }};

        private final Coordinate coordinate;

        public Block(Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        public char getSymbol() {
            return coordinate.getSymbol();
        }

        public boolean go() {
            if (coordinate.isEnd()) return true;

            if (coordinate.isEmpty()) {
                x = coordinate.x + 1;
                y = coordinate.y + 1;
                symbol = findSymbol();
                return false;
            }

            for (int i : specialBlock.get(coordinate.getSymbol())) {
                Coordinate next = coordinate.next(dirs[i]);
                if (next.invalid() || next.isVisited()) continue;

                next.checkVisit();
                if (!new Block(next).go()) return false;
                next.uncheckVisit();
            }

            return true;
        }

        public char findSymbol() {
            List<Integer> dirIndex = new ArrayList<>();
            List<Integer> secondBest = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Coordinate next = coordinate.next(dirs[i]);

                if (next.invalid() || next.isEmpty()) continue;
                if (!canGo(next, coordinate)) continue;

                if (next.isEnd()) secondBest.add(i);
                else dirIndex.add(i);
            }
            if (dirIndex.isEmpty()) dirIndex = secondBest;
            return getSpecial(dirIndex);
        }

        private boolean canGo(Coordinate from, Coordinate to) {
            char symbol = from.getSymbol();
            if (symbol == 'M' || symbol == 'Z') symbol = '|';

            for (int i : specialBlock.get(symbol)) {
                Coordinate next = from.next(dirs[i]);
                if (next.equals(to)) return true;
            }
            return false;
        }

        private char getSpecial(List<Integer> dirIndex) {
            return specialBlock.entrySet().stream()
                    .filter(x -> x.getValue().equals(dirIndex))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse('.');
        }
    }

    static class Coordinate {
        private final int x, y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Coordinate next(int[] d) {
            return new Coordinate(x + d[0], y + d[1]);
        }

        public boolean invalid() {
            return x < 0 || y < 0 || x >= R || y >= C;
        }

        public char getSymbol() {
            return map[x][y];
        }

        boolean isVisited() {
            int check = getSymbol() == '+' ? 2 : 1;
            return visited[x][y] == check;
        }

        void checkVisit() {
            visited[x][y]++;
        }

        void uncheckVisit() {
            visited[x][y]--;
        }

        public boolean isEnd() {
            return getSymbol() == 'Z' || getSymbol() == 'M';
        }

        public boolean isEmpty() {
            return getSymbol() == '.';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}