import java.util.Scanner;

public class b1806 {
    private static int N, S;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        S = sc.nextInt();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(solve(arr));
    }

    private static int solve(int[] arr) {
        int left = 0, right = 0;
        int len = Integer.MAX_VALUE;

        int sum = arr[left];
        while (true) {
            while (sum < S && right < arr.length - 1) {
                right++;
                sum += arr[right];
            }
            if (sum < S) break;
            
            len = Math.min(len, right - left + 1);

            if (left == right) {
                right++;
                if (right == arr.length) break;
                sum += arr[right];
            }

            sum -= arr[left];
            left++;
        }
        return len == Integer.MAX_VALUE ? 0 : len;
    }
}