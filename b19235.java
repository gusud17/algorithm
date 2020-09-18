import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class b19235 {
    static int N;
    static Block[][] green = new Block[6][4], blue = new Block[6][4];
    static int score;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        int[][] blockInfo = new int[N][3];
        for (int i = 0; i < N; i++) {
            blockInfo[i] = new int[]{sc.nextInt(), sc.nextInt(), sc.nextInt()};
        }

        for (int[] info : blockInfo) {
            Block gBlock = Block.of(info);
            Block bBlock = gBlock.reverse();
            solve(green, gBlock);
            solve(blue, bBlock);
        }

        System.out.println(score);
        System.out.println(cntBlocks());
    }

    private static void solve(Block[][] board, Block block) {
        addBlock(board, block);
        addScore(board);
        lightBlocks(board);
    }

    private static void addBlock(Block[][] board, Block block) {
        for (int i = 0; i < 6; i++) {
            if (i == 5 || board[i + 1][block.y1()] != null || board[i + 1][block.y2()] != null) {
                board[i][block.y1()] = block;
                board[i][block.y2()] = block;
                block.setX(i);
                if (block.y1() == block.y2() && block.size == 2) {
                    board[i - 1][block.y1()] = block;
                    block.setX1(i - 1);
                }
                break;
            }
        }
    }

    private static void addScore(Block[][] board) {
        int cnt = 0;
        for (int i = 2; i < 6; i++) {
            boolean success = true;
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == null) success = false;
            }

            if (success) {
                cnt++;

                for (int j = 0; j < 4; j++) {
                    board[i][j].remove(new Pos(i, j));
                    board[i][j] = null;
                }
            }
        }

        if (cnt == 0) return;

        boolean[][] visited = new boolean[6][4];
        for (int i = 5; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == null || visited[i][j]) continue;
                board[i][j].checkVisit(visited);
                board[i][j].goDown(board);
            }
        }

        score += cnt;
        addScore(board);
    }

    private static void lightBlocks(Block[][] board) {
        int cnt = 0;
        for (int i = 0; i < 2; i++) {
            for (Block block : board[i]) {
                if (block != null) {
                    cnt++;
                    break;
                }
            }
        }

        if (cnt == 0) return;
        Set<Block> blocks = new HashSet<>();
        for (int i = 0; i < 6 - cnt; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == null) continue;
                blocks.add(board[i][j]);
                board[i][j] = null;
            }
        }

        for (int i = 6 - cnt; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = null;
            }
        }

        for (Block block : blocks) {
            block.plusX(cnt);
            block.apply(board);
        }
    }

    private static int cntBlocks() {
        int cnt = 0;
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j] != null) cnt++;
                if (blue[i][j] != null) cnt++;
            }
        }
        return cnt;
    }

    static class Pos {
        private final int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return x == pos.x &&
                    y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        protected Pos clone() {
            return new Pos(x, y);
        }

        public Pos next(int[] d) {
            return new Pos(x + d[0], y + d[1]);
        }

        public Pos reverse() {
            return new Pos(y, x);
        }

        public Pos x(int x) {
            return new Pos(x, this.y);
        }

        public boolean empty(Block[][] board) {
            return board[x][y] == null;
        }

        public boolean isBottom() {
            return x == 5;
        }

        public Pos plusX(int dx) {
            return new Pos(x + dx, y);
        }
    }

    static class Block {
        static int[][] d = {
                {0, 0}, {0, 1}, {1, 0}
        };

        private Pos p1, p2;  //p2 >= p1
        private int size;

        public Block(Pos p1, Pos p2) {
            this.p1 = p1;
            this.p2 = p2;
            size = p1.equals(p2) ? 1 : 2;
        }

        public static Block of(int[] info) {
            Pos p1 = new Pos(info[1], info[2]);
            Pos p2 = p1.next(d[info[0] - 1]);
            return new Block(p1, p2);
        }

        public void remove(Pos pos) {
            if (p1.equals(pos)) p1 = p2.clone();
            if (p2.equals(pos)) p2 = p1.clone();
            size = 1;
        }

        public Block reverse() {
            return new Block(p1.reverse(), p2.reverse());
        }

        public int y1() {
            return p1.y;
        }

        public int y2() {
            return p2.y;
        }

        public void setX(int x) {
            p1 = p1.x(x);
            p2 = p2.x(x);
        }

        public void setX1(int x) {
            p1 = p1.x(x);
        }

        public void checkVisit(boolean[][] visited) {
            visited[p1.x][p1.y] = true;
            visited[p2.x][p2.y] = true;
        }

        public void goDown(Block[][] board) {
            if (p2.isBottom()) return;

            Pos np1 = p1;
            Pos np2 = p2;

            while ((np1.plusX(1).empty(board) || np1.plusX(1).equals(p2)) && np2.plusX(1).empty(board)) {
                np1 = np1.plusX(1);
                np2 = np2.plusX(1);

                if (np1.isBottom() || np2.isBottom()) break;
            }

            board[p1.x][p1.y] = null;
            board[p2.x][p2.y] = null;

            p1 = np1;
            p2 = np2;
            apply(board);
        }

        public void apply(Block[][] board) {
            board[p1.x][p1.y] = this;
            board[p2.x][p2.y] = this;
        }

        public void plusX(int d) {
            p1 = p1.plusX(d);
            p2 = p2.plusX(d);

            if (p1.x >= 6) {
                p1 = p2;
                size = 1;
            } else if (p2.x >= 6) {
                p2 = p1;
                size = 1;
            }
        }
    }
}