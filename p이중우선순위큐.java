
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

class Solution {
    public int[] solution(String[] operations) {
        Heap minHeap = new MinHeap();
        Heap maxHeap = new MaxHeap();
        Map<Integer, Integer> map = new HashMap<>();

        for (String operation : operations) {
            char command = operation.charAt(0);
            Integer num = Integer.parseInt(operation.split(" ")[1]);
            if (command == 'I') {
                minHeap.insert(num);
                maxHeap.insert(num);
                map.putIfAbsent(num, 0);
                map.put(num, map.get(num) + 1);
                continue;
            }
            Heap heap = num == 1 ? maxHeap : minHeap;
            num = heap.poll();
            while (num != null && map.get(num) == 0) {
                num = heap.poll();
            }
            if (num != null) map.put(num, map.get(num) - 1);
        }

        int[] answer = {0, 0};
        List<Integer> nums = map.keySet().stream()
                .filter(x -> map.get(x) != 0)
                .sorted()
                .collect(Collectors.toList());

        if (!nums.isEmpty()) {
            answer[0] = nums.get(nums.size() - 1);
            answer[1] = nums.get(0);
        }

        return answer;
    }

    abstract class Heap {
        private final int[] heap;
        private int size = 0;

        Heap(int firstVal) {
            heap = new int[1000001];
            heap[0] = firstVal;
        }

        void insert(int num) {
            size++;
            int cur = size;
            heap[cur] = num;

            while (true) {
                int parent = cur / 2;
                if (compare(cur, parent)) {
                    swap(parent, cur);
                    cur = parent;
                } else break;
            }
        }

        abstract boolean compare(int parent, int cur);

        void swap(int i, int j) {
            int tmp = heap[i];
            heap[i] = heap[j];
            heap[j] = tmp;
        }

        Integer poll() {
            if (size == 0) return null;

            int ans = heap[1];
            heap[1] = heap[size];
            heap[size] = 0;
            size--;

            int cur = 1;
            while (true) {
                int child = cur * 2;

                if (child >= size) break;

                if (child + 1 < size) {
                    child = compare(child + 1, child) ? child + 1 : child;
                }

                if (compare(child, cur)) {
                    swap(child, cur);
                    cur = child;
                } else break;
            }

            return ans;
        }

        int valueOf(int i) {
            return heap[i];
        }
    }

    class MaxHeap extends Heap {
        MaxHeap() {
            super(Integer.MAX_VALUE);
        }

        @Override
        boolean compare(int parent, int child) {
            return valueOf(parent) > valueOf(child);
        }
    }

    class MinHeap extends Heap {
        MinHeap() {
            super(Integer.MIN_VALUE);
        }

        @Override
        boolean compare(int parent, int cur) {
            return valueOf(parent) < valueOf(cur);
        }
    }
}
