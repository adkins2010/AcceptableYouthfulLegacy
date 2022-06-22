package michael.adkins.legacy.stuff;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class FindTheKthLargestElementInAList {

    /**
     * Uses Java library to do either merge-sort of quick-sort which is O(nlogn) time.
     * @param nums list of integers
     * @param k the kth largest number
     * @return kth largest number in the list
     */
    public static int findKthLargestElement1(List<Integer> nums, int k) {
        return nums.stream().sorted().toList().get(nums.size()-k);
    }

    /**
     * Uses a heap which is O(klogn) time.
     * @param nums list of integers
     * @param k the kth largest number
     * @return kth largest number in the list
     */
    public static int findKthLargestElement2(List<Integer> nums, int k) {
        return nums.stream().sorted().toList().get(nums.size()-k);
    }


}
