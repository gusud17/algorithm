package com.company;

import java.util.*;

public class b16236 {
    static int N;
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];
        Shark shark = null;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    map[i][j] = 0;
                    shark = new Shark(i, j);
                }
            }
        }

        System.out.println(shark.survive());
    }

    static class Shark {
        private static final int[][] DIR = {
                {-1, 0}, {0, -1}, {0, 1}, {1, 0}
        };

        private int x, y;
        private int size;

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
            size = 2;
        }

        public int survive() {
            int ans = 0;
            int eatCnt = 0;

            while (true) {
                Node food = findFood();
                if (food == null) break;

                map[food.x][food.y] = 0;
                this.x = food.x;
                this.y = food.y;

                ans += food.cnt;
                eatCnt++;

                if (eatCnt == size) {
                    eatCnt = 0;
                    size++;
                }
            }
            return ans;
        }

        private Node findFood() {
            boolean[][] visited = new boolean[N][N];

            Queue<Node> q = new LinkedList<>();
            q.add(new Node(x, y, 0));

            int dist = 0;
            List<Node> foods = new ArrayList<>();

            while (!q.isEmpty()) {
                Node cur = q.poll();

                if (dist < cur.cnt) {
                    if (!foods.isEmpty()) {
                        break;
                    }
                    dist++;
                }

                for (int[] dir : DIR) {
                    Node next = cur.next(dir);

                    if (!next.isValid() || next.isVisited(visited)) {
                        continue;
                    }

                    int fishSize = map[next.x][next.y];
                    visited[next.x][next.y] = true;

                    if (fishSize > 0 && fishSize < this.size) {
                        foods.add(next);
                        continue;
                    }

                    if (fishSize > this.size) continue;

                    q.add(next);
                }
            }

            return foods.stream()
                    .sorted()
                    .findFirst()
                    .orElse(null);
        }
    }

    static class Node implements Comparable<Node> {
        private final int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        public Node next(int[] dir) {
            return new Node(x + dir[0], y + dir[1], cnt + 1);
        }

        public boolean isValid() {
            return !(x < 0 || y < 0 || x >= N || y >= N);
        }

        public boolean isVisited(boolean[][] visited) {
            return visited[x][y];
        }

        @Override
        public int compareTo(Node o) {
            if (o.x == x) return y - o.y;
            return x - o.x;
        }
    }
}