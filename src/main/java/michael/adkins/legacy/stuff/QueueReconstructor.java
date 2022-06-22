package michael.adkins.legacy.stuff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class QueueReconstructor {

	/**
	 * Time complexity is O(nlogn) + O(n) = O(nlog)
	 * @param input list of people
	 * @return queue of people sorted by height (tallest first) and then perceived position
	 */
	public static int[][] reconstructQueueByHeight(int[][] input) {
		//first sort by height where tallest people are first
		Comparator<int[]> heightComparator = Comparator.comparing((int[] person) -> person[0]);
		Comparator<int[]> perceivedPositionComparator = Comparator.comparing((int[] person) -> person[1]);
		Arrays.sort(input, heightComparator.reversed().thenComparing(perceivedPositionComparator));
		List<int[]> result = new ArrayList<>();
		// then take each person and insert them based on perceived position.
		// it pushes them back.  So, in position 0, [7, 0] goes first.
		// Later, [5, 0] pushes [7, 0] down second place.
		Arrays.stream(input).forEach((int[] person) -> result.add(person[1], person));
		return result.toArray(new int[result.size()][]);
	}

	public static void main(String[] args) {
		System.out.println(Arrays.deepToString(reconstructQueueByHeight(new int[][]{new int[]{7, 0}, new int[]{4, 4}, new int[]{7, 1}, new int[]{5, 0}, new int[]{6, 1}, new int[]{5, 2}})));
	}
}
