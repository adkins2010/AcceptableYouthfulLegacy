package michael.adkins.legacy.stuff;

import java.util.*;

public class IndicesOfTwoNumbersThatEqualASum {
    /**
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     * @param nums list of numbers
     * @param targetSum target sum
     * @return indices of two different numbers that equaled the sum
     */
    public static int[] twoSumIndicesUsingBF(List<Integer> nums, int targetSum) {
            for(int i = 0; i < nums.size(); i++) {
                for(int j = i+1; j < nums.size(); j++) {
                    if ((!Objects.equals(nums.get(i), nums.get(j))) && (targetSum == (nums.get(i) + nums.get(j)))) {
                        return new int[]{i, j};
                    }
                }
            }
            return null;
    }

    /**
     * Uses hash map to keep the numbers and indices.  Then iterates through
     * the list of numbers given and subtracts each number by its target sum
     * and uses the hash map to see if it's present.  If it is then it returns
     * index of the difference and current index in an array,
     * if not then it adds the current value and index to the aforementioned hash map.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param nums list of numbers
     * @param targetSum target sum
     * @return indices of two different numbers that equaled the sum
     */
    public static int[] twoSumIndicesUsingHashMap(List<Integer> nums, int targetSum) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for(int i = 0; i < nums.size(); i++) {
            int diff = targetSum - nums.get(i);
            if(indexMap.containsKey(diff)){
                return new int[]{indexMap.get(diff),  i};
            }
            indexMap.put(nums.get(i), i);
        }
        return null;
    }
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(List.of(2, 7, 11, 15));
        int targetSum = 18;
        System.out.println(Arrays.toString(twoSumIndicesUsingBF(nums, targetSum)));
        System.out.println(Arrays.toString(twoSumIndicesUsingHashMap(nums, targetSum)));
    }
}
