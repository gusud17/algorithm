package com.gusud;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b5373 {

    /*
               0  1  2
               3  4  5
    L        F 6  7  8  R         B
    36 37 38  18 19 20  45 46 47  27 28 29
    39 40 41  21 22 23  48 49 50  30 31 32
    42 43 44  24 25 26  51 52 53  33 34 35
               9 10 11
              12 13 14
              15 16 17
     */

    private static final int[][] rotateSide = new int[][]{
            {36, 37, 38, 18, 19, 20, 45, 46, 47, 27, 28, 29},   //U
            {44, 43, 42, 35, 34, 33, 53, 52, 51, 26, 25, 24},   //D
            {38, 41, 44, 9, 10, 11, 51, 48, 45, 8, 7, 6},       //F
            {47, 50, 53, 17, 16, 15, 42, 39, 36, 0, 1, 2},      //B
            {29, 32, 35, 15, 12, 9, 24, 21, 18, 6, 3, 0},       //L
            {20, 23, 26, 11, 14, 17, 33, 30, 27, 2, 5, 8}       //R
    };

    private static final int[] rotateFace = new int[]{
            0, 1, 2, 6, 3, 0, 8, 7, 6, 2, 5, 8
    };

    private static final String FACE = "UDFBLR";
    private static final char[] COLOR = {'w', 'y', 'r', 'o', 'g', 'b'};

    private static char[] cube = new char[54];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            initCube();

            int n = sc.nextInt();
            for (int j = 0; j < n; j++) {
                String rotateWay = sc.next();
                rotateCube(rotateWay.charAt(0), rotateWay.charAt(1));
            }

            print();
        }
    }

    private static void initCube() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                cube[i * 9 + j] = COLOR[i];
            }
        }
    }

    private static void rotateCube(char face, char dir) {
        int f = FACE.indexOf(face);
        int cnt = dir == '+' ? 1 : 3;

        while (cnt > 0) {
            cnt--;

            Queue<Character> q1 = new LinkedList<>();
            Queue<Character> q2 = new LinkedList<>();
            for (int i = 0; i < 12; i++) {
                q1.add(cube[rotateSide[f][i]]);
                q2.add(cube[rotateFace[i] + f * 9]);
            }

            for (int i = 0; i < 3; i++) {
                q1.add(q1.poll());
                q2.add(q2.poll());
            }

            for (int i = 0; i < 12; i++) {
                cube[rotateSide[f][i]] = q1.poll();
                cube[rotateFace[i] + f * 9] = q2.poll();
            }
        }
    }

    private static void print() {
        for (int j = 0; j < 9; j++) {
            System.out.print(cube[j]);
            if ((j + 1) % 3 == 0) System.out.println();
        }
    }
}
