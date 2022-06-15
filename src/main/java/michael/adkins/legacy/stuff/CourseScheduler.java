package michael.adkins.legacy.stuff;

import java.util.*;

/**
 * Course Scheduler that checks if there is a pre-requisite cycle
 */
public class CourseScheduler {

    /**
     * Space Complexity is O(n) which is the size of the graph.
     * Time Complexity can be up to O(n^2) but cache (hasVisited) reduces time complexity to O(n)
     * @param graph keeps all of the course info
     * @param courseNum key of the course in the graph
     * @param seen a list of course keys already seen
     * @param hasVisited cache which reduces time complexity, b/c you can repeat work otherwise
     * @return true if there is a cycle and false if not
     */
    public static boolean dfsCycleCheck(Map<Integer, List<Integer>> graph, Integer courseNum, Set<Integer> seen, Map<Integer, Boolean> hasVisited) {
        if(hasVisited.containsKey(courseNum)) {
            return hasVisited.get(courseNum);
        }
        if(seen.contains(courseNum)) {
            return true;
        }
        if(graph.containsKey(courseNum)) {
            seen.add(courseNum);
            boolean hasCycle = false;
            for (Integer neighborIndex : graph.get(courseNum)) {
                if(dfsCycleCheck(graph, neighborIndex, seen, hasVisited)) {
                    hasCycle = true;
                    break;
                }
            }
            seen.remove(courseNum);
            hasVisited.put(courseNum, hasCycle);
            return hasCycle;
        }
        return false;
    }

    public static boolean canFinish(int numberOfCourses, List<List<Integer>> prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (List<Integer> prerequisite : prerequisites) {
            if(graph.containsKey(prerequisite.get(0))) {
                graph.get(prerequisite.get(0)).add(prerequisite.get(1));
            } else {
                graph.put(prerequisite.get(0), Collections.singletonList(prerequisite.get(1)));
            }
        }
        for (int courseIndex = 0; courseIndex < numberOfCourses; courseIndex++) {
            if(dfsCycleCheck(graph, courseIndex, new HashSet<Integer>(), new HashMap<Integer, Boolean>())) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<List<Integer>> courseSchedule1 = List.of(List.of(1, 0));
        System.out.println(canFinish(2, courseSchedule1));
        List<List<Integer>> courseSchedule2 = List.of(List.of(1, 0), List.of(0, 1));
        System.out.println(canFinish(2, courseSchedule2));
    }
}
