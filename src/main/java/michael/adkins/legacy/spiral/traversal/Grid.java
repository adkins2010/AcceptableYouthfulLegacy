package michael.adkins.legacy.spiral.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Grid {
    enum Direction {
        RIGHT(0),
        UP(1),
        LEFT(2),
        DOWN(3);
        final int value;

        Direction(int i) {
            this.value = i;
        }
    }

    private final List<List<Integer>> matrix;

    public Grid(List<List<Integer>> matrix) {
        this.matrix = new ArrayList<>();
        matrix.forEach((List<Integer> integers) -> {
            this.matrix.add(new ArrayList<>(integers));
        });
    }

    private int[] nextPosition(int[] currentPosition, Direction direction) {
        switch (direction) {
            case RIGHT -> {
                return new int[]{currentPosition[0], currentPosition[1] + 1};
            }
            case DOWN -> {
                return new int[]{currentPosition[0] + 1, currentPosition[1]};
            }
            case LEFT -> {
                return new int[]{currentPosition[0], currentPosition[1] - 1};
            }
            case UP -> {
                return new int[]{currentPosition[0] - 1, currentPosition[1]};
            }
        }
        return null;
    }

    private Direction nextDirection(Direction direction) {
        return Map.of(Direction.RIGHT, Direction.DOWN, Direction.DOWN, Direction.LEFT, Direction.LEFT, Direction.UP, Direction.UP, Direction.RIGHT).get(direction);
    }

    private boolean isValidPosition(int[] position) {
        return (position[0] >= 0 && position[1] >= 0 && position[0] < matrix.size() && position[1] < matrix.get(0).size() && matrix.get(position[0]).get(position[1]) != null);
    }

    public String spiralPrint() {
        int remaining = matrix.size() * matrix.get(0).size();
        Direction currentDirection = Direction.RIGHT;
        int[] currentPosition = new int[]{0, 0};
        StringBuilder buf = new StringBuilder();
        while (remaining > 0) {
            assert currentPosition != null;
            Integer currentNumber = matrix.get(currentPosition[0]).get(currentPosition[1]);
            matrix.get(currentPosition[0]).set(currentPosition[1], null);
            buf.append(currentNumber.toString()).append(", ");
            int[] nextPosition = nextPosition(currentPosition, currentDirection);
            assert nextPosition != null;
            if (isValidPosition(nextPosition)) {
                currentPosition = nextPosition;
            } else {
                currentDirection = nextDirection(currentDirection);
                currentPosition = nextPosition(currentPosition, currentDirection);
            }
            remaining--;
        }
        buf.deleteCharAt(buf.lastIndexOf(","));
        return buf.toString();
    }

    public static void main(String[] args) {
        List<List<Integer>> matrix = List.of(
                List.of(1, 2, 3, 4, 5),
                List.of(6, 7, 8, 9, 10),
                List.of(11, 12, 13, 14, 15),
                List.of(16, 17, 18, 19, 20));
        System.out.println(new Grid(matrix).spiralPrint());
    }
}
