package michael.adkins.legacy.max.number.in.a.stack;

import java.util.ArrayList;
import java.util.List;

public class CustomStack {
    final List<Integer> stack = new ArrayList<>();
    final List<Integer> maxes = new ArrayList<>();

    /**
     * Pushes value onto the stack, but keeps another array for tracking Max
     * Space Complexity is double
     * @param value
     */
    public void push(Integer value) {
        stack.add(value);
        if(maxes.isEmpty() || maxes.get(maxes.size() -1) < value) {
            maxes.add(value);
        } else {
            maxes.add(maxes.get(maxes.size()-1));
        }
    }

    /**
     * Pops value from the stack, and keeps another array for tracking Max
     * Space Complexity is double
     * @return item at hte top of the stack
     */
    public Integer pop() {
        if(!maxes.isEmpty()) {
            maxes.remove(maxes.size()-1);
        }
        return stack.remove(stack.size()-1);
    }

    /**
     * Returns the Max Value by looking at the end of the maxes array.
     * Space Complexity is double, but
     * Time Complexity is constant.
     * @return the highest number in the stack.
     */
    public Integer maxValue() {
        return maxes.get(stack.size()-1);
    }
}
