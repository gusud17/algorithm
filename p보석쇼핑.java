import java.util.*;

public class p보석쇼핑 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"})));
        System.out.println(Arrays.toString(new Solution().solution(new String[]{"AA", "AB", "AC", "AA", "AC"})));
        System.out.println(Arrays.toString(new Solution().solution(new String[]{"XYZ", "XYZ", "XYZ"})));
        System.out.println(Arrays.toString(new Solution().solution(new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"})));
        System.out.println(Arrays.toString(new Solution().solution(new String[]{"DIA", "EM", "EM", "RUB", "DIA"})));
    }
}

class Solution {
    public int[] solution(String[] gems) {
        long gemType = Arrays.stream(gems)
                .distinct()
                .count();

        Map<String, Integer> gemMap = new HashMap<>();
        gemMap.put(gems[0], 1);

        int l = 0, r = 0;

        List<int[]> answers = new ArrayList<>();

        while (true) {
            if (gemMap.keySet().size() == gemType) {
                answers.add(new int[]{l + 1, r + 1});
                if (l < r) {
                    gemMap.compute(gems[l], (k, v) -> v -= 1);
                    if (gemMap.get(gems[l]) == 0) gemMap.remove(gems[l]);
                    l++;
                    continue;
                }
            }

            r++;
            if (r == gems.length) break;
            gemMap.putIfAbsent(gems[r], 0);
            gemMap.compute(gems[r], (k, v) -> v += 1);
        }

        Collections.sort(answers, (int[] a, int[] b) -> {
            if (getLength(a) == getLength(b)) return a[0] - b[0];
            return getLength(a) - getLength(b);
        });

        return answers.get(0);
    }

    int getLength(int[] arr) {
        return arr[1] - arr[0];
    }
}