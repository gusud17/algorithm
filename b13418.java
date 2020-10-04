import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class b13418 {
    private static int N, M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i <= M; i++) {
            edges.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        List<Edge> ascEdges = edges.stream()
                .sorted(Comparator.comparingInt(Edge::getWeight))
                .collect(Collectors.toList());
        List<Edge> descEdges = edges.stream()
                .sorted((e1, e2) -> e2.weight - e1.weight)
                .collect(Collectors.toList());

        System.out.println(getWeights(descEdges) - getWeights(ascEdges));
    }

    private static long getWeights(List<Edge> edges) {
        UnionFind unionFind = new UnionFind(N);
        int answer = 0;
        for (Edge edge : edges) {
            if (edge.sameUnion(unionFind)) continue;
            answer += edge.weight;
            edge.union(unionFind);
        }

        return (long) Math.pow(answer, 2);
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
            this.weight = weight == 1 ? 0 : 1;
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