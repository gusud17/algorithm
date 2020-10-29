import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p하노이의탑 {
    public static void main(String[] args) {
        int[][] ans = new Solution().solution(2);
        for (int[] l : ans) {
            System.out.println(Arrays.toString(l));
        }
    }
}

class Solution {
    private List<int[]> seq;

    public int[][] solution(int n) {
        seq = new ArrayList<>();

        hanoi(n, 1, 3, 2);

        int[][] answer = new int[seq.size()][];
        for (int i = 0; i < seq.size(); i++) {
            answer[i] = seq.get(i);
        }
        return answer;
    }

    private void hanoi(int n, int from, int to, int via) {
        if (n == 1) {
            seq.add(new int[]{from, to});
        } else {
            hanoi(n - 1, from, via, to);
            seq.add(new int[]{from, to});
            hanoi(n - 1, via, to, from);
        }
    }
}
