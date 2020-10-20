import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int p = 0;
        int ret = 0;
        for (int i = 0; i < B.length; i++) {
            if (B[i] > A[p]) {
                p++;
                ret++;
            }
        }
        
        return ret;
    }
}
