package com.company;

import java.util.*;

public class b16235 {
    static int N, M, K;
    static int[][] A;
    static int[][] map;
    static Deque<Integer>[][] trees;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        A = new int[N][N];
        map = new int[N][N];
        trees = new Deque[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = sc.nextInt();
                map[i][j] = 5;
                trees[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < M; i++) {
            trees[sc.nextInt()-1][sc.nextInt()-1].add(sc.nextInt());
        }

        for (int i = 0; i < K; i++) {
            int[][] food = spring();
            addFood(food); //summer
            fall();
            addFood(A); //winter
        }

        int treeCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                treeCnt += trees[i][j].size();
            }
        }
        System.out.println(treeCnt);
    }

    private static void addFood(int[][] food) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] += food[i][j];
            }
        }
    }

    private static final int[][] dirs = {
            {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
    };

    private static void fall() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int tree : trees[i][j]) {
                    if (tree % 5 == 0) {
                        for (int[] dir : dirs) {
                            int nx = i + dir[0];
                            int ny = j + dir[1];

                            if (nx < 0||ny < 0|| nx >= N || ny >= N) {
                                continue;
                            }
                            trees[nx][ny].addFirst(1);
                        }
                    }
                }
            }
        }
    }

    private static int[][] spring() {
        int[][] food = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = trees[i][j].size();

                while (cnt > 0) {
                    cnt--;
                    int tree = trees[i][j].pollFirst();
                    if (tree <= map[i][j]) {
                        map[i][j] -= tree;
                        trees[i][j].addLast(tree + 1);
                    } else {
                        food[i][j] += tree / 2;
                    }
                }
            }
        }
        return food;
    }
}