package michael.adkins.legacy.stuff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class NonDuplicateFinder {
    
    /**
     * T: O(2n) = O(n)
     * Space complexity is linear space
     * @param nums list of numbers
     * @return non-duplicate
     */
    public static int findSingleInteger(List<Integer> nums) {
        AtomicInteger result = new AtomicInteger();
        Map<Integer, Integer> ocurrences = new HashMap<>();
        nums.forEach(integer -> {
            ocurrences.put(integer, ocurrences.getOrDefault(integer, 0) + 1);
        });
            ocurrences.forEach((key, value) -> {
                if (value == 1) {
                    result.set(key);
                }
            });
        return result.get();
    }

    /**
     * T: O(n)
     * Space complexity is constant.
     * @param nums list of numbers
     * @return non-duplicate
     */
    public static int findSingleInteger1(List<Integer> nums) {
        int unique = 0;
        for(int n : nums) {
            unique ^= n; //5 ^ 5 = 0, but 5 ^ anything =
        }
        return unique;
    }

    public static void main(String[] args) {
        List<Integer> nums = List.of(4, 3, 2, 4, 1, 3, 2);
        System.out.printf("Single integer in %s is %d.%n", nums.toString(), findSingleInteger(nums));
        System.out.printf("Single integer in %s is %d.", nums.toString(), findSingleInteger1(nums));
    }
}
