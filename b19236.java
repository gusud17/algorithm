import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class b19236 {
    static int ans = 0;
    static Fish[][] map = new Fish[4][4];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Fish fish = new Fish(sc.nextInt(), sc.nextInt() - 1);
                map[i][j] = fish;
            }
        }

        go(new Shark());
        System.out.println(ans);
    }

    private static void go(Shark shark) {
        Fish[][] tmp = copyOf(map);

        shark.eat();
        moveFish(shark);
        List<Shark> next = shark.nexts();

        if (next.size() == 0) {
            ans = Math.max(ans, shark.cnt);
        } else {
            for (Shark n : next) {
                go(n);
            }
        }

        map = copyOf(tmp);
    }

    private static Fish[][] copyOf(Fish[][] map) {
        Fish[][] nmap = new Fish[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j] != null)
                    nmap[i][j] = map[i][j].clone();
            }
        }
        return nmap;
    }

    private static void moveFish(Shark shark) {
        for (int f = 1; f <= 16; f++) {
            boolean check = true;
            for (int i = 0; i < 4 && check; i++) {
                for (int j = 0; j < 4 && check; j++) {
                    if (map[i][j] == null) continue;
                    if (map[i][j].num == f) {
                        check = false;
                        map[i][j].move(i, j, shark);
                    }
                }
            }
        }
    }

    private static final int[][] DIRS = {
            {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}
    };

    static class Fish {
        private int num, dir;

        public Fish(int num, int dir) {
            this.num = num;
            this.dir = dir;
        }

        void move(int x, int y, Shark shark) {
            for (int i = 0; i < 8; i++) {
                int nx = x + DIRS[dir][0];
                int ny = y + DIRS[dir][1];

                if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || shark.in(nx, ny)) {
                    nextDir();
                    continue;
                }

                Fish tmp = map[nx][ny];
                map[nx][ny] = map[x][y];
                map[x][y] = tmp;
                return;
            }
        }

        private void nextDir() {
            dir = (dir + 1) % 8;
        }

        protected Fish clone() {
            return new Fish(num, dir);
        }
    }

    static class Shark {
        private final int x, y;
        private int dir;
        private int cnt;

        public Shark() {
            x = 0;
            y = 0;
            cnt = 0;
        }

        public Shark(Shark shark, int nx, int ny) {
            cnt = shark.cnt;
            x = nx;
            y = ny;
        }

        boolean in(int x, int y) {
            return this.x == x && this.y == y;
        }

        List<Shark> nexts() {
            List<Shark> nexts = new ArrayList<>();

            int nx = x;
            int ny = y;

            while (true) {
                nx += DIRS[dir][0];
                ny += DIRS[dir][1];

                if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) {
                    break;
                }

                if (map[nx][ny] != null) {
                    nexts.add(new Shark(this, nx, ny));
                }
            }

            return nexts;
        }

        public void eat() {
            cnt += map[x][y].num;
            dir = map[x][y].dir;
            map[x][y] = null;
        }
    }
}
