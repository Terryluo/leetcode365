/*
977. Squares of a Sorted Array
Easy

Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.



Example 1:

Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
Example 2:

Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]


Constraints:

1 <= nums.length <= 10^4
-10^4 <= nums[i] <= 10^4
nums is sorted in non-decreasing order.
* */
public class SquaresofaSortedArray_977 {
    public int[] sortedSquares(int[] nums) {
        //bucket sort the array, then create the square array
        int[] bucket = new int[10001];
        for (int num : nums) {
            bucket[Math.abs(num)]++;
        }
        int[] result = new int[nums.length];
        int idx = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 0) {
                continue;
            }
            while (bucket[i] > 0) {
                result[idx] = i * i;
                idx++;
                bucket[i]--;
            }
        }
        return result;
    }
}
// Time Complexity: O(2n);
// Space Complexity: O(2n);
