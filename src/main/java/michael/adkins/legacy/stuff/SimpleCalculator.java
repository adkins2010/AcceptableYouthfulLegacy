package michael.adkins.legacy.stuff;

import java.util.List;

public class SimpleCalculator {
    public static int evaluate(StringBuilder expression) {
        int index = 0;
        int result = 0;
        Character op = '+';
        while(index < expression.length()) {

            char c = expression.charAt(index);
            if(List.of('+', '-').contains(c)) {
                op = c;
            } else {
                int value = 0;
                if(Character.isDigit(c)) {
//                    if(value == 0) {
//                        value = Integer.parseInt(String.valueOf(c));
//                    } else {
//                        value = Integer.parseInt(String.valueOf(value) + c);
//                    }
                    value = Integer.parseInt(String.valueOf(c));
                } else if(c == '(') {
                    value = evaluate(new StringBuilder(expression.substring(index+1)));
                    expression.replace(index, expression.indexOf(")"), (String.valueOf(c)));
                }
                if(op.equals('+')) {
                    result+=value;
                } else if(op.equals('-')) {
                    result-=value;
                }
            }
            index++;
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(evaluate(new StringBuilder("(1+(2+(3+(4+5))))")));
    }
}
