package michael.adkins.legacy.word.search;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Grid {
    int numberOfRows;
    int numberOfColumns;
    List<List<Character>> matrix;

    public Grid(List<List<Character>> matrix) {
        this.matrix = matrix;
        numberOfRows = matrix.size();
        numberOfColumns = matrix.get(0).size();
    }

    public boolean hasWord(String word) {
        for (int i = 0; i < numberOfRows; i++) {
            if(horizontalSearch(word, i)) {
                return true;
            }
        }
        for (int i = 0; i < numberOfColumns; i++) {
            if(verticalSearch(word, i)) {
                return true;
            }
        }
        return false;
    }

    private boolean verticalSearch(String word, int index) {
        for (int i = 0; i < numberOfRows; i++) {
            if(!matrix.get(i).get(index).equals(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean horizontalSearch(String word, int index) {
        for (int i = 0; i < matrix.get(index).size(); i++) {
            if(!matrix.get(index).get(i).equals(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public static List<List<Character>> parseIntoMatrix(String[] words) {
        List<List<Character>> matrix = Arrays.stream(words).map((String word) -> {
            return word.chars().mapToObj((int code) -> (char) code).collect(Collectors.toList());
        }).toList();
        return matrix;
    }
    public static void main(String[] args) {
        String[] words = {"FACI", "OBQP", "ANOB", "MASS"};
        Grid grid = new Grid(parseIntoMatrix(words));
         System.out.println(grid.hasWord("FOAM"));
    }
}
