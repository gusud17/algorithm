package com.gusud;

import java.util.*;

public class b17140 {

    static int r, c, k;
    static int[][] arr;

    static int rowSize = 3, colSize = 3;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        r = sc.nextInt() - 1;
        c = sc.nextInt() - 1;
        k = sc.nextInt();

        arr = new int[100][100];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int cnt = 0;
        while (arr[r][c] != k) {
            if (++cnt > 100) {
                cnt = -1;
                break;
            }
            if (rowSize < colSize) C();
            else R();
        }

        System.out.println(cnt);
    }

    private static void C() {
        reverseArr();
        R();
        reverseArr();
    }

    private static void reverseArr() {
        int[][] narr = new int[100][100];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                narr[j][i] = arr[i][j];
            }
        }
        arr = narr;

        int tmp = rowSize;
        rowSize = colSize;
        colSize = tmp;
    }

    public static void R() {
        colSize = 0;
        for (int i = 0; i < rowSize; i++) {
            List<Bundle> bundles = convert(arr[i]);

            int size = Math.min(50, bundles.size());
            arr[i] = new int[100];
            for (int j = 0; j < size; j++) {
                Bundle b = bundles.get(j);
                arr[i][j * 2] = b.num;
                arr[i][j * 2 + 1] = b.cnt;
            }

            colSize = Math.max(size * 2, colSize);
        }
    }

    private static List<Bundle> convert(int[] nums) {
        Arrays.sort(nums);

        List<Bundle> bundles = new ArrayList<>();

        Bundle bundle = null;
        for (int num : nums) {
            if (num == 0) continue;
            if (bundle == null) bundle = new Bundle(num);
            else if (bundle.num == num) bundle.cnt++;
            else {
                bundles.add(bundle);
                bundle = new Bundle(num);
            }
        }
        bundles.add(bundle);
        Collections.sort(bundles);
        return bundles;
    }

    static class Bundle implements Comparable<Bundle> {
        private int num, cnt;

        public Bundle(int num) {
            this.num = num;
            cnt = 1;
        }

        @Override
        public int compareTo(Bundle o) {
            if (cnt == o.cnt) return num - o.num;
            return cnt - o.cnt;
        }
    }
}
