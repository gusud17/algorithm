import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class b1600 {
	private static final int[][] BASIC_DIRS = {
			{-1, 0}, {1, 0}, {0, -1}, {0, 1}
	};
	private static final int[][] HORSE_DIRS = {
			{-1, -2}, {-2, -1}, {1, -2}, {2, -1},
			{-1, 2}, {-2, 1}, {1, 2}, {2, 1}
	};
	
	private static int K, H, W;
	private static int[][] map;
	private static int[][] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		K = sc.nextInt();
		W = sc.nextInt();
		H = sc.nextInt();
		
		map = new int[H][W];
		visited = new int[H][W];
		
		for (int i = 0 ;i < H; i++) {
			Arrays.fill(visited[i], -1);
			for (int j = 0; j < W; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		System.out.println(solve());
	}

	static int solve() {
		Queue<Node> q = new LinkedList<>();
		Node src = new Node(0, 0, 0, 0);
		q.add(src);
		src.visit();
		
		while (!q.isEmpty()) {
			Node cur = q.poll();	
			if (cur.success()) return cur.depth;			
			q.addAll(cur.nexts());
		}
		
		return -1;
	}
	
	static class Node {
		private final int x, y;
		private final int depth;
		private final int horseMovCnt;
		
		public Node(int x, int y, int depth, int horseMovCnt) {
			this.x = x;
			this.y = y;
			this.depth = depth;
			this.horseMovCnt = horseMovCnt;
		}

		public List<Node> nexts() {
			List<Node> nexts = new ArrayList<>();
			
			for (int[] dir : BASIC_DIRS) {
				int nx = x + dir[0];
				int ny = y + dir[1];
				if (nx < 0 || ny < 0 || nx >= H || ny >= W || map[nx][ny] == 1) continue;

				if (visited[nx][ny] == -1 || visited[nx][ny] > horseMovCnt) {
					Node next = new Node(nx, ny, depth + 1, horseMovCnt);
					nexts.add(next);
					next.visit();
				}
			}
			
			if (horseMovCnt < K) {
				for (int[] dir : HORSE_DIRS) {
					int nx = x + dir[0];
					int ny = y + dir[1];
					if (nx < 0 || ny < 0 || nx >= H || ny >= W || map[nx][ny] == 1) continue;

					if (visited[nx][ny] == -1 || visited[nx][ny] > horseMovCnt + 1) {
						Node next = new Node(nx, ny, depth + 1, horseMovCnt + 1);
						nexts.add(next);
						next.visit();
					}
				}
			}
			
			return nexts;
		}

		void visit() {
			visited[x][y] = horseMovCnt;
		}
		
		boolean success() {
			return x == H - 1 && y == W - 1;
		}
	}
}

