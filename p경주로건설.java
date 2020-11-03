import java.util.*;

public class p경주로건설 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
        ));
        System.out.println(new Solution().solution(
                new int[][]{{0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 1}, {0, 0, 1, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 0, 1, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0}}
        ));
    }
}

class Solution {
    static private int n;

    public int solution(int[][] board) {
        n = board.length;

        int[][][] cost = new int[4][n][n];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < n; i++) {
                Arrays.fill(cost[j][i], Integer.MAX_VALUE);
            }
        }

        Queue<Node> q = new LinkedList<>();
        for (int i = 1; i < 4; i += 2) {
            Node node = new Node(Node.DIRS[i][0], Node.DIRS[i][1], i);
            if (node.isBlock(board)) continue;
            q.add(node);
            node.checkCost(cost, 100);
        }

        int ans = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.is(n - 1, n - 1)) {
                ans = Math.min(ans, cur.cost(cost));
                continue;
            }
            q.addAll(cur.nexts(cost, board));
        }
        return ans;
    }

    static class Node {
        private static final int[][] DIRS = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };
        private final int x, y;
        private final int dirIndex;

        public Node(int x, int y, int dirIndex) {
            this.x = x;
            this.y = y;
            this.dirIndex = dirIndex;
            System.out.println(dirIndex);
        }

        public boolean is(int x, int y) {
            return this.x == x && this.y == y;
        }

        public List<Node> nexts(int[][][] cost, int[][] board) {
            List<Node> nexts = new ArrayList<>();

            //직선도로
            Node next = next(dirIndex);
            if (!next.invalid() && !next.isBlock(board)
                    && next.cost(cost) > cost(cost) + 100) {
                nexts.add(next);
                next.checkCost(cost, cost(cost) + 100);
            }

            //코너
            int nDirIndex = dirIndex < 2 ? 2 : 0;

            for (int i = 0; i < 2; i++) {
                next = next(nDirIndex + i);

                if (next.invalid() || next.isBlock(board)
                        || next.cost(cost) <= cost(cost) + 600) continue;
                nexts.add(next);
                next.checkCost(cost, cost(cost) + 600);
            }

            return nexts;
        }

        private boolean isBlock(int[][] board) {
            return board[x][y] == 1;
        }

        private void checkCost(int[][][] cost, int c) {
            cost[dirIndex][x][y] = c;
        }

        private boolean invalid() {
            return x < 0 || y < 0 || x >= n || y >= n;
        }

        private int cost(int[][][] cost) {
            return cost[dirIndex][x][y];
        }

        private Node next(int dirIndex) {
            int[] dir = DIRS[dirIndex];
            return new Node(x + dir[0], y + dir[1], dirIndex);
        }
    }
}


