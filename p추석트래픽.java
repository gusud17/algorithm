import java.util.*;

class Solution {
    public int solution(String[] lines) {
        List<TimeBlock> timeBlocks = new ArrayList();
        for (String line : lines) {
            double during = Double.parseDouble(line.substring(24, line.length() - 1));
            double endTime = timeToDouble(line.substring(11, 23));
            double startTime = endTime - during + 0.001;
            timeBlocks.add(new TimeBlock(startTime, endTime));
        }
        
        int ans = 0;
        for (int i = 0; i < timeBlocks.size(); i++) {
            int tmp = 1;
            double start = timeBlocks.get(i).end;
            for (int j = i + 1; j < timeBlocks.size(); j++) {
                if (timeBlocks.get(j).valid(start + 1)) {
                    tmp++;
                }
            }
            if (tmp > ans) ans = tmp;
        }
        
        return ans;
    }
    
    double timeToDouble(String str) {
        String[] tokens = str.split(":");
        return new Integer(tokens[0]) * 3600 + new Integer(tokens[1]) * 60 + Double.parseDouble(tokens[2]);
    }
    
    class TimeBlock {
        double start, end;
        
        TimeBlock(double start, double end) {
            this.start = start;
            this.end = end;
        }
        
        boolean valid(double t) {
            return t > start;
        }
        
        @Override
        public String toString() {
            return start + " - " + end;
        }
    }
}
