import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b2234 {
	private static int N, M;
	private static int[][] wall;
	private static boolean[][] visited;

	private static final int[][] dirs = { 
			{0, -1}, {-1, 0}, {0, 1}, {1, 0}
	};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		wall = new int[M][N];
		visited = new boolean[M][N];
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				wall[i][j] = sc.nextInt();
			}
		}
				
		int roomCnt = 0;
		int maxRoom = 0;
		for (int i = 0; i < M; i++) {
			for (int j =0; j< N; j++) {
				if (visited[i][j]) continue;
				maxRoom = Math.max(maxRoom, go(i, j));
				roomCnt++;
			}
		}
		
		System.out.println(roomCnt);
		System.out.println(maxRoom);
		System.out.println(getMaxMerge());
	}

	private static int getMaxMerge() {
		visited = new boolean[M][N];
	
		int maxRoom = 0;
		for (int i = 0; i < M; i++) {
			for (int j =0; j< N; j++) {
				for (int w = 1; w <= 8; w *= 2) {
					if ((wall[i][j] & w) == w) {
						visited = new boolean[M][N];
						wall[i][j] -= w;
						maxRoom = Math.max(maxRoom, go(i, j));
						wall[i][j] += w;
					}
				}
			}
		}
		return maxRoom;
	}

	private static int go(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		visited[x][y] = true;
		
		int size = 0;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			size++;
			int wallInfo = wall[cur[0]][cur[1]];
			
			for (int i = 0; i < 4; i++) {
				if ((wallInfo >> i & 1) == 1) continue;
				int nx = cur[0] + dirs[i][0];
				int ny = cur[1] + dirs[i][1];
				if (invalid(nx, ny) || visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				q.add(new int[] {nx, ny});
			}
		}
		return size;
	}
	
	static boolean invalid(int x, int y) {
		return x < 0 || y < 0 || x >= M || y >= N;
	}
}

