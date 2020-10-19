import java.util.Arrays;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        
        int answer = 0;
        int min = 0;
        int max = distance;
        
        while (min <= max) {
            int mid = (min + max) / 2;
            int prev = 0;
            
            int cnt = 0;
            for (int i = 0; i < rocks.length; i++) {
                if (rocks[i] - prev < mid) cnt++;
                else prev = rocks[i];
            }            
            if (distance - prev < mid) cnt++;
            
            if (cnt > n) {
                max = mid - 1;
                continue;
            }
            
            if (answer < mid) answer = mid;
            min = mid + 1;
        }
        return answer;
    }
}
