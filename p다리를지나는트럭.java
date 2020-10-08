import java.util.LinkedList;
import java.util.Queue;

public class p다리를지나는트럭 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(2, 10, new int[]{7, 4, 5, 6}));
        System.out.println(new Solution().solution(100, 100, new int[]{10}));
        System.out.println(new Solution().solution(100, 100, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10}));
    }
}


class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();
        int curBridgeWeight = 0;
        int truckNums = truck_weights.length;
        int truckPointer = 0;
        int time = 0;
        while (truckPointer < truckNums) {
            time++;

            if (bridge.size() == bridge_length) {
                curBridgeWeight -= bridge.poll();
            }

            int truck = truck_weights[truckPointer];
            if (truck + curBridgeWeight <= weight) {
                curBridgeWeight += truck;
                truckPointer++;
                bridge.add(truck);
            } else {
                bridge.add(0);
            }

            System.out.println(String.format("%d  - %s", time, bridge.toString()));
        }
        time += bridge_length;
        return time;
    }
}