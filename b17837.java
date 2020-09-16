import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class b17837 {
    static int N, K;
    static int[][] board;
    static List<Piece>[][] pieceBoard;
    static List<Piece> pieces = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        board = new int[N][N];
        pieceBoard = new List[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = sc.nextInt();
                pieceBoard[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < K; i++) {
            Piece piece = new Piece(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt() - 1);
            pieces.add(piece);
            pieceBoard[piece.x][piece.y].add(piece);
        }

        for (int i = 1; i <= 1000; i++) {
            for (Piece piece : pieces) {
                if (piece.move() >= 4) {
                    System.out.println(i);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    static class Piece {
        private static final int[][] DIRS = {
                {0, 1}, {0, -1}, {-1, 0}, {1, 0}
        };
        private int x, y;
        private int dx, dy;

        public Piece(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            dx = DIRS[dir][0];
            dy = DIRS[dir][1];
        }

        public int move() {
            int nx = x + dx;
            int ny = y + dy;

            if (isBlue(nx, ny)) {
                //blue
                dx *= -1;
                dy *= -1;
                nx = x + dx;
                ny = y + dy;

                if (!isBlue(nx, ny)) {
                    moveRedBlue(nx, ny);
                }
            } else {
                moveRedBlue(nx, ny);
            }

            return pieceBoard[x][y].size();
        }

        private void moveRedBlue(int nx, int ny) {
            int i = pieceBoard[x][y].indexOf(this);
            List<Piece> mover = pieceBoard[x][y].subList(i, pieceBoard[x][y].size());
            if (board[nx][ny] == 1) Collections.reverse(mover); //red
            pieceBoard[x][y] = pieceBoard[x][y].subList(0, i);
            pieceBoard[nx][ny].addAll(mover);
            for (Piece p : mover) {
                p.x = nx;
                p.y = ny;
            }
        }

        private boolean isBlue(int nx, int ny) {
            return nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == 2;
        }
    }
}
