package michael.adkins.legacy.stuff;

import michael.adkins.legacy.max.number.in.a.stack.CustomStack;

/**
 * Max Number in a stack is calculated by using double space complexity but
 * constant time by implementing a stack that uses a maxes list.
 */
public class MaxNumberInAStack {
    public static void main(String[] args) {
        CustomStack stack = new CustomStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(2);
        System.out.printf("Max: %d%n", stack.maxValue());
        System.out.printf("Popping Out: %d%n", stack.pop());
        System.out.printf("Max: %d%n", stack.maxValue());
        System.out.printf("Popping Out: %d%n", stack.pop());
        System.out.printf("Max: %d%n", stack.maxValue());
        System.out.printf("Popping Out: %d%n", stack.pop());
        System.out.printf("Max: %d%n", stack.maxValue());
        System.out.printf("Popping Out: %d%n", stack.pop());
    }
}
