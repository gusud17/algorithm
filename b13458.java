package com.gusud;

import java.util.Scanner;

public class b13458 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, chief, deputy;
        int[] candidates;

        n = sc.nextInt();

        candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }

        chief = sc.nextInt();
        deputy = sc.nextInt();

        System.out.println(solution(n, candidates, chief, deputy));
    }

    private static long solution(int n, int[] candidates, int chief, int deputy) {
        long ans = n;

        for (int candidate : candidates) {
            candidate -= chief;

            if (candidate <= 0) continue;

            ans += candidate / deputy;
            if (candidate % deputy > 0) ans += 1;
        }

        return ans;
    }

}
