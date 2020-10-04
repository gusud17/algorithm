import java.util.*;

public class b1647 {
    private static int N, M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            edges.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        Collections.sort(edges, Comparator.comparingInt(Edge::getWeight));

        System.out.println(solve(edges));
    }

    private static long solve(List<Edge> edges) {
        UnionFind unionFind = new UnionFind(N);

        int answer = 0;
        int unionCount = N;
        for (Edge edge : edges) {
            if (unionCount == 2) break;
            if (edge.sameUnion(unionFind)) continue;
            answer += edge.weight;
            edge.union(unionFind);
            unionCount--;
        }

        return answer;
    }

    static class UnionFind {
        private final int[] parent;

        public UnionFind(int N) {
            this.parent = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] == x) return x;
            parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }

    static class Edge {
        private final int v1, v2, weight;

        public Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        public boolean sameUnion(UnionFind unionFind) {
            return unionFind.find(v1) == unionFind.find(v2);
        }

        public void union(UnionFind unionFind) {
            unionFind.union(v1, v2);
        }

        public int getWeight() {
            return weight;
        }
    }
}