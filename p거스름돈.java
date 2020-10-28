import java.util.Arrays;

public class p거스름돈 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                5, new int[]{1, 2, 5}
        ));
    }
}

class Solution {
    public static final int MOD = 1000000007;

    public int solution(int n, int[] money) {
        int[] ret = new int[n + 1];
        Arrays.fill(ret, 0);

        ret[0] = 1;

        for (int m : money) {
            for (int i = 0; i <= n; i++) {
                if (i >= m) {
                    ret[i] += ret[i - m] % MOD;
                }
            }
        }

        return ret[n];
    }
}
