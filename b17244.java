import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;
import java.util.Scanner;

public class b17244 {
	private static int N, M;
	private static char[][] map;
	private static boolean[][] visited;
	private static boolean[] check;
	private static List<Coordinate> umbs;
	private static Coordinate src, dest;
	private static int ans;
	
	public static void main(String[] args) {
		umbs = new ArrayList<>();
		src = null;
		dest = null;
		ans = Integer.MAX_VALUE;
		
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			map[i] = sc.next().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'S') src = new Coordinate(i, j);
				else if (map[i][j] == 'X') umbs.add(new Coordinate(i, j));
				else if (map[i][j] == 'E') dest = new Coordinate(i, j);
			}
		}
		
		check = new boolean[umbs.size()];
		perm(new Stack<>());
		
		System.out.println(ans);
	}
	
	private static void perm(Stack<Integer> seq) {
		if (seq.size() == umbs.size()) {
			int cnt = 0;
			Coordinate from = src;
			for (int i : seq) {
				Coordinate to = umbs.get(i);
				cnt += bfs(from, to);
				from = to;
			}
			
			cnt += bfs(from, dest);
			ans = Math.min(ans, cnt);
			return;
		}
		
		for (int i = 0; i < umbs.size(); i++) {
			if (check[i]) continue;
			check[i] = true;
			seq.push(i);
			perm(seq);
			check[i] = false;
			seq.pop();
		}
	}
	
	private static int bfs(Coordinate from, Coordinate to) {
		visited = new boolean[N][M];

		Queue<Node> q = new LinkedList<>();
		Node src = new Node(from, 0);
		q.add(src);
		src.visit();
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.coordinate.equals(to)) return cur.depth;
			q.addAll(cur.nexts());
		}
		
		return Integer.MAX_VALUE;
	}
	
	private static class Coordinate {
		private final int x, y;
		
		Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Coordinate next(int[] dir) {
			return new Coordinate(x + dir[0], y + dir[1]);
		}

		public boolean invalid() {
			return x < 0 || y < 0 || x >= N || y >= M;
		}
		
		@Override
		public boolean equals(Object o) {
			Coordinate other = (Coordinate) o;
			return x == other.x && y == other.y;
		}
	}
	
	private static class Node {
		private static final int[][] dirs = {
				{-1, 0}, {1, 0}, {0, -1}, {0, 1}
		};
		
		private final Coordinate coordinate;
		private final int depth;
		
		public Node(Coordinate coordinate, int depth) {
			this.coordinate = coordinate;
			this.depth = depth;
		}
		
		List<Node> nexts() {
			List<Node> nexts = new ArrayList<>();
			
			for (int[] dir : dirs) {
				Node next = next(dir);
				
				if (next.invalid() || next.visited() || next.symbol() == '#') continue;
				
				nexts.add(next);
				next.visit();
			}
			
			return nexts;
		}
		
		private void visit() {
			visited[coordinate.x][coordinate.y] = true;
		}

		char symbol() {
			return map[coordinate.x][coordinate.y];
		}
		
		Node next(int[] dir) {
			return new Node(coordinate.next(dir), depth + 1);
		}
		
		boolean invalid() {
			return coordinate.invalid();
		}
		
		boolean visited() {
			return visited[coordinate.x][coordinate.y];
		}
 	}
}

