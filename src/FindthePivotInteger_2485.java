/*
2485. Find the Pivot Integer
Easy

Given a positive integer n, find the pivot integer x such that:

The sum of all elements between 1 and x inclusively equals the sum of all elements between x and n inclusively.
Return the pivot integer x. If no such integer exists, return -1. It is guaranteed that there will be at most one pivot index for the given input.



Example 1:

Input: n = 8
Output: 6
Explanation: 6 is the pivot integer since: 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21.
Example 2:

Input: n = 1
Output: 1
Explanation: 1 is the pivot integer since: 1 = 1.
Example 3:

Input: n = 4
Output: -1
Explanation: It can be proved that no such integer exist.


Constraints:

1 <= n <= 1000
* */
public class FindthePivotInteger_2485 {
    public int pivotInteger(int n) {
        // prefixsum 0 1 3 6 10 15 21 28 36
        if (n == 1) return 1;
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + i;
        }
        for (int pivot = n / 2; pivot < prefixSum.length; pivot++) {
            if (prefixSum[n] - prefixSum[pivot - 1] == prefixSum[pivot]) {
                return pivot;
            }
        }
        return -1;
    }
    // The fastest solution O(1) Time Complexity
    public int pivotInteger1(int n) {
        /*
         * pivot => (1 + pivot) * pivot / 2 = (pivot + n) * (n - pivot + 1) / 2
         * pivot + pivot^2 = pivot * n - pivot ^ 2 + n ^ 2 - pivot * n + pivot + n
         * 2 pivot^2 = n ^ 2 + n
         * pivot ^ 2 = n * (n + 1) / 2 = sum(n)
         * */
        int sum = n * (n + 1) / 2;
        int pivot = (int) Math.sqrt(sum);
        if (pivot * pivot == sum) return pivot;
        else return -1;
    }
}
