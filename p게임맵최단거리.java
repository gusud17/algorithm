import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class p게임맵최단거리 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{
                {1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}
        }));

        System.out.println(new Solution().solution(new int[][]{
                {1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}
        }));
    }
}

class Solution {
    private int n, m;

    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;

        boolean[][] visited = new boolean[n][m];
        Queue<Node> q = new LinkedList<>();
        Node src = new Node(0, 0, 1);
        q.add(src);
        src.visit(visited);

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.success()) return cur.cnt;

            q.addAll(cur.nexts(maps, visited));
        }

        return -1;
    }

    class Node {
        private final int[][] DIRS = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        private final int x, y;
        private final int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        public void visit(boolean[][] visited) {
            visited[x][y] = true;
        }

        public boolean visited(boolean[][] visited) {
            return visited[x][y];
        }

        public boolean success() {
            return x == n - 1 && y == m - 1;
        }

        public List<Node> nexts(int[][] maps, boolean[][] visited) {
            List<Node> nexts = new ArrayList<>();

            for (int[] dir : DIRS) {
                Node next = next(dir);
                if (next.invalid() || next.visited(visited) || next.isWall(maps)) continue;
                nexts.add(next);
                next.visit(visited);
            }
            return nexts;
        }

        private boolean isWall(int[][] maps) {
            return maps[x][y] == 0;
        }

        private boolean invalid() {
            return x < 0 || y < 0 || x >= n || y >= m;
        }

        private Node next(int[] dir) {
            return new Node(x + dir[0], y + dir[1], cnt + 1);
        }
    }
}


