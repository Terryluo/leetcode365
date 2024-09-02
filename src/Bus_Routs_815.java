import java.util.*;

/*
You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.

For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.

Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.



Example 1:

Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Example 2:

Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1




Constraints:

1 <= routes.length <= 500.
1 <= routes[i].length <= 10^5
All the values of routes[i] are unique.
sum(routes[i].length) <= 10^5
0 <= routes[i][j] < 10^6
0 <= source, target < 10^6
 */
public class Bus_Routs_815 {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (routes == null || routes.length == 0 || source < 0 || target < 0) {
            return -1;
        }
        if (source == target) {
            return 0; // early termination
        }
        Deque<Integer> queue = new LinkedList<>(); // use for BST
        Set<Integer> visited = new HashSet<>(); // record the buses that visited
        Set<Integer> goal = new HashSet<>(); // record the goal of bus
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                if (stop == source) {
                    queue.offer(i);
                    visited.add(i);
                }
                if (stop == target) {
                    goal.add(i);
                }
            }
        }

        // initial graph
        List<List<Integer>> busGraph = new ArrayList<>();
        for (int i = 0; i < routes.length; i++) {
            Arrays.sort(routes[i]);
            busGraph.add(new ArrayList<Integer>());
        }

        // construct graph
        for (int i = 0; i < routes.length; i++) {
            for (int j = i + 1; j < routes.length; j++) {
                if (hasCommon(routes[i], routes[j])) {
                    busGraph.get(i).add(j);
                    busGraph.get(j).add(i);
                }
            }
        }

        // BST
        int result = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int q : queue) {
                if (goal.contains(q)) {
                    return result;
                }
            }
            for (int i = 0; i < size; i++) {
                int currentBus = queue.poll();
                for (int bus : busGraph.get(currentBus)) {
                    if (visited.add(bus)) {
                        queue.offer(bus);
                    }
                }
            }
            result++;
        }
        return -1;
    }

    private boolean hasCommon(int[] a, int[] b) {
        int i = 0, j = 0; // pointer for two arrays
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                i++;
            } else if (a[i] > b[j]) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }
}
