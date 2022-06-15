package michael.adkins.legacy.stuff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class QueueReconstructor {

	public static int[][] reconstructQueueByHeight(int[][] input) {
		Comparator<int[]> heightComparator = Comparator.comparing(ints -> ints[0]);
		Comparator<int[]> perceivedPostionComparator = Comparator.comparing(ints -> ints[1]);
		Arrays.sort(input, heightComparator.reversed().thenComparing(perceivedPostionComparator));
		List<int[]> result = new ArrayList<>();
		Arrays.stream(input).forEach((int[] ints) -> {
			result.add(ints[1], ints);
		});
		return result.toArray(new int[result.size()][]);
	}

	public static void main(String[] args) {
		System.out.println(Arrays.deepToString(reconstructQueueByHeight(new int[][]{new int[]{7, 0}, new int[]{4, 4}, new int[]{7, 1}, new int[]{5, 0}, new int[]{6, 1}, new int[]{5, 2}})));
	}
}
