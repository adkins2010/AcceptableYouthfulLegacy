package michael.adkins.legacy.stuff;

import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidParentheses {
    static final Map<Character, Character> parenMap = Stream.of(new Character[][] {
            {'[', ']'},
            {'{', '}'},
            {'(', ')'}
    }).collect(Collectors.toMap(characters -> characters[0], characters -> characters[1]));
    static final Map<Character, Character> invParenMap = parenMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

    public static boolean isValid(String expression) {
        Stack<Character> parenStack = new Stack<>();
        for (char c: expression.toCharArray()) {
            if(parenMap.containsKey(c)) {
                parenStack.push(c);
            } else if (invParenMap.containsKey(c)) {
                if(parenStack.isEmpty() || !parenStack.peek().equals(invParenMap.get(c))) {
                    return false;
                }
                parenStack.pop();
            }
        }
        return parenStack.size() == 0;
    }

    public static void main(String[] args) {
        System.out.println(isValid("(){([])}"));
        System.out.println(isValid("(){(["));
    }
}
