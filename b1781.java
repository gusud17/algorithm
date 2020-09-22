import java.util.Arrays;
import java.util.Scanner;

public class b1781 {
    private static int N;
    private static Problem[] problems;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        problems = new Problem[N];
        for (int i = 0; i < N; i++) {
            problems[i] = new Problem(sc.nextInt(), sc.nextInt());
        }

        Arrays.sort(problems);
        int[] ans = new int[N + 1];

        for (Problem problem : problems) {
            int p = problem.deadline;
            while (ans[p]!= 0) {
                p--;
            }

            if (p > 0) ans[p] = problem.reward;
        }

        System.out.println(Arrays.stream(ans).sum());
    }

    static class Problem implements Comparable<Problem> {
        private final int deadline;
        private final int reward;

        public Problem(int deadline, int reward) {
            this.deadline = deadline;
            this.reward = reward;
        }

        @Override
        public int compareTo(Problem o) {
            return o.reward - reward;
        }
    }
}