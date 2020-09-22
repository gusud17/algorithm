import java.util.Stack;

public class p크레인인형뽑기게임 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                new int[][]{{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}},
                new int[]{1, 5, 3, 5, 1, 2, 1, 4}
        ));
    }
}

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        Stack<Integer> stack = new Stack<>();
        for (int move :moves) {
            int num = getN(board, move-1);
            if (num == 0) continue;
            if (stack.isEmpty() || stack.peek() != num) {
                stack.push(num);
            } else {
                stack.pop();
                answer+=2;
            }
        }

        return answer;
    }

    private int getN(int[][] board, int n) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][n] > 0) {
                int ans = board[i][n];
                board[i][n] = 0;
                return ans;
            }
        }
        return 0;
    }
}