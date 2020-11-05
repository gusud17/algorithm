import java.util.PriorityQueue;

public class p지형이동 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{
                {1, 4, 8, 10}, {5, 5, 5, 5}, {10, 10, 10, 10}, {10, 10, 10, 20}
        }, 3));

        System.out.println(new Solution().solution(new int[][]{
                {10, 11, 10, 11}, {2, 21, 20, 10}, {1, 20, 21, 11}, {2, 1, 2, 1}
        }, 1));
    }
}

class Solution {
    private static final int[][] dirs = new int[][]{
            {1, 0}, {0, 1}, {-1, 0}, {0, -1}
    };

    private int n;
    private int[][] land;
    private int height;
    private int[][] areaMap;

    public int solution(int[][] land, int height) {
        this.n = land.length;
        this.land = land;
        this.height = height;
        this.areaMap = new int[n][n];

        int areaCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (areaMap[i][j] > 0) continue;
                areaCnt++;
                go(i, j, areaCnt);
            }
        }

        DisjointSet disjointSet = new DisjointSet(areaCnt);
        PriorityQueue<Ladder> ladderPriorityQueue = getLadderPriorityQueue();

        int answer = 0;
        while (!ladderPriorityQueue.isEmpty()) {
            Ladder ladder = ladderPriorityQueue.poll();

            if (disjointSet.isEqual(ladder.area1, ladder.area2)) continue;

            answer += ladder.height;
            disjointSet.union(ladder.area1, ladder.area2);
        }

        return answer;
    }

    private void go(int x, int y, int symbol) {
        areaMap[x][y] = symbol;

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n || areaMap[nx][ny] > 0) continue;

            int heightDiff = Math.abs(land[x][y] - land[nx][ny]);
            if (heightDiff > height) continue;

            go(nx, ny, symbol);
        }
    }

    class DisjointSet {
        private final int[] parent;

        DisjointSet(int n) {
            parent = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }

        void union(int i, int j) {
            parent[find(i)] = parent[j];
        }

        private int find(int i) {
            if (i == parent[i]) return i;

            parent[i] = find(parent[i]);
            return parent[i];
        }

        boolean isEqual(int i, int j) {
            return find(i) == find(j);
        }
    }

    private PriorityQueue<Ladder> getLadderPriorityQueue() {
        PriorityQueue<Ladder> ladderPriorityQueue = new PriorityQueue<>();
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                int area1 = areaMap[x][y];
                for (int i = 0; i <= 1; i++) {
                    int nx = x + dirs[i][0];
                    int ny = y + dirs[i][1];
                    if (nx >= n || ny >= n) continue;

                    int area2 = areaMap[nx][ny];
                    if (area1 == area2) continue;

                    int height = Math.abs(land[x][y] - land[nx][ny]);
                    ladderPriorityQueue.add(new Ladder(area1, area2, height));
                }
            }
        }
        return ladderPriorityQueue;
    }

    class Ladder implements Comparable<Ladder> {
        private final int area1, area2;
        private final int height;

        Ladder(int area1, int area2, int height) {
            this.area1 = area1;
            this.area2 = area2;
            this.height = height;
        }

        @Override
        public int compareTo(Ladder o) {
            return height - o.height;
        }
    }
}
