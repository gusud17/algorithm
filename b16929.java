import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class b16929 {
	private static int N, M;
	private static char[][] map;
	private static boolean[][] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new char[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = sc.next().toCharArray();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && go(new Stack<>(), new Node(i, j, 1))) {
					System.out.println("Yes");
					return;
				}
			}
		}
		System.out.println("No");
	}
	
	private static final int[][] dirs = {
			{-1, 0}, {1, 0}, {0, -1}, {0, 1}
	};
	
	static boolean go(Stack<Node> path, Node node) {
		node.visit();
		path.push(node);
		for (int[] dir : dirs) {
			Node next = node.next(dir);
			
			if (next.invalid() || next.symbol() != node.symbol()) continue;
			if (next.checkCycle(path)) return true;			
			if (next.visited()) continue;
			
			next.visit();
			if (go(path, next)) return true;
		}
		path.pop();
		return false;
	}
	
	static class Node {
		private final int x, y;
		private final int depth;
		
		Node(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
		
		public char symbol() {
			return map[x][y];
		}

		public boolean checkCycle(List<Node> path) {
			for (int i = 0; i < path.size() - 2; i++) {
				if (equals(path.get(i))) return true;
			}
			return false;
		}

		public boolean visited() {
			return visited[x][y];
		}

		public boolean isCycle(Node src) {
			if (depth == 3) return false;
			return src.x ==x && src.y == y;
		}

		public boolean invalid() {
			return x < 0 || y < 0 || x >= N || y >= M;
		}

		public Node next(int[] dir) {
			return new Node(x + dir[0], y + dir[1], depth + 1);
		}

		void visit() {
			visited[x][y] = true;
		}
		
		@Override
		public boolean equals(Object o) {
			Node other = (Node) o;
			return other.x == x && other.y == y;
		}
	}
}

