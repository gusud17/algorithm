import java.util.Arrays;

class Solution {    
    public int solution(int[] a) {
        int len = a.length;
        int[] fromLeft = new int[len];
        fromLeft[0] = Integer.MAX_VALUE;
        int[] fromRight = new int[len];
        fromRight[len - 1] = Integer.MAX_VALUE;
        
        for (int i = 1; i < len; i++) {
            fromLeft[i] = Math.min(fromLeft[i - 1], a[i - 1]);
            fromRight[len - i - 1] = Math.min(fromRight[len - i], a[len - i]);
        }
        
        int answer = 0;
        for (int i = 0; i < a.length; i++) {
            if (fromLeft[i] < a[i] && a[i] > fromRight[i]) continue;
            answer++;
        }
        return answer;
    }
}
