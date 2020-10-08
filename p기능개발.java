import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p기능개발 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(
                new int[]{93, 30, 55}, new int[]{1, 30, 5}
        )));
    }
}


class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> days = new ArrayList<>();
        for (int i = 0; i < progresses.length; i++) {
            int work = 100 - progresses[i];
            int day = work / speeds[i] + (work % speeds[i] > 0 ? 1 : 0);
            days.add(day);
        }

        int bef = days.get(0);
        int distribute = 1;

        List<Integer> answer = new ArrayList<>();

        for (int i = 1; i < days.size(); i++) {
            if (bef < days.get(i)) {
                answer.add(distribute);
                distribute = 1;
                bef = days.get(i);
            } else {
                distribute += 1;
            }
        }

        answer.add(distribute);
        return answer.stream()
                .mapToInt(x -> x)
                .toArray();
    }
}