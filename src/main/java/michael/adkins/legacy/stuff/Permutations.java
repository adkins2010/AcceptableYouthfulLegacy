package michael.adkins.legacy.stuff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Permutations {
    public static List<List<Integer>> permute(List<Integer> nums, int start) {

        if (start == nums.size() - 1) {
            return List.of(List.copyOf(nums));
        }
        List<List<Integer>> permutations = new ArrayList<>();
        for (int i = start; i < nums.size(); i++) {
            Collections.swap(nums, start, i);
            permutations.addAll(permute(nums, start + 1));
            Collections.swap(nums, start, i);
        }
        return permutations;
    }

    public static List<List<Integer>> permute1(List<Integer> nums, List<Integer> values) {
        if (nums == null || nums.isEmpty()) {
            return List.of(values);
        }
        List<List<Integer>> permutations = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> collect = Stream.concat(nums.subList(0, i).stream(), nums.subList(i + 1, nums.size()).stream()).collect(Collectors.toList());
            List<Integer> vals = Stream.concat(values.stream(), Stream.of(nums.get(i))).collect(Collectors.toList());
            permutations.addAll(permute1(collect, vals));
        }
        return permutations;
    }

    public static List<List<Integer>> quickPermCounting(List<Integer> nums) {
        List<Integer> values = new ArrayList<>(List.copyOf(nums));
        List<List<Integer>> permutations = new ArrayList<>();

        int[] weightedIndices = new int[nums.size()];
        int upperBoundIndex = 1;
        permutations.add(List.copyOf(values));
        while(upperBoundIndex < nums.size()) {
            if(weightedIndices[upperBoundIndex] < upperBoundIndex) {
                int lowerBoundIndex = upperBoundIndex%2*weightedIndices[upperBoundIndex];
                Collections.swap(values, lowerBoundIndex, upperBoundIndex);
                weightedIndices[upperBoundIndex]++;
                upperBoundIndex = 1;
                permutations.add(List.copyOf(values));
            } else {
                weightedIndices[upperBoundIndex] = 0;
                upperBoundIndex++;
            }
        }
        return permutations;
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3));
        System.out.println(permute(nums, 0));
        System.out.println(permute1(nums, new ArrayList<>()));
        System.out.println(quickPermCounting(nums));
    }
}
