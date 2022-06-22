package michael.adkins.legacy.stuff;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class FindTheKthLargestElementInAList {

    /**
     * Uses Java library to do either merge-sort of quick-sort which is O(nlogn) time.
     * Space Complexity could be O(logn)
     * @param nums list of integers
     * @param k the kth largest number
     * @return kth largest number in the list
     */
    public static int findKthLargestElement1(List<Integer> nums, int k) {
        return nums.stream().sorted().toList().get(nums.size()-k);
    }

    /**
     * Uses a heap which is O(klogn) time. Space complexity is O(n) which is worse than sorted approach
     * @param nums list of integers
     * @param k the kth largest number
     * @return kth largest number in the list
     */
    public static int findKthLargestElement2(List<Integer> nums, int k) {
        Queue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        heap.addAll(nums);
        int result = 0;
        for (int i = 0; i < k; i++) {
            if(heap.peek() != null) {
                result = heap.poll();
            }
        }
        return  result;
    }

    /**
     * Uses Quick-Select algorithm which has a Time Complexity of O(n).  Space complexity is likely to be the same
     * as the sorted approach if Java's underlying implementation is quick-sort.
     * @param nums list of integers
     * @param k the kth largest number
     * @return kth largest number in the list
     */
    public static int findKthLargestElement(List<Integer> nums, int k) {
        return quickSelect(nums, 0, nums.size()-1, nums.size()-k);
    }

    private static int quickSelect(List<Integer> nums, int left, int right, int index) {
        if(left == right) {
            return nums.get(left);
        }
        int pivotIndex = new Random().ints(left, right).findAny().getAsInt();
        //move pivot to the beginning of the list
        Collections.swap(nums,left, pivotIndex);
        //partition
        int i = left;
        for (int j = left+1; j <right+1; j++) {
            if(nums.get(j) < nums.get(left)) {
                i++;
                Collections.swap(nums, i, j);
            }
        }
        //move pivot to correct location
        Collections.swap(nums, i, left);
        //only continue to partition one side
        if(index < i) {
            return quickSelect(nums, left, i-1, index);
        }
        if(index > i) {
            return quickSelect(nums, i+1, right, index);
        }
        return nums.get(i);
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(List.of(3, 5, 2, 4, 6, 8));
        int k = 3;
        System.out.println(findKthLargestElement1(nums, k));
        System.out.println(findKthLargestElement2(nums, k));
        System.out.println(findKthLargestElement(nums, k));
    }

}
