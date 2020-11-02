import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class pGPS {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                7, 10, new int[][]{{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}},
                6, new int[]{1, 2, 3, 3, 6, 7}
        ));
        System.out.println(new Solution().solution(
                7, 10, new int[][]{{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}},
                6, new int[]{1, 2, 4, 6, 5, 7}
        ));
    }
}

class Solution {
    private List<Integer>[] graph;

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        graph = new List[n + 1];
        for (int[] edge : edge_list) {
            addEdge(graph, edge[0], edge[1]);
            addEdge(graph, edge[1], edge[0]);
        }
        for (int i = 0; i < n; i++) {
            addEdge(graph, i, i);
        }
        
        int[][] memo = new int[k][n + 1]; //i번 차례에 j를 갈 경우 경로를 수정하는 최소 횟수
        for (int i = 0; i < k; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        memo[0][gps_log[0]] = 0;

        for (int i = 1; i < k; i++) {
            for (int cur = 1; cur <= n; cur++) {
                if (memo[i - 1][cur] == Integer.MAX_VALUE) continue;

                for (int next : graph[cur]) {
                    int a = gps_log[i] == next ? 0 : 1;
                    memo[i][next] = Integer.min(memo[i][next], memo[i - 1][cur] + a);
                }
            }
        }

        int ret = memo[k - 1][gps_log[k - 1]];
        return ret != Integer.MAX_VALUE ? ret : -1;
    }

    private void addEdge(List<Integer>[] graph, int src, int dst) {
        if (graph[src] == null) {
            graph[src] = new ArrayList<>();
        }
        graph[src].add(dst);
    }
}
