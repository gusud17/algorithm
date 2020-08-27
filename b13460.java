package com.gusud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b13460 {
    static int N, M;
    static boolean[][] wall;
    static Map<Character, Position> rboPos = new HashMap<>();
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static boolean[][][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        wall = new boolean[N][M];
        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            String line = input.readLine();
            for (int j = 0; j < M; j++) {
                if (line.charAt(j) == '#') wall[i][j] = true;
                else if (line.charAt(j) != '.') {
                    rboPos.put(line.charAt(j), new Position(i, j));
                }
            }
        }

//        int answer = go(rboPos.get('R'), rboPos.get('B'), rboPos.get('O'), 1);
        int answer = bfs(rboPos.get('R'), rboPos.get('B'), rboPos.get('O'));
        System.out.println(answer > 10 ? -1 : answer);
    }

    private static int go(Position red, Position blue, Position goal, int count) {
        int ans = 11;

        if (count > 10) return ans;

        for (int i = 0; i < 4; i++) {
            Position nRed = nextPos(red, dir[i]);
            Position nBlue = nextPos(blue, dir[i]);

            if (nBlue.equals(goal)) continue;
            if (nRed.equals(goal))return count;

            if (nRed.equals(nBlue)) {
                if (red.distance(nRed) > blue.distance(nBlue)) {
                    nRed = nRed.before(dir[i]);
                } else {
                    nBlue = nBlue.before(dir[i]);
                }
            }

            if (!visited[nRed.n][nRed.m][nBlue.n][nBlue.m]) {
                visited[nRed.n][nRed.m][nBlue.n][nBlue.m] = true;
                ans = Integer.min(ans, go(nRed, nBlue, goal, count + 1));
            }
        }

        return ans;
    }

    private static int bfs(Position red, Position blue, Position goal) {
        Queue<Turn> q = new LinkedList<>();
        q.add(new Turn(red, blue, 0));

        while(!q.isEmpty()) {
            Turn cur = q.poll();
            int nCnt = cur.count + 1;

            if (nCnt > 10) break;

            for (int i = 0; i < 4; i++) {
                Position nRed = nextPos(cur.red, dir[i]);
                Position nBlue = nextPos(cur.blue, dir[i]);

                if (nBlue.equals(goal)) continue;
                if (nRed.equals(goal)) return nCnt;

                if (nRed.equals(nBlue)) {
                    if (cur.red.distance(nRed) > cur.blue.distance(nBlue)) {
                        nRed = nRed.before(dir[i]);
                    } else {
                        nBlue = nBlue.before(dir[i]);
                    }
                }

                if (!visited[nRed.n][nRed.m][nBlue.n][nBlue.m]) {
                    visited[nRed.n][nRed.m][nBlue.n][nBlue.m] = true;
                    q.add(new Turn(nRed, nBlue, nCnt));
                }
            }
        }

        return -1;
    }

    private static Position nextPos(Position cur, int[] dir) {
        Position nPos = cur.next(dir);

        while (!nPos.equals(rboPos.get('O'))) {
            if (nPos.isInvalid() || nPos.isWall()) {
                nPos = nPos.before(dir);
                break;
            }

            nPos = nPos.next(dir);
        }

        return nPos;
    }

    static class Turn {
        private Position red, blue;
        private int count;

        public Turn(Position red, Position blue, int count) {
            this.red = red;
            this.blue = blue;
            this.count = count;
        }
    }

    static class Position {
        private final int n, m;

        public Position(int n, int m) {
            this.n = n;
            this.m = m;
        }

        public int distance(Position pos) {
            return Math.abs(pos.n - n + pos.m - m);
        }

        public Position next(int[] dir) {
            return new Position(n + dir[0], m + dir[1]);
        }

        public Position before(int[] dir) {
            return new Position(n - dir[0], m - dir[1]);
        }

        public boolean isWall() {
            return wall[n][m];
        }

        public boolean isInvalid() {
            return n < 0 || m < 0 || n >= N || m >= M;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return n == position.n &&
                    m == position.m;
        }

        @Override
        public int hashCode() {
            return Objects.hash(n, m);
        }
    }
}
