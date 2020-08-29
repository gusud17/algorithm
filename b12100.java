package com.gusud;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class b12100 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        System.out.println(solution(n, board));
    }

    private static int solution(int n, int[][] board) {
        Queue<Turn> q = new LinkedList<>();
        q.add(new Turn(board, 0));

        int ans = 0;
        while (!q.isEmpty()) {
            Turn cur = q.poll();

            if (cur.cnt == 5) {
                ans = Math.max(ans, cur.maxNum());
                continue;
            }

            List<Turn> nextTurns = cur.nexts(n);
            q.addAll(nextTurns);
        }

        return ans;
    }

    static class Turn {
        private final int[][] board;
        private final int cnt;

        public Turn(int[][] board, int cnt) {
            this.board = board;
            this.cnt = cnt;
        }

        public int maxNum() {
            return Arrays.stream(board)
                    .flatMapToInt(Arrays::stream)
                    .max()
                    .orElse(0);
        }

        public List<Turn> nexts(int n) {
            return Arrays.stream(Move.values())
                    .map(move -> new Turn(move.operate(n, board), cnt + 1))
                    .collect(Collectors.toList());
        }
    }

    enum Move {
        UP(Move::up), DOWN(Move::down), RIGHT(Move::right), LEFT(Move::left);

        private final BiFunction<Integer, int[][], int[][]> operation;

        Move(BiFunction<Integer, int[][], int[][]> operation) {
            this.operation = operation;
        }

        public int[][] operate(int n, int[][] board) {
            return operation.apply(n, board);
        }

        static int[][] up(int n, int[][] board) {
            int[][] res = new int[n][n];

            for (int col = 0; col < n; col++) {
                int p = 0;
                for (int row = 0; row < n; ) {
                    if (board[row][col] == 0) {
                        row++;
                        continue;
                    }

                    res[p][col] = board[row][col];
                    row++;

                    while (row < n && board[row][col] == 0) {
                        row++;
                    }

                    if (row < n && res[p][col] == board[row][col]) {
                        res[p][col] *= 2;
                        row++;
                    }
                    p++;
                }
            }
            
            return res;
        }

        static int[][] down(int n, int[][] board) {
            int[][] res = new int[n][n];

            for (int col = 0; col < n; col++) {
                int p = n - 1;
                for (int row = n - 1; row >= 0; ) {
                    if (board[row][col] == 0) {
                        row--;
                        continue;
                    }

                    res[p][col] = board[row][col];
                    row--;

                    while (row >= 0 && board[row][col] == 0) {
                        row--;
                    }

                    if (row >= 0 && res[p][col] == board[row][col]) {
                        res[p][col] *= 2;
                        row--;
                    }
                    p--;
                }
            }

            return res;
        }

        static int[][] right(int n, int[][] board) {
            int[][] res = new int[n][n];

            for (int row = 0; row < n; row++) {
                int p = n - 1;
                for (int col = n - 1; col >= 0; ) {
                    if (board[row][col] == 0) {
                        col--;
                        continue;
                    }

                    res[row][p] = board[row][col];
                    col--;

                    while (col >= 0 && board[row][col] == 0) {
                        col--;
                    }
                    if (col >= 0 && res[row][p] == board[row][col]) {
                        res[row][p] *= 2;
                        col--;
                    }
                    p--;
                }
            }

            return res;
        }

        static int[][] left(int n, int[][] board) {
            int[][] res = new int[n][n];

            for (int row = 0; row < n; row++) {
                int p = 0;
                for (int col = 0; col < n; ) {
                    if (board[row][col] == 0) {
                        col++;
                        continue;
                    }

                    res[row][p] = board[row][col];
                    col++;

                    while (col < n && board[row][col] == 0) {
                        col++;
                    }

                    if (col < n && res[row][p] == board[row][col]) {
                        res[row][p] *= 2;
                        col++;
                    }
                    p++;
                }
            }

            return res;
        }
    }
}