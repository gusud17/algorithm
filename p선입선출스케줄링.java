import java.util.Arrays;

public class p선입선출스케줄링 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(6, new int[]{1, 2, 3}));
    }
}

class Solution {
    public int solution(int n, int[] cores) {
        if (n <= cores.length) return n;

        int minCore = Arrays.stream(cores).min().orElse(10000);

        int minTime = n * minCore / cores.length;
        int maxTime = n * minCore;

        while (minTime < maxTime) {
            int midTime = (minTime + maxTime) / 2;

            int processedCnt = 0;
            int startCnt = 0;

            for (int core : cores) {
                processedCnt += midTime / core + 1;
                if (midTime % core == 0) {
                    startCnt++;
                    processedCnt--;
                }
            }

            if (processedCnt >= n) {
                maxTime = midTime;
            } else if (processedCnt + startCnt < n) {
                minTime = midTime + 1;
            } else {
                for (int i = 0; i < cores.length; i++) {
                    if (midTime % cores[i] == 0) processedCnt++;
                    if (processedCnt == n) return i + 1;
                }
            }
        }

        return 0;
    }
}