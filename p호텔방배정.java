
import java.util.*;

public class p호텔방배정 {
    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(new Solution().solution(10, new long[]{1, 3, 4, 1, 3, 1})));

    }
}

class Solution {
    Map<Long, Long> map = new HashMap<>();

    private long find(Long i) {
        if (!map.containsKey(i)) return i;
        map.put(i, find(map.get(i)));
        return map.get(i);
    }

    public long[] solution(long k, long[] room_number) {
        List<Long> answer = new ArrayList<>();

        for (long num : room_number) {
            if (!map.containsKey(num)) {
                answer.add(num);
                map.put(num, find(num + 1));
            } else {
                long tmp = find(num);
                answer.add(tmp);
                map.put(tmp, find(tmp + 1));
            }
        }

        return answer.stream()
                .mapToLong(x -> x)
                .toArray();
    }
}