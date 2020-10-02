import java.util.Arrays;
import java.util.Scanner;

public class b1197 {
    private static int V, E;
    private static Edge[] edges;
    private static UnionFind unionFind;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();

        unionFind = new UnionFind(V);
        edges = new Edge[E];
        for (int i = 0; i < E; i++) {
            edges[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        System.out.println(solve());
    }

    private static int solve() {
        Arrays.sort(edges);

        int answer = 0;
        for (Edge edge : edges) {
            if (edge.sameUnion(unionFind)) continue;
            answer += edge.weight;
            edge.union(unionFind);
        }
        return answer;
    }

    static class Edge implements Comparable<Edge> {
        private final int v1;
        private final int v2;
        private final int weight;

        public Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }

        public boolean sameUnion(UnionFind unionFind) {
            return unionFind.find(v1) == unionFind.find(v2);
        }

        public void union(UnionFind unionFind) {
            unionFind.union(v1, v2);
        }
    }

    static class UnionFind {
        private final int[] parent;

        UnionFind(int n) {
            this.parent = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (x == parent[x]) {
                return x;
            }

            parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            parent[px] = py;
        }
    }
}