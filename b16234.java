package com.gusud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class b16234 {

    private static int N, L, R;
    private static boolean[][] visited;
    private static int[][] populations;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();

        populations = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                populations[i][j] = sc.nextInt();
            }
        }

        int ans = 0;
        while (movePopulation()) {
            ans++;
        }

        System.out.println(ans);
    }

    private static boolean movePopulation() {
        visited = new boolean[N][N];

        List<Union> unions = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;

                Union union = new Union(i, j);
                if (union.size() > 1) {
                    unions.add(union);
                }
            }
        }

        unions.forEach(Union::movePopulation);
        return !unions.isEmpty();
    }

    static class Union {
        private static int[][] dirs = {
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };

        private List<int[]> union;

        public Union(int x, int y) {
            this.union = new ArrayList<>();
            unite(x, y);
        }

        private void unite(int x, int y) {
            visited[x][y] = true;
            union.add(new int[]{x, y});

            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;

                int popDiff = Math.abs(populations[x][y] - populations[nx][ny]);

                if (popDiff >= L && popDiff <= R) {
                    unite(nx, ny);
                }
            }
        }

        int size() {
            return union.size();
        }

        public void movePopulation() {
            int population = union.stream()
                    .mapToInt(arr -> populations[arr[0]][arr[1]])
                    .sum();
            population /= union.size();

            for (int[] pos : union) {
                populations[pos[0]][pos[1]] = population;
            }
        }
    }
}
