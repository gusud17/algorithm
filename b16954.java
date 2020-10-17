import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class b16954 {
	private static final int[][] DIRS = {
			{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1}, {1, -1}, {-1, -1}, {1, 1}	
	};
	
	private static char[][] board;
	private static Position src;
	private static boolean[][] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		board = new char[8][8];
		visited = new boolean[8][8];
		
		for (int i = 0 ;i < 8; i++) {
			board[i] = sc.next().toCharArray();
		}
		
		src = new Position(7, 0);
		if (solve()) System.out.println(1);
		else System.out.println(0);
	}
	
	private static boolean solve() {
		Queue<Node> q  = new LinkedList<>();
		q.add(new Node(src, 0));
		src.visit();
		
		int depth = 0;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.success()) return true;
			if (depth < cur.depth) {
				depth++;
				moveWalls();
			}
			
			if (cur.canMove()) {			
				q.addAll(cur.nexts());
			}
		}
		
		return false;
	}
	
	static void moveWalls() {
		for (int i = 0; i < 8; i++) {
			for (int j = 7; j >0; j--) {
				board[j][i] = board[j - 1][i];
			}
			board[0][i] = '.';
		}
	}

	static class Node {
		private final Position position;
		private final int depth;
		
		Node(Position position, int depth) {
			this.position = position;
			this.depth = depth;
		}

		public boolean canMove() {
			return position.symbol() != '#';
		}

		public List<Node> nexts() {
			List<Node> nexts = new ArrayList<>();
			
			for (int[] dir : DIRS) {
				Position np = position.next(dir);
				if (np.outOfRange() || np.invalid()) continue;
				np.visit();
				nexts.add(new Node(np, depth + 1));
			}
			
			return nexts;
		}

		public boolean success() {
			return position.on(0, 7);
		}
	}
	
	static class Position {
		private final int x, y;
		
		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public void visit() {
			for (int i = 0; i < 8; i++) {
				if (board[i][y] == '#') return;
			}
			
			visited[x][y] = true;
		}

		public boolean on(int x, int y) {
			return this.x == x && this.y == y;
		}
		
		public boolean outOfRange() {
			return x < 0 || y < 0 || x >= 8 || y>= 8;
		}
		
		public boolean invalid() {
			return symbol() != '.' || visited[x][y];
		}

		public Position next(int[] dir) {
			return new Position(x + dir[0], y + dir[1]);
		}
		
		char symbol() {
			return board[x][y];
		}
	}
}

