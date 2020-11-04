import java.util.Arrays;

public class p쿼드압축후개수세기 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(
                new int[][]{
                        {1, 1, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 1},
                        {1, 1, 1, 1}
                })));
        System.out.println(Arrays.toString(new Solution().solution(
                new int[][]{
                        {1, 1, 1, 1, 1, 1, 1, 1},
                        {0, 1, 1, 1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 1, 1, 1, 1},
                        {0, 1, 0, 0, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0, 1, 1},
                        {0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 1, 0, 0, 1},
                        {0, 0, 0, 0, 1, 1, 1, 1}
                })));
    }
}

class Solution {
    int[] answer;

    public int[] solution(int[][] arr) {
        answer = new int[2];
        go(0, 0, arr.length, arr);
        return answer;
    }

    int[][] D = {
            {0, 0}, {0, 1}, {1, 0}, {1, 1}
    };

    void go(int x, int y, int size, int[][] arr) {
        int check = arr[x][y];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (check == arr[i + x][j + y]) continue;

                size /= 2;
                for (int[] d : D) {
                    go(x + d[0] * size, y + d[1] * size, size, arr);
                }
                return;
            }
        }
        
        answer[check]++;
    }
}
