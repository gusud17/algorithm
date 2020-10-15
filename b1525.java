import java.util.*;

public class b1525 {
    private static int[][] board;
    private static Set<String> visited;

    public static void main(String[] args) {
        board = new int[3][3];
        visited = new HashSet<>();
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        System.out.println(solve(new Puzzle(board, 0)));
    }

    private static int solve(Puzzle start) {
        Queue<Puzzle> q = new LinkedList<>();
        q.add(start);
        start.visit();

        while (!q.isEmpty()) {
            Puzzle cur = q.poll();
            if (cur.isSuccess()) return cur.depth;

            q.addAll(cur.next());
        }
        return -1;
    }

    static class Puzzle {
        private static final int[][] dirs = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };
        private static final String SUCCESS_SYMBOL = "123456780";

        private final int[][] board;
        private final int depth;
        private final String symbol;

        public Puzzle(int[][] board, int depth) {
            this.board = board;
            this.depth = depth;
            symbol = getSymbol();
        }

        String getSymbol() {
            StringBuffer sb = new StringBuffer();
            for (int[] line : board) {
                for (int num : line) {
                    sb.append(num);
                }
            }
            return sb.toString();
        }

        public boolean isSuccess() {
            return symbol.equals(SUCCESS_SYMBOL);
        }

        public List<Puzzle> next() {
            List<Puzzle> nexts = new ArrayList<>();
            int x0 = 0, y0 = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 0) {
                        x0 = i;
                        y0 = j;
                    }
                }
            }

            for (int[] dir : dirs) {
                int nx = x0 + dir[0];
                int ny = y0 + dir[1];
                if (nx < 0 || ny < 0 || nx >= 3 || ny >= 3) continue;

                int[][] nBoard = copyOf(board);
                swap(nBoard, x0, y0, nx, ny);
                Puzzle next = new Puzzle(nBoard, depth + 1);

                if (next.visited()) continue;
                next.visit();
                nexts.add(next);
            }
            return nexts;
        }

        private boolean visited() {
            return visited.contains(symbol);
        }

        private void swap(int[][] arr, int x1, int y1, int x2, int y2) {
            int tmp = arr[x1][y1];
            arr[x1][y1] = arr[x2][y2];
            arr[x2][y2] = tmp;
        }

        private int[][] copyOf(int[][] arr) {
            int[][] ret = new int[3][];
            for (int i = 0; i < 3; i++) {
                ret[i] = Arrays.copyOf(arr[i], 3);
            }
            return ret;
        }

        void visit() {
            visited.add(symbol);
        }
    }
}