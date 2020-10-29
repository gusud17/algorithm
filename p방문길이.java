import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class p방문길이 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution("ULURRDLLU"));  //7
        System.out.println(new Solution().solution("LULLLLLLU"));  //7
        System.out.println(new Solution().solution("UDU"));  //1
        System.out.println(new Solution().solution("LRLRL"));  //1
        System.out.println(new Solution().solution("LLLLRLRLRLL"));//5
        System.out.println(new Solution().solution("UUUUDUDUDUUU"));//5
        System.out.println(new Solution().solution("LURDLURDLURDLURDRULD"));//7
        System.out.println(new Solution().solution("RRRRRRRRRRRRRRRRRRRRRUUUUUUUUUUUUULU"));//11
    }
}

class Solution {
    private static final Map<Character, Pos> dirMap = new HashMap<>();
    public static final int MAX = 11;

    static {
        dirMap.put('U', new Pos(-1, 0));
        dirMap.put('D', new Pos(1, 0));
        dirMap.put('R', new Pos(0, 1));
        dirMap.put('L', new Pos(0, -1));
    }

    public int solution(String dirs) {
        boolean[][][][] visited = new boolean[MAX][MAX][MAX][MAX];
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                for (int k = 0; k < MAX; k++) {
                    Arrays.fill(visited[i][j][k], false);
                }
            }
        }

        int answer = 0;

        Pos cur = new Pos(MAX / 2, MAX / 2);
        for (int i = 0; i < dirs.length(); i++) {
            Pos next = cur.next(dirMap.get(dirs.charAt(i)));
            if (next.invalid()) continue;

            if (!cur.isVisit(visited, next)) {
                answer++;
                cur.visit(visited, next);
            }

            cur = next;
        }

        return answer;
    }

    static class Pos {
        private final int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Pos next(Pos dir) {
            int nx = x + dir.x;
            int ny = y + dir.y;
            return new Pos(nx, ny);
        }

        boolean invalid() {
            return x < 0 || y < 0 || x >= MAX || y >= MAX;
        }

        boolean isVisit(boolean[][][][] visited, Pos next) {
            return visited[x][y][next.x][next.y];
        }

        void visit(boolean[][][][] visited, Pos next) {
            visited[x][y][next.x][next.y] = true;
            visited[next.x][next.y][x][y] = true;
        }
    }
}
