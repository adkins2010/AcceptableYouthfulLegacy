package michael.adkins.legacy.stuff;

import java.util.*;

public class TopKFrequentElements {
    public static List<Integer> topFrequent(List<Integer> nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (Integer n: nums) {
            count.put(n, (count.get(n) != null ? count.get(n)+1 : 1));
        }
        Queue<int[]> heap = new PriorityQueue<>((int[] x, int[] y) -> {
            if (x[1] != y[1]) {
                return Integer.compare(x[1], y[1]);
            }
            return Integer.compare(x[0], y[0]);
        });
        count.forEach((num, c) -> heap.add(new int[]{ -c, num}));
        List<Integer> result = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            result.add(heap.peek() != null ? heap.poll()[1] : 0);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(topFrequent(List.of(1, 1, 1, 2, 2, 3), 2));
    }
}
