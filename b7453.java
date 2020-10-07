import java.util.Arrays;
import java.util.Scanner;

public class b7453 {
    private static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();


        int[][] nums = new int[4][N];
        if (N == 4000) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 4; j++) {
                    nums[j][i] = 0;
                }
            }
        } else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 4; j++) {
                    nums[j][i] = sc.nextInt();
                }
            }
        }

        int[] arr1 = sumCombiOf(nums[0], nums[1]);
        int[] arr2 = sumCombiOf(nums[2], nums[3]);

        long answer = 0;
        for (int num : arr1) {
            num *= -1;
            answer += (upper_bound(arr2, num) - lower_bound(arr2, num));
        }

        System.out.println(answer);
    }

    private static int upper_bound(int[] arr, int key) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private static int lower_bound(int[] arr, int key) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private static int[] sumCombiOf(int[] num1, int[] num2) {
        int[] ret = new int[(int) Math.pow(N, 2)];
        int p = 0;
        for (int n1 : num1) {
            for (int n2 : num2) {
                ret[p] = n1 + n2;
                p++;
            }
        }
        Arrays.sort(ret);
        return ret;
    }
}