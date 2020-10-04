import java.util.*;

public class b2887 {
    private static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        List<Planet> planets = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            planets.add(new Planet(i, sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        System.out.println(solve(planets));
    }

    private static long solve(List<Planet> planets) {
        List<Edge> edges = extractEdges(planets);
        Collections.sort(edges);
        UnionFind unionFind = new UnionFind(N);

        int answer = 0;
        int cnt = 0;
        for (Edge edge : edges) {
            if (edge.sameUnion(unionFind)) continue;

            answer += edge.weight;
            edge.union(unionFind);
            cnt++;
            if (cnt == N - 1) break;
        }

        return answer;
    }

    private static List<Edge> extractEdges(List<Planet> planets) {
        List<Edge> edges = new ArrayList<>();

        planets.sort(Comparator.comparingInt(Planet::getX));
        edges.addAll(connectSide(planets));
        planets.sort(Comparator.comparingInt(Planet::getY));
        edges.addAll(connectSide(planets));
        planets.sort(Comparator.comparingInt(Planet::getZ));
        edges.addAll(connectSide(planets));

        return edges;
    }

    private static List<Edge> connectSide(List<Planet> planets) {
        List<Edge> edges = new ArrayList<>();
        Planet cur = planets.get(0);
        for (int i = 1; i < N; i++) {
            Planet next = planets.get(i);
            edges.add(new Edge(cur, next, cur.dist(next)));
            cur = next;
        }
        return edges;
    }

    static class Edge implements Comparable<Edge> {
        private final int v1, v2;
        private final int weight;

        public Edge(Planet v1, Planet v2, int weight) {
            this.v1 = v1.id;
            this.v2 = v2.id;
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

        public UnionFind(int N) {
            this.parent = new int[N];
            for (int i = 0; i < N; i++) {
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

    static class Planet {
        private final int id, x, y, z;

        public Planet(int id, int x, int y, int z) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        int dist(Planet o) {
            int dx = Math.abs(o.x - x);
            int dy = Math.abs(o.y - y);
            int dz = Math.abs(o.z - z);

            int dist = Math.min(dx, dy);
            return Math.min(dist, dz);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }
    }
}