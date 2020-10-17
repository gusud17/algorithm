import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class b5427 {
	private static final int[][] DIRS = {
			{-1, 0}, {1, 0}, {0, -1}, {0, 1}	
	};
	
	private static int w, h;
	private static char[][] map;
	private static Position src;
	private static boolean[][] visited;
	private static Queue<Node> fires;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for (int i = 0; i < t; i++) {
			w = sc.nextInt();
			h = sc.nextInt();
			map = new char[h][w];
			visited = new boolean[h][w];
			fires = new LinkedList<>();
			
			for (int j = 0; j < h; j++) {
				map[j] = sc.next().toCharArray();
				for (int k =0; k < w; k++) {
					if (map[j][k] == '@') {
						src = new Position(j, k);
						map[j][k] = '.';
					} else if (map[j][k] == '*') {
						fires.add(new Node(new Position(j, k), 0));
					}
				}
			}
			
			int ans = solve();
			if (ans == -1) System.out.println("IMPOSSIBLE");
			else System.out.println(ans);
		}
	}
	
	private static int solve() {
		Queue<Node> q  = new LinkedList<>();
		q.add(new Node(src, 0));
		src.visit();
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.success()) return cur.depth;
			while (!fires.isEmpty() && fires.peek().depth <= cur.depth) {
				Node fire = fires.poll();
				fires.addAll(fire.nextFire());
			}
			
			q.addAll(cur.nexts());
		}
		
		return -1;
	}

	static class Node {
		private final Position position;
		private final int depth;
		
		Node(Position position, int depth) {
			this.position = position;
			this.depth = depth;
		}

		public List<Node> nextFire() {
			List<Node> nexts = new ArrayList<>();
			
			for (int[] dir : DIRS) {
				Position np = position.next(dir);
				if (np.outOfRange() || !np.isEmpty()) continue;
				map[np.x][np.y] = '*';
				nexts.add(new Node(np, depth + 1));
			}
			
			return nexts;
		}

		public List<Node> nexts() {
			List<Node> nexts = new ArrayList<>();
			
			for (int[] dir : DIRS) {
				Position np = position.next(dir);
				if (np.outOfRange()) return Arrays.asList(new Node(np, depth + 1));
				if (np.invalid()) continue;
				np.visit();
				nexts.add(new Node(np, depth + 1));
			}
			
			return nexts;
		}

		public boolean success() {
			return position.outOfRange();
		}
		
	}
	
	static class Position {
		private final int x, y;
		
		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public void visit() {
			visited[x][y] = true;
		}

		public boolean outOfRange() {
			return x < 0 || y < 0 || x >= h || y>= w;
		}

		public boolean isEmpty() {
			return symbol() == '.';
		}
		
		public boolean invalid() {
			return !isEmpty() || visited[x][y];
		}

		public Position next(int[] dir) {
			return new Position(x + dir[0], y + dir[1]);
		}

		char symbol() {
			return map[x][y];
		}
	}
}

