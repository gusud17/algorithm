import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class b16137 {
	private static final int[][] DIRS = {
			{-1, 0}, {1, 0}, {0, -1}, {0, 1}
	};
	
	private static int N, M;
	private static int[][] map;
	private static int[][] visited; //0 아직 안지나감, 1 다리 놓고 지나감, 2 다리 안놓고 지나감
	private static boolean[][] canMakeBridge;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		visited = new int[N][N];
		canMakeBridge = new boolean[N][N];
		
		for (int i = 0 ;i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		initCanMakeBridge();
		
		System.out.println(solve());
	}
	
	static void initCanMakeBridge() {
		for (int x = 0 ;x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (map[x][y] == 0) {
					boolean checkRow = true;
					boolean checkCol = true;
					
					for (int i = 0; i < 2; i++) {
						int nx = x + DIRS[i][0];
						int ny = y + DIRS[i][1];
						if (nx < 0 || ny < 0 || nx >= N || ny >= N )continue;
						
						if (map[nx][ny] == 0) checkRow = false;
					}
					
					for (int i = 2; i < 4; i++) {
						int nx = x + DIRS[i][0];
						int ny = y + DIRS[i][1];
						if (nx < 0 || ny < 0 || nx >= N || ny >= N )continue;
						
						if (map[nx][ny] == 0) checkCol = false;
					}
					
					canMakeBridge[x][y] = checkRow || checkCol;
				}
			}
		}
	}
	
	private static int solve() {
		Queue<Node> q  = new LinkedList<>();
		Node src = new Node(0, 0, 0, false, false);
		q.add(src);
		src.visit();
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.success()) return cur.time;
			q.addAll(cur.nexts());
		}
		
		return 0;
	}

	static class Node {
		private final int x, y;
		private final int time;
		private final boolean beforeBridge;
		private final boolean didBridge;
		
		public Node(int x, int y, int time, boolean beforeBridge, boolean didBridge) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.beforeBridge = beforeBridge;
			this.didBridge = didBridge;
		}
		
		void visit() {
			visited[x][y] = didBridge ? 1 : 2;
		}
		
		public boolean success() {
			return x == N - 1 && y == N - 1;
		}
		
		List<Node> nexts() {
			List<Node> nexts = new ArrayList<>();
			int nt = time + 1;
			
			for (int[] dir : DIRS) {
				int nx = x + dir[0];
				int ny = y + dir[1];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N 
						|| visited[nx][ny] == 2) continue;
				
				if (didBridge && visited[nx][ny] == 1) continue;
				if (map[nx][ny] == 0 && (didBridge || !canMakeBridge[nx][ny])) continue;
				if (map[nx][ny] != 1 && beforeBridge) continue;
				
				Node next;
				if (map[nx][ny] == 1) {
					next = new Node(nx, ny, nt, false, didBridge);
				} else {
					int bridgePeriod = map[nx][ny] == 0 ? M : map[nx][ny];
					
					if ((time + 1) % bridgePeriod != 0) {
						next = new Node(x, y, nt,  beforeBridge, didBridge);
					} else {
						next = new Node(nx, ny, nt, true, map[nx][ny] == 0 ? true : didBridge);
					}
				}
				
				nexts.add(next);
				next.visit();
			}
			
			return nexts;
		}
	}
}

