/*
238. Product of Array Except Self
Medium

Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.



Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]


Constraints:

2 <= nums.length <= 105
-30 <= nums[i] <= 30
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.


Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
* */
public class ProductofArrayExceptSelf_238 {
    public int[] productExceptSelf(int[] nums) {
        /*
        *  use prefix multiple and subfix multiple to solve the problem
        *  from front to end, calculate the multiplication for the array[index] from 0 to index - 1
        *  from end to front, calculate the multiplication for the array[index] from array.length - 1 to index + 1
        * */
        int[] result = new int[nums.length];
        int temp = 1;
        // from front to end
        for (int i = 0; i < nums.length; i++) {
            result[i] = temp;
            temp *= nums[i];
            /*
            *  This case, result[0] = 1, result[1] = 1 * nums[0], result[2] = 1 * nums[0] * nums[1]
            * */
        }
        temp = 1; //reset temp
        for (int j = nums.length - 1; j >= 0; j--) {
            result[j] *= temp; // here is multiplication
            temp *= result[j];
            /*
            *  This case, result[nums.length - 1] = 1 * nums[0] * ...* nums[nums.length - 2]
            *             result[nums.length - 2] = 1 * nums[0] * ...* nums[nums.length - 3] * nums[nums.length - 1]
            * */
        }
        return result;
    }
}
