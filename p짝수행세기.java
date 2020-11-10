import java.math.BigInteger;
import java.util.Arrays;

public class p짝수행세기 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]
                {{0, 1, 0}, {1, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
        System.out.println(new Solution().solution(new int[][]
                {{1, 0, 0}, {1, 0, 0}}));
        System.out.println(new Solution().solution(new int[][]
                {{1, 0, 0, 1, 1}, {0, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 0, 1}}));
    }
}

class Solution {
    private static final BigInteger MOD = BigInteger.valueOf(10000019);

    private int rowSize, colSize;

    public int solution(int[][] a) {
        rowSize = a.length;
        colSize = a[0].length;

        BigInteger[][] nCr = getCombis(rowSize);
        int[] oneCnt = getOneCnt(a);

        //dp[a][b] = 1열부터 a열까지 계산했을 때, b개의 홀수행을 가진 경우의 수
        int[][] dp = new int[colSize][rowSize + 1];
        dp[0][oneCnt[0]] = nCr[rowSize][oneCnt[0]].mod(MOD).intValue();

        for (int i = 1; i < colSize; i++) {
            for (int oddRow = 0; oddRow <= rowSize; oddRow++) {
                if (dp[i - 1][oddRow] == 0) continue;

                for (int overlap = 0; overlap <= oddRow; overlap++) {
                    if (overlap > oneCnt[i]) break;
                    int nOnes = oddRow + oneCnt[i] - overlap;
                    if (nOnes > rowSize) continue;

                    BigInteger tmp = BigInteger.valueOf(dp[i - 1][oddRow]);
                    tmp = tmp.multiply(nCr[oddRow][overlap]);
                    tmp = tmp.multiply(nCr[rowSize - oddRow][oneCnt[i] - overlap]);

                    int z = nOnes - overlap;
                    dp[i][z] = tmp.add(BigInteger.valueOf(dp[i][z])).mod(MOD).intValue();
                }
            }
        }

        return dp[colSize - 1][0];
    }

    private BigInteger[][] getCombis(int N) {
        BigInteger[][] nCr = new BigInteger[N + 1][N + 1];
        for (int n = 0; n <= N; n++) {

