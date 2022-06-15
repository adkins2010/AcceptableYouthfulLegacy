package michael.adkins.legacy.stuff;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PythagoreanTriplets {

    /**
     * This approach has Space Complexity of O(n), but
     * Time Complexity of O(n^3)
     * @param nums input numbers to test for Pythagorean triplets
     * @return true if a^2 + b^2 = c^2 can be found, and false otherwise
     */
    public static boolean findPythagoreanTriplets(List<Integer> nums) {
        for (Integer a : nums) {
            for (Integer b : nums) {
                for (Integer c : nums) {
                    if(a*a + b*b == c*c) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Space Complexity of O(n^2), but Time Complexity of O(n^2) + O(1) or O(nlogn)
     * @param nums input numbers to test for Pythagorean triplets
     * @return true if a^2 + b^2 = c^2 can be found, and false otherwise
     */
    public static boolean findPythagoreanTriplets1(List<Integer> nums) {
        Set<Integer> squares = nums.stream().map(n -> n*n).collect(Collectors.toSet());
        for (Integer a: nums) {
            for (Integer b : nums) {
                if(squares.contains(a*a + b*b)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<Integer> nums = List.of(3, 5, 12, 5, 13);
        System.out.println(findPythagoreanTriplets(nums));
        System.out.println(findPythagoreanTriplets1(nums));
    }
}
