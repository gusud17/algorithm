public class p멀쩡한사각형 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(8, 12));
    }
}

class Solution {
    public long solution(int w, int h) {
        int gcd = gcd(w, h);
        int wp = w / gcd;
        int hp = h / gcd;

        long origin = ((long) w) * h;
        long trash = (h / hp) * (wp + hp - 1);
        return origin - trash;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}