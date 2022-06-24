package michael.adkins.legacy.stuff;

import java.util.*;

/**
 * see IndicesOfTwoNumbersThatEqualASum
 */
public class ThreeSum {
    /**
     * @param nums list of numbers
     * @return List of a list of three numbers that, when added, equal zero
     */
    public static List<int[]> threeSumUsingBF(List<Integer> nums) {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                for (int k = j + 1; k < nums.size(); k++) {
                    if (nums.get(i) + nums.get(j) + nums.get(k) == 0) {
                        result.add(new int[]{nums.get(i), nums.get(j), nums.get(k)});
                    }
                }
            }
        }
        return result;
    }

    public static List<int[]> threeSumUsingHashMap(List<Integer> nums) {
//        nums.sort(Comparator.naturalOrder());
        List<Integer> sortedNums = nums.stream().sorted().toList();
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < sortedNums.size(); i++) {
            int[] triplets = twoSumUsingHashMap(sortedNums, i);
            if(triplets != null) {
                result.add(triplets);
            }
        }
        return result;
    }

    private static int[] twoSumUsingHashMap(List<Integer> nums, int startIndex) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        int target = nums.get(startIndex) * -1;
        for (int i = startIndex + 1; i < nums.size(); i++) {
            int diff = target - nums.get(i);
            if (indexMap.containsKey(diff)) {
                return new int[]{nums.get(i), diff, nums.get(startIndex)};
            }
            indexMap.put(nums.get(i), 1);
        }
        return null;
    }

    public static List<int[]> threeSumUsingHashMap1(List<Integer> nums) {
        List<Integer> sortedNums = nums.stream().sorted().toList();
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < sortedNums.size(); i++) {
            int target = sortedNums.get(i) * -1;
            List<Integer> subList = sortedNums.subList(i, sortedNums.size());
            int[] indices = IndicesOfTwoNumbersThatEqualASum.twoSumIndicesUsingHashMap(subList, target);
            if (indices != null) {
                result.add(new int[]{subList.get(indices[1]), subList.get(indices[0]), subList.get(0)});
            }
        }
        return result;
    }

    public static List<int[]> threeSumUsingIndices(List<Integer> nums) {
        List<Integer> sortedNums = nums.stream().sorted().toList();
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            twoSumUsingIndices(sortedNums, i, result);
        }
        return result;
    }

    private static void twoSumUsingIndices(List<Integer> nums, int startIndex, List<int[]> result) {
        int low = startIndex + 1;
        int high = nums.size() - 1;
        while (low < high) {
            int sum = nums.get(startIndex) + nums.get(low) + nums.get(high);
            if(sum < 0) {
                low++;
            } else if(sum > 0) {
                high--;
            } else {
                result.add(new int[]{nums.get(startIndex), nums.get(low), nums.get(high)});
                low++;
                high--;
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(List.of(-1, 0, 1, 2, -4, -3));
        System.out.println(Arrays.deepToString(threeSumUsingBF(nums).toArray()));
        System.out.println(Arrays.deepToString(threeSumUsingHashMap(nums).toArray()));
        System.out.println(Arrays.deepToString(threeSumUsingHashMap1(nums).toArray()));
        System.out.println(Arrays.deepToString(threeSumUsingIndices(nums).toArray()));
    }
}
