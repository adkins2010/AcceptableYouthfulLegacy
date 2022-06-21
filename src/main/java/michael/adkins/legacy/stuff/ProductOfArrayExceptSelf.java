package michael.adkins.legacy.stuff;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {
    public static int[] productOfSiblings(int[] nums) {
        int[] right = new int[nums.length];
        int product = 1;

        for (int i = nums.length-2; i >= 0; i--) {
            product *= nums[i+1];
            right[i] = product;
        }
        product = 1;
        for (int i = 1; i < nums.length; i++) {
            product *= nums[i-1];
            right[i] *= product;
        }
        return right;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(productOfSiblings(nums)));
    }
}
