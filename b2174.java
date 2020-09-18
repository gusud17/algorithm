import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class b2174 {
    static int A, B, N, M;
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[A][B];
        List<Robot> robots = new ArrayList<>();
        robots.add(null);
        for (int i = 0; i < N; i++) {
            Robot robot = new Robot(sc.nextInt() - 1, sc.nextInt() - 1, sc.next().charAt(0));
            map[robot.x][robot.y] = i + 1;
            robots.add(robot);
        }

        List<Command> commands = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            commands.add(new Command(sc.nextInt(), sc.next().charAt(0), sc.nextInt()));
        }

        for (Command command : commands) {
            Robot robot = robots.get(command.robotIndex);
            for (int i = 0; i < command.cnt; i++) {
                if (!robot.operate(command.type)) {
                    if (robot.inRange()) {
                        System.out.println(String.format("Robot %d crashes into robot %d", command.robotIndex, map[robot.x][robot.y]));
                    } else {
                        System.out.println(String.format("Robot %d crashes into the wall", command.robotIndex));
                    }
                    return;
                }
            }
        }

        System.out.println("OK");
    }

    static class Robot {
        private static int[][] DIRS = {
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };
        private static final String NESW = "NESW";
        private int x, y;
        private int dir;

        public Robot(int x, int y, char dir) {
            this.x = x;
            this.y = y;
            this.dir = NESW.indexOf(dir);
        }

        public boolean operate(char type) {
            if (type == 'F') {
                int robotId = map[x][y];
                map[x][y] = 0;
                x = x + DIRS[dir][0];
                y = y + DIRS[dir][1];

                if (!inRange()) {
                    return false;
                }
                if (map[x][y] > 0) {
                    return false;
                }
                map[x][y] = robotId;
            } else if (type == 'R') {
                dir = (dir + 1) % 4;
            } else if (type == 'L') {
                dir = (dir + 3) % 4;
            }

            return true;
        }

        public boolean inRange() {
            return !(x < 0 || y < 0 || x >= A || y >= B);
        }
    }

    static class Command {
        private int robotIndex, cnt;
        private char type;

        public Command(int robot, char type, int cnt) {
            this.robotIndex = robot;
            this.cnt = cnt;
            this.type = type;
        }
    }
}