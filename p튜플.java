import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p튜플 {
    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(new Solution().solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"))
        );
        System.out.println(
                Arrays.toString(new Solution().solution("{{1,2,3},{2,1},{1,2,4,3},{2}}"))
        );
        System.out.println(
                Arrays.toString(new Solution().solution("{{20,111},{111}}"))
        );
        System.out.println(
                Arrays.toString(new Solution().solution("{{123}}"))
        );
        System.out.println(
                Arrays.toString(new Solution().solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"))
        );
    }
}

class Solution {
    public int[] solution(String s) {
        Pattern p = Pattern.compile("[{][0-9,]*[}]");
        Matcher m = p.matcher(s);

        List<Tuple> tuples = new ArrayList<>();
        while (m.find()) {
            tuples.add(Tuple.of(m.group()));
        }
        Collections.sort(tuples);

        List<Integer> answer = new ArrayList<>();
        for (Tuple tuple : tuples) {
            answer.add(tuple.getElement(answer));
        }

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    static class Tuple implements Comparable<Tuple> {
        private final List<Integer> nums;

        public Tuple(List<Integer> nums) {
            this.nums = nums;
        }

        static Tuple of(String token) {
            Pattern p = Pattern.compile("[0-9]+");
            Matcher m = p.matcher(token);
            List<Integer> nums = new ArrayList<>();
            while (m.find()) {
                String n = m.group();
                nums.add(Integer.parseInt(n));
            }
            return new Tuple(nums);
        }

        @Override
        public int compareTo(Tuple o) {
            return nums.size() - o.nums.size();
        }

        public int getElement(List<Integer> answer) {
            for (int num : nums) {
                if (!answer.contains(num)) return num;
            }
            return 0;
        }
    }
}