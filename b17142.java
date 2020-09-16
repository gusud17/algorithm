import java.util.*;

public class b17142 {
    static int N, M;
    static int map[][];
    static List<Virus> viruses;

    static int ans = Integer.MAX_VALUE;
    static int space = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        viruses = new ArrayList<>();
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 2) {
                    viruses.add(new Virus(i, j, 0));
                }
                if (map[i][j] == 0) {
                    space++;
                }
            }
        }

        combi(new Stack<>(), 0);
        if (Integer.MAX_VALUE == ans) ans = -1;
        System.out.println(ans);
    }

    static void combi(Stack<Virus> ret, int r) {
        if (ret.size() == M) {
            startVirus(ret);
            return;
        }

        if (r >= viruses.size()) return;

        ret.push(viruses.get(r));
        combi(ret, r + 1);
        ret.pop();
        combi(ret, r + 1);
    }

    static int[][] visited;

    static void startVirus(Stack<Virus> activateVirus) {
        Queue<Virus> virusQ = new LinkedList<>(activateVirus);
        initVisited(activateVirus);

        int cnt = 0;
        while (!virusQ.isEmpty()) {
            Virus cur = virusQ.poll();
            if (map[cur.x][cur.y] == 0) {
                cnt++;
            }
            if (cnt == space) {
                ans = Math.min(ans, cur.cnt);
                break;
            }
            virusQ.addAll(cur.next());
        }
    }

    private static void initVisited(Stack<Virus> activateVirus) {
        visited = new int[N][N];
        for (Virus v : activateVirus) {
            visited[v.x][v.y] = 1;
        }
    }

    static class Virus {
        private static final int[][] dirs = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        private final int x, y;
        private final int cnt;

        public Virus(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        List<Virus> next() {
            List<Virus> nexts = new ArrayList<>();
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] > 0)
                    continue;
                if (map[nx][ny] != 0 && map[nx][ny] != 2)
                    continue;

                visited[nx][ny] = cnt + 1;
                nexts.add(new Virus(nx, ny, cnt + 1));
            }
            return nexts;
        }
    }
}
