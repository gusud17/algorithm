import java.util.Scanner;

public class b1520 {
	private static final int[][] DIRS = {
			{-1, 0}, {1, 0}, {0, -1}, {0, 1}
	};
	
	private static int N, M;
	private static int[][] map;
	private static int[][] pathCnt;
	private static boolean[][] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		pathCnt = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0 ;i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		System.out.println(go(0, 0));
	}

	static int go(int x, int y) {
		if (x == N - 1 && y == M - 1) return 1;
		if (visited[x][y]) return pathCnt[x][y];
		
		for (int[] dir : DIRS) {
			int nx = x + dir[0];
			int ny = y + dir[1];
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= M ||
					map[x][y] <= map[nx][ny]) continue;
			
			pathCnt[x][y] += go(nx, ny);
		}
		
		visited[x][y] = true;
		
		return pathCnt[x][y];
	}
}

