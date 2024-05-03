/*
2441. Largest Positive Integer That Exists With Its Negative
Easy

Given an integer array nums that does not contain any zeros, find the largest positive integer k such that -k also exists in the array.

Return the positive integer k. If there is no such integer, return -1.



Example 1:

Input: nums = [-1,2,-3,3]
Output: 3
Explanation: 3 is the only valid k we can find in the array.
Example 2:

Input: nums = [-1,10,6,7,-7,1]
Output: 7
Explanation: Both 1 and 7 have their corresponding negative values in the array. 7 has a larger value.
Example 3:

Input: nums = [-10,8,6,7,-2,-3]
Output: -1
Explanation: There is no a single valid k, we return -1.


Constraints:

1 <= nums.length <= 1000
-1000 <= nums[i] <= 1000
nums[i] != 0
* */
public class LargestPositiveIntegerThatExistsWithItsNegative_2441 {
    public int findMaxK(int[] nums) {
        int[] record = new int[1001];
        int result = -1;
        for (int num : nums) {
            int idx = num > 0 ? num : -num;
            if (record[idx] != num) {
                record[idx] += num;
            }
            if (record[idx] == 0) {
                result = Math.max(result, idx);
            }
        }
        return result;
    }
}
