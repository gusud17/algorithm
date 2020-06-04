import java.util.*;

class p블록게임 {
    public int solution(int[][] board) {
        Map<Integer, Block> blocks = new HashMap<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] > 0 && !blocks.containsKey(board[i][j])) {
                    Block block = new Block(i, j, board);
                    if (block.isInvalid()) continue;
                    blocks.put(board[i][j], block);
                }
            }
        }

        int ans = 0;
        int check;
        do {
            check = 0;
            for (Integer key : blocks.keySet()) {
                if (blocks.get(key).pop(board)) {
                    check++;
                }
            }
            ans += check;
        } while (check > 0);

        return ans;
    }

    class Block {
        private final int x, y;
        private final BlockType blockType;

        public Block(int x, int y, int[][] board) {
            this.x = x;
            this.y = y;
            this.blockType = BlockType.getBlockType(x, y, board);
        }

        boolean isInvalid() {
            return blockType == null;
        }

        boolean pop(int[][] board) {
            if (!canPop(board)) return false;

            board[x][y] = 0;
            for (int i =0; i < 3; i++) {
                board[x + blockType.dx.get(i)][y + blockType.dy.get(i)] = 0;
            }

            return true;
        }

        boolean canPop(int[][] board) {
            if (isInvalid() || board[x][y] == 0) return false;
            List<int[]> positions = blockType.getCheckPositions(x, y);
            for (int[] pos : positions) {
                if (board[x][y] == board[pos[0]][pos[1]] || board[pos[0]][pos[1]] == 0) continue;
                return false;
            }
            return true;
        }
    }

    public enum BlockType {
        HIGHL(Arrays.asList(1, 2, 2), Arrays.asList(0, 0, 1), Arrays.asList(1), 2), // L
        LOWL(Arrays.asList(1, 1, 1), Arrays.asList(0, 1, 2), Arrays.asList(1, 2), 1), // ㄴ_
        REVLOWL(Arrays.asList(1, 1, 1), Arrays.asList(0, -1, -2), Arrays.asList(-1, -2), 1), // __|
        REVHIGHL(Arrays.asList(1, 2, 2), Arrays.asList(0, 0, -1), Arrays.asList(-1), 2), // _|
        REVT(Arrays.asList(1, 1, 1), Arrays.asList(-1, 0, 1), Arrays.asList(-1, 1), 1);  // ㅗ

        private final List<Integer> dx, dy;
        private final List<Integer> cols;
        private final int row;

        BlockType(List<Integer> dx, List<Integer> dy, List<Integer> cols, int row) {
            this.dx = dx;
            this.dy = dy;
            this.cols = cols;
            this.row = row;
        }

        static BlockType getBlockType(int x, int y, int[][] board) {
            for (BlockType blockType : values()) {
                if (blockType.isMyType(x, y, board)) return blockType;
            }
            return null;
        }

        private boolean isMyType(int x, int y, int[][] board) {
            for (int i = 0; i < 3; i++) {
                int nx = x + dx.get(i);
                int ny = y + dy.get(i);
                if (nx < 0 || ny < 0 || nx >= board.length || ny >= board.length || board[x][y] != board[nx][ny]) {
                    return false;
                }
            }
            return true;
        }

        public List<int[]> getCheckPositions(int x, int y) {
            List<int[]> positions = new ArrayList<>();
            for (int i : cols) {
                for (int j = 0; j < row + x; j++) {
                    int[] position = {j, y + i};
                    positions.add(position);
                }
            }
            return positions;
        }
    }
}