import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b17836 {
	private static int N, M, T;
	private static int[][] map;
	private static int[][] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		T = sc.nextInt();
		
		map = new int[N][M];
		visited = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int ret = solve(new Yongsa(0, 0, false, 0));
		if (ret == -1 || ret > T) System.out.println("Fail");
		else System.out.println(ret);
	}
	
	private static int solve(Yongsa yongsa) {
		Queue<Yongsa> q = new LinkedList<>();
		q.add(yongsa);
		yongsa.visit();
		
		while (!q.isEmpty()) {
			Yongsa cur = q.poll();
			if (cur.success()) return cur.depth;
			
			q.addAll(cur.nexts());
		}
		
		return -1;
	}
	
	private static class Yongsa {
		private static final int[][] dirs = {
				{-1, 0}, {1, 0}, {0, -1}, {0, 1}
		};
		
		private final int x, y;
		private final boolean hasGram;
		private final int depth;
		
		public Yongsa(int x, int y, boolean hasGram, int depth) {
			this.depth = depth;
			this.x = x;
			this.y = y;
			this.hasGram = hasGram;
		}
		
		void visit() {
			int i = hasGram ? 2 : 1;
			visited[x][y] = i;
		}
		
		boolean success() {
			return x == N-1 && y == M-1;
		}
		
		List<Yongsa> nexts() {
			List<Yongsa> nexts = new ArrayList<>();
			
			for (int[] dir : dirs) {
				int nx = x + dir[0];
				int ny = y + dir[1];
				if (invalid(nx, ny)) continue;
				
				Yongsa next = next(nx, ny);
				if (next.visited() || next.cantGo()) continue;
				
				nexts.add(next);
				next.visit();
			}
			
			return nexts;
		}
		
		Yongsa next(int nx, int ny) {
			return new Yongsa(nx, ny, this.hasGram || map[nx][ny] == 2, depth + 1);
		}
		
		boolean invalid(int x, int y) {
			return x < 0 || y < 0 || x >= N || y >= M;
		}
		
		boolean visited() {
			if (hasGram) return visited[x][y] == 2;
            return visited[x][y] != 0;
		}
		
		boolean cantGo() {
			if (hasGram) return false;
			return map[x][y] == 1;
		}
 	}
}

