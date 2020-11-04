import java.util.Arrays;

public class p폰켓몬 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{3, 1, 2, 3}));
        System.out.println(new Solution().solution(new int[]{3, 3, 3, 2, 2, 4}));
        System.out.println(new Solution().solution(new int[]{3, 3, 3, 2, 2, 2}));
    }
}

class Solution {
    public int solution(int[] nums) {
        int typeCnt = (int) Arrays.stream(nums)
                .distinct()
                .count();

        return Math.min(typeCnt, nums.length / 2);
    }
}
