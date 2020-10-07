import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class b2661 {
    private static final char[] NUM = {'1', '2', '3'};

    private static int N;
    private static String answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        List<Character> nums = new ArrayList<>();
        nums.add(NUM[0]);

        addNum(nums);

        System.out.println(answer);
    }

    private static void addNum(List<Character> nums) {
        if (nums.size() == N) {
            StringBuffer sb = new StringBuffer();
            for (char c : nums) {
                sb.append(c);
            }
            answer = sb.toString();
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (canAdd(nums, NUM[i]) && answer == null) {
                nums.add(NUM[i]);
                addNum(nums);
                nums.remove(nums.size() - 1);
            }
        }
    }

    static boolean canAdd(List<Character> nums, char num) {
        int size = nums.size();
        int len = size / 2 + size % 2;

        for (int i = 1; i <= len; i++) { //비교하는 수의 개수
            int p = size - i + 1;
            int lp = size - 2 * i + 1;

            boolean isSame = true;
            while (p <= size) {
                int lNum = nums.get(lp);
                int rNum = p < size ? nums.get(p) : num;

                if (lNum != rNum) {
                    isSame = false;
                    break;
                }

                p++;
                lp++;
            }

            if (isSame) return false;
        }
        return true;
    }
}