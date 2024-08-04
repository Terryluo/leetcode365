import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
1508. Range Sum of Sorted Subarray Sums
Medium

You are given the array nums consisting of n positive integers. You computed the sum of all non-empty continuous subarrays from the array and then sorted them in non-decreasing order, creating a new array of n * (n + 1) / 2 numbers.

Return the sum of the numbers from index left to index right (indexed from 1), inclusive, in the new array. Since the answer can be a huge number return it modulo 109 + 7.



Example 1:

Input: nums = [1,2,3,4], n = 4, left = 1, right = 5
Output: 13
Explanation: All subarray sums are 1, 3, 6, 10, 2, 5, 9, 3, 7, 4. After sorting them in non-decreasing order we have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]. The sum of the numbers from index le = 1 to ri = 5 is 1 + 2 + 3 + 3 + 4 = 13.
Example 2:

Input: nums = [1,2,3,4], n = 4, left = 3, right = 4
Output: 6
Explanation: The given array is the same as example 1. We have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]. The sum of the numbers from index le = 3 to ri = 4 is 3 + 3 = 6.
Example 3:

Input: nums = [1,2,3,4], n = 4, left = 1, right = 10
Output: 50


Constraints:

n == nums.length
1 <= nums.length <= 1000
1 <= nums[i] <= 100
1 <= left <= right <= n * (n + 1) / 2
 */
public class RangeSumofSortedSubarraySums_1508 {
    public long rangeSum(int[] nums, int n, int left, int right) {
        List<Integer> subarraySums = new ArrayList<>();

        // Generate all subarray sums
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            for (int j = i; j < n; j++) {
                currentSum += nums[j];
                subarraySums.add(currentSum);
            }
        }

        // Sort the subarray sums
        Collections.sort(subarraySums);

        // Calculate the range sum
        int result = 0;
        int mod = 1000000007;
        for (int k = left - 1; k < right; k++) {
            result = (result + subarraySums.get(k)) % mod;
        }

        return result;
    }

    public static void main(String[] args) {
        RangeSumofSortedSubarraySums_1508 rssss = new RangeSumofSortedSubarraySums_1508();
        int[] nums = new int[1000];
        int idx = 0;
        while (idx < 1000) {
            nums[idx] = 100;
            idx++;
        }
        long result = rssss.rangeSum(nums, 1000, 1, 500500);
        System.out.println(result);
    }
}
