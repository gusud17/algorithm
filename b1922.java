import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class b1922 {
    private static int N, M;
    private static List<Edge>[] edges;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        edges = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int weight = sc.nextInt();
            edges[v1].add(new Edge(v2, weight));
            edges[v2].add(new Edge(v1, weight));
        }

        System.out.println(solve());
    }

    private static int solve() {
        int answer = 0;
        boolean[] selected = new boolean[N + 1];
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        Edge cur = new Edge(1, 0);

        do {
            if (!selected[cur.dest]) {
                selected[cur.dest] = true;
                priorityQueue.addAll(edges[cur.dest]);
                answer += cur.weight;
            }
            cur = priorityQueue.poll();
        } while (cur != null);

        return answer;
    }

    static class Edge implements Comparable<Edge> {
        private final int dest, weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}