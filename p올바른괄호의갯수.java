public class p올바른괄호의갯수 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(2));
        System.out.println(new Solution().solution(3));
        System.out.println(new Solution().solution(4));
    }
}

class Solution {
    int n;

    public int solution(int n) {
        this.n = n;
        return go(1, 1);
    }

    int go(int depth, int opened) {
        if (depth == n) {
            return 1;
        }

        int ret = go(depth + 1, opened + 1);
        for (int i = 0; i < opened; i++) {
            ret += go(depth + 1, opened - i);
        }
        return ret;
    }
}
