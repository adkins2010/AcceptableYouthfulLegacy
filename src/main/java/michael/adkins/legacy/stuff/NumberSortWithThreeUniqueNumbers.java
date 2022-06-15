package michael.adkins.legacy.stuff;

import java.util.*;

public class NumberSortWithThreeUniqueNumbers {

    public static List<Integer> sortNums(List<Integer> nums) {
        List<Integer> sortedList = new ArrayList<>();
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        nums.forEach(integer -> {
            Integer count = counts.get(integer);
            counts.put(integer, (count != null ? count + 1 : 0));
        });
        //if you know that an array can only have 1, 2, 3 then this is possible
        for(int key = 1; key <= 3; key++) {
            for(int i = 0; i <= counts.get(key); i++) {
                sortedList.add(key);
            }
        }
        return sortedList;
    }

    public static List<Integer> sortNums2(List<Integer> nums) {
        List<Integer> sortedList = new ArrayList<>(List.copyOf(nums));
        int index = 0;
        int one_index = 0;
        int three_index = sortedList.size()-1;
        while(index < three_index) {
            switch(sortedList.get(index)){
                case 1:
                    Collections.swap(sortedList, index, one_index);
                    one_index++;
                    index++;
                case 2:
                    index++;
                case 3:
                    Collections.swap(sortedList, index, three_index);
                    three_index--;
            }
        }
        return sortedList;
    }
    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>(List.of(3, 3, 2, 1, 3, 2, 1));
        System.out.println(sortNums(nums));
        System.out.println(sortNums2(nums));
    }
}
