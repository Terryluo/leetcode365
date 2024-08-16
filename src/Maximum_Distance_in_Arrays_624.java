import java.util.List;

/*
624. Maximum Distance in Arrays
Medium
Topics
Companies
You are given m arrays, where each array is sorted in ascending order.

You can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a - b|.

Return the maximum distance.



Example 1:

Input: arrays = [[1,2,3],[4,5],[1,2,3]]
Output: 4
Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
Example 2:

Input: arrays = [[1],[1]]
Output: 0


Constraints:

m == arrays.length
2 <= m <= 10^5
1 <= arrays[i].length <= 500
-10^4 <= arrays[i][j] <= 10^4
arrays[i] is sorted in ascending order.
There will be at most 105 integers in all the arrays.
 */
public class Maximum_Distance_in_Arrays_624 {
    // brute force is looping all the combination of first element and last element, which is O(n^2) TC.
    public int maxDistance(List<List<Integer>> arrays) {
        // need to find the first lowest and second lowest, first biggest and second biggest value in the arrays
        int[][] lowest = new int[][]{{10001, 0}, {10001, 0}}; // value, index
        int[][] biggest = new int[][]{{-10001, 0}, {-10001, 0}};
        for (int i = 0; i < arrays.size(); i++) {
            List<Integer> array = arrays.get(i);
            int n = array.size();
            if (array.get(0) < lowest[0][0]) {
                lowest[1][0] = lowest[0][0];
                lowest[1][1] = lowest[0][1];
                lowest[0][0] = array.get(0);
                lowest[0][1] = i;
            } else if (array.get(0) < lowest[1][0]) {
                lowest[1][0] = array.get(0);
                lowest[1][1] = i;
            }
            if (array.get(n - 1) > biggest[0][0]) {
                biggest[1][0] = biggest[0][0];
                biggest[1][1] = biggest[0][1];
                biggest[0][0] = array.get(n - 1);
                biggest[0][1] = i;
            } else if (array.get(n - 1) > biggest[1][0]) {
                biggest[1][0] = array.get(n - 1);
                biggest[1][1] = i;
            }
        }
        if (lowest[0][1] != biggest[0][1]) {
            return Math.abs(biggest[0][0] - lowest[0][0]);
        } else {
            return Math.max(Math.abs(biggest[0][0] - lowest[1][0]), Math.abs(biggest[1][0] - lowest[0][0]));
        }
    }
}
