import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        long min = 0;
        Arrays.sort(times);
        long max = times[times.length - 1] * (long) n;

        long answer = max;
        while (min <= max) {
            long mid = (min + max) / 2;
            long cnt = 0;
            for (int t : times) {
                cnt += mid / t;
            }
            
            if (cnt < n){
                min = mid + 1;
                continue;
            }
            if (mid < answer) {
                answer = mid;
            }
            max = mid - 1;
        }
        return answer;
    }
}
