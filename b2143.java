import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class b2143 {
    private static long T;
    private static int n, m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        n = sc.nextInt();
        List<Integer> pSumA = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pSumA.add(sc.nextInt());
        }
        addPSum(pSumA);

        m = sc.nextInt();
        List<Integer> pSumB = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            pSumB.add(sc.nextInt());
        }
        addPSum(pSumB);

        Collections.sort(pSumA);
        Collections.sort(pSumB);

        long answer = 0;
        for (int a : pSumA) {
            long chkB = T - a;
            answer += lowerBound(pSumB, chkB) - upperBound(pSumB, chkB);
        }

        System.out.println(answer);
    }

    private static int lowerBound(List<Integer> arr, long key) {
        int left = 0, right = arr.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr.get(mid) <= key) left = mid + 1;
            else right = mid;
        }
        return right;
    }

    private static int upperBound(List<Integer> arr, long key) {
        int left = 0, right = arr.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr.get(mid) < key) left = mid + 1;
            else right = mid;
        }
        return right;
    }

    private static void addPSum(List<Integer> nums) {
        int size = nums.size();

        for (int i = 0; i < size - 1; i++) {
            int sum = nums.get(i);
            for (int j = i + 1; j < size; j++) {
                sum += nums.get(j);
                nums.add(sum);
            }
        }
    }
}