class Solution {
    public int solution(String s) {
        int answer = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int palidromeLength = Math.max(palidromeLength(s, i, i + 1), 1 + palidromeLength(s, i - 1, i + 1));
            answer = Math.max(palidromeLength, answer);
        }       

        return answer;
    }
    
    int palidromeLength(String s, int left, int right) {
        int ret = 0;
        
        while (true) {            
            if (left < 0 || right >= s.length()) break;
            if (s.charAt(left) != s.charAt(right)) break;
            ret += 2;
            left--;
            right++;
        }
        
        return ret;
    }
}
