package michael.adkins.legacy.stuff;

import java.util.List;

public class NonDecreasingArray {

    /**
     * Given an array with numbers, the array needs to be modified to where it's non-decreasing.  However, you can only
     *     change one index.  The method will check if this is possible.
     *     problemIndex = 0
     *     problemIndex = n-2
     *     val[index] <= val[index+2]
     *     val[index-1] <= val[index+1]
     * @param nums list of numbers
     * @return true if it only takes one or less indices to be changed.  False, if otherwise.
     */
    public static boolean canBeModifiedForNonDecreasing(List<Integer> nums) {
        int problemIndex = -1;
        for (int i = 0; i < nums.size()-1; i++) {
            if(nums.get(i) > nums.get(i+1)) {
                if(problemIndex >= 0) {
                    return false;
                }
                problemIndex = i;
            }
        }
        if(problemIndex == 0) {
            return  true;
        }
        if(problemIndex == nums.size()-2) {
            return  true;
        }
        if(nums.get(problemIndex) <= nums.get(problemIndex+2)) {
            return true;
        }
        return nums.get(problemIndex - 1) <= nums.get(problemIndex + 1);
    }

    public static void main(String[] args) {
        System.out.println(canBeModifiedForNonDecreasing(List.of(4, 1, 2)));
        System.out.println(canBeModifiedForNonDecreasing(List.of(3, 2, 4, 1)));
    }

}
