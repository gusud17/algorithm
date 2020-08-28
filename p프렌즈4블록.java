package com.gusud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p프렌즈4블록 {
    public int solution(int m, int n, String[] board) {
        Friends4Block friends4Block = new Friends4Block(m, n, toCharBoard(board));
        return friends4Block.play();
    }

    private char[][] toCharBoard(String[] board) {
        return Arrays.stream(board)
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    static class Friends4Block {
        private static final char EMPTY = '0';
        private static final int[][] dir = {{0, 0}, {1, 0}, {0, 1}, {1, 1}};

        private final int m, n;
        private final char[][] board;

        public Friends4Block(int m, int n, char[][] board) {
            this.m = m;
            this.n = n;
            this.board = board;
        }

        public int play() {
            int answer = 0;

            while (true) {
                Boolean[][] popMap = checkPop();
                int popCnt = countPop(popMap);
                if (popCnt == 0) break;

                popBlock(popMap);
                answer += popCnt;
            }

            return answer;
        }

        private Boolean[][] checkPop() {
            Boolean[][] pop = new Boolean[m][n];
            for (Boolean[] p : pop) {
                Arrays.fill(p, false);
            }

            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (board[i][j] == EMPTY || !canPop(i, j)) continue;

                    for (int k = 0; k < 4; k++) {
                        pop[i + dir[k][0]][j + dir[k][1]] = true;
                    }
                }
            }
            return pop;
        }

        private boolean canPop(int row, int col) {
            for (int i = 1; i < 4; i++) {
                if (board[row][col] != board[row + dir[i][0]][col + dir[i][1]]) {
                    return false;
                }
            }
            return true;
        }

        private void popBlock(Boolean[][] pop) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (pop[i][j]) {
                        board[i][j] = EMPTY;
                    }
                }
            }

            for (int j = 0; j < n; j++) {
                List<Character> friendsBlocks = new ArrayList<>();

                for (int i = m - 1; i >= 0; i--) {
                    if (board[i][j] != EMPTY) {
                        friendsBlocks.add(board[i][j]);
                    }
                }

                if (friendsBlocks.size() == m) continue;

                int i = m - 1;
                for (Character friendBlock : friendsBlocks) {
                    board[i--][j] = friendBlock;
                }
                while (i >= 0) {
                    board[i--][j] = EMPTY;
                }
            }
        }

        private int countPop(Boolean[][] pop) {
            return (int) Arrays.stream(pop)
                    .flatMap(Arrays::stream)
                    .filter(x -> x.equals(true))
                    .count();
        }
    }
}
