import java.util.*;

class Solution {
    public int[] solution(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ans = getAnswer(ans);
        }
        return toArray(ans);
    }
    
    List<Integer> getAnswer(List<Integer> input) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        
        int p = 1;
        for (int k : input) {
            ans.add(k);
            ans.add(p);
            p = 1 - p;
        }

        return ans; 
    }
    
    int[] toArray(List<Integer> list) {
        int ans[] = new int[list.size()];
        
        for (int i = 0 ;i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
