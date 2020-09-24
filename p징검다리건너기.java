public class p징검다리건너기 {
    public static void main(String[] args) {
        System.out.println(
                new Solution().solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));

    }
}

class Solution {
    public int solution(int[] stones, int k) {
        int left = 1;
        int right = 200000000;


        while (left <= right) {
            int mid = (left + right) / 2;

            if (canGo(mid, stones, k)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean canGo(int mid, int[] stones, int k) {
        int cnt = 0;
        for (int i = 0; i < stones.length; i++) {
            if (stones[i] <= mid) cnt++;
            if (stones[i] > mid) cnt = 0;
            if (cnt >= k) return false;
        }
        return true;
    }
}