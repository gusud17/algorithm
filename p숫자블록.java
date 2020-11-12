import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p숫자블록 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(1, 10)));
        System.out.println(Arrays.toString(new Solution().solution(11, 20)));
        System.out.println(Arrays.toString(new Solution().solution(999999999, 1000000000)));
    }
}

class Solution {
    private static final long MAX = 10000000;

    public int[] solution(long begin, long end) {
        List<Integer> answer = new ArrayList<>();

        for (long i = begin; i <= end; i++) {
            answer.add(blockOf(i));
        }

        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    private int blockOf(long number) {
        if (number == 1) return 0;
        for (int i = 2; i * i <= number; i++) {
            if (number / i <= MAX && number % i == 0) {
                return (int) (number / i);
            }
        }
        return 1;
    }
}
