import java.util.Collections;
import java.util.PriorityQueue;

public class p야근지수 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                4, new int[]{4, 3, 3}
        ));
        System.out.println(new Solution().solution(
                1, new int[]{2, 1, 2}
        ));
        System.out.println(new Solution().solution(
                3, new int[]{1, 1}
        ));
    }
}

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) {
            priorityQueue.add(work);
        }

        for (int i = 0; i < n; i++) {
            if (priorityQueue.isEmpty()) break;
            int w = priorityQueue.poll() - 1;
            if (w > 0) {
                priorityQueue.add(w);
            }
        }

        long answer = 0;

        for (int work : priorityQueue) {
            answer += (long) Math.pow(work, 2);
        }
        return answer;
    }
}
