import java.util.Arrays;

public class p최고의집합 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(
                2, 9
        )));
        System.out.println(Arrays.toString(new Solution().solution(
                2, 1
        )));
        System.out.println(Arrays.toString(new Solution().solution(
                2, 8
        )));
    }
}

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];

        if (s / n == 0) {
            return new int[]{-1};
        }

        int i = 0;
        while (n > 0) {
            answer[i] = s / n;
            s -= answer[i];
            i++;
            n--;
        }

        return answer;
    }
}
