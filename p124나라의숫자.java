import java.util.ArrayList;
import java.util.List;

public class p124나라의숫자 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(1));
        System.out.println(new Solution().solution(2));
        System.out.println(new Solution().solution(3));
        System.out.println(new Solution().solution(4));
        System.out.println(new Solution().solution(5));
        System.out.println(new Solution().solution(6));
        System.out.println(new Solution().solution(7));
        System.out.println(new Solution().solution(8));
        System.out.println(new Solution().solution(9));
        System.out.println(new Solution().solution(10));
        System.out.println(new Solution().solution(11));
        System.out.println(new Solution().solution(12));
        System.out.println(new Solution().solution(13));
        System.out.println(new Solution().solution(14));
        System.out.println(new Solution().solution(15));
        System.out.println(new Solution().solution(16));
        System.out.println(new Solution().solution(17));
        System.out.println(new Solution().solution(18));
    }
}


class Solution {
    public String solution(int n) {
        char[] newNums = new char[]{'4', '1', '2'};
        List<Integer> rest = new ArrayList<>();
        do {
            int r = n % 3;
            rest.add(r);
            n /= 3;
            if (r == 0) n--;
        } while (n != 0);

        StringBuffer sb = new StringBuffer();
        for (int num : rest) {
            sb.append(newNums[num]);
        }
        sb.reverse();
        return sb.toString();
    }
}