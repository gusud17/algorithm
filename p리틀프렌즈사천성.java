import java.util.PriorityQueue;

public class p리틀프렌즈사천성 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                3, 3, new String[]{"DBA", "C*A", "CDB"}
        ));
        System.out.println(new Solution().solution(
                2, 4, new String[]{"NRYN", "ARYA"}
        ));
        System.out.println(new Solution().solution(
                4, 4, new String[]{".ZI.", "M.**", "MZU.", ".IU."}
        ));
        System.out.println(new Solution().solution(
                2, 2, new String[]{"AB", "BA"}
        ));
        System.out.println(new Solution().solution(
                5, 5, new String[]{"FGHEI", "BAB..", "D.C*.", "CA..I", "DFHGE" }
        ));
    }
}

class Solution {
    private int m, n;
    private char[][] board;

    public String solution(int m, int n, String[] board) {
        this.m = m;
        this.n = n;
        this.board = new char[m][n];
        for (int i = 0; i < m; i++) {
            this.board[i] = board[i].toCharArray();
        }

        PriorityQueue<Node> pq = getPrioirityQueue();
        PriorityQueue<Node> nexts = new PriorityQueue<>();

        StringBuffer answer = new StringBuffer();

        while (!pq.isEmpty()) {
            Node src = pq.poll();
            Node dst = pq.poll();

            if (downSide(src, dst) || sideDown(src, dst)) {
                answer.append(src.symbol());

                src.removeSymbol();
                dst.removeSymbol();

                pq.addAll(nexts);
                nexts.clear();
            } else {
                nexts.add(src);
                nexts.add(dst);
            }
        }

        return nexts.isEmpty() ? answer.toString() : "IMPOSSIBLE";
    }

    private boolean downSide(Node src, Node dst) {
        for (int x = src.x + 1; x <= dst.x; x++) {
            if (board[x][src.y] != '.' && board[x][src.y] != src.symbol()) {
                return false;
            }
        }

        int left = Math.min(src.y, dst.y);
        int right = Math.max(src.y, dst.y);

        for (int y = left + 1; y < right; y++) {
            if (board[dst.x][y] != '.') {
                return false;
            }
        }
        return true;
    }

    private boolean sideDown(Node src, Node dst) {
        int left = Math.min(src.y, dst.y);
        int right = Math.max(src.y, dst.y);

        for (int y = left; y <= right; y++) {
            if (board[src.x][y] != '.' && board[src.x][y] != src.symbol()) {
                return false;
            }
        }

        for (int x = src.x + 1; x < dst.x; x++) {
            if (board[x][dst.y] != '.') {
                return false;
            }
        }
        return true;
    }

    private PriorityQueue<Node> getPrioirityQueue() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i][j];

