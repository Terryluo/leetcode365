/*
2966. Divide Array Into Arrays With Max Difference

You are given an integer array nums of size n and a positive integer k.

Divide the array into one or more arrays of size 3 satisfying the following conditions:

Each element of nums should be in exactly one array.
The difference between any two elements in one array is less than or equal to k.
Return a 2D array containing all the arrays. If it is impossible to satisfy the conditions, return an empty array. And if there are multiple answers, return any of them.



Example 1:

Input: nums = [1,3,4,8,7,9,3,5,1], k = 2
Output: [[1,1,3],[3,4,5],[7,8,9]]
Explanation: We can divide the array into the following arrays: [1,1,3], [3,4,5] and [7,8,9].
The difference between any two elements in each array is less than or equal to 2.
Note that the order of elements is not important.
Example 2:

Input: nums = [1,3,3,2,7,3], k = 3
Output: []
Explanation: It is not possible to divide the array satisfying all the conditions.


Constraints:

n == nums.length
1 <= n <= 10^5
n is a multiple of 3.
1 <= nums[i] <= 10^5
1 <= k <= 10^5
* */

import java.util.Arrays;

public class DivideArrayIntoArraysWithMaxDifference_2966 {
    public int[][] divideArray1(int[] nums, int k) {
        // Try to use a greedy approach.
        // Sort the array and try to group each 3 consecutive elements.
        int[][] result = new int[nums.length / 3][3];
        int resultIdx = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 3) {
            if (nums[i + 2] - nums[i] > k) {
                return new int[0][0];
            }
            result[resultIdx][0] = nums[i];
            result[resultIdx][1] = nums[i + 1];
            result[resultIdx][2] = nums[i + 2];
            resultIdx++;
        }
        return result;
    }

    public int[][] divideArray(int[] nums, int k) {
        // Use bucket sort instead of sort will save our time.

        int max = 0; // all nums are positive integer
        for (int num : nums) {
            max = Math.max(max, num);
        }
        // bucket sort
        int[] bucket = new int[max + 1]; // maxsize 10^5 + 1
        for (int num : nums) {
            bucket[num]++;
        }

        int[][] result = new int[nums.length / 3][3];
        for (int n = 0, x = 0, y = 0; n < bucket.length;) {
            if (bucket[n] == 0) {
               n++;
            }
            result[resultIdx][0] = nums[i];
            result[resultIdx][1] = nums[i + 1];
            result[resultIdx][2] = nums[i + 2];
            resultIdx++;
        }
        return result;
    }

    public static void main(String[] args) {
        DivideArrayIntoArraysWithMaxDifference_2966 daiawmd = new DivideArrayIntoArraysWithMaxDifference_2966();
        int[] nums = {1,3,4,8,7,9,3,5,1};
        int[][] result = daiawmd.divideArray(nums, 2);
        for (int x = 0; x < result.length; x++) {
            for (int y = 0; y < result[0].length; y++) {
                System.out.printf("%d, ", result[x][y]);;
            }
            System.out.println();
        }
    }
}
