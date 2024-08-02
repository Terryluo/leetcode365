/*
2134. Minimum Swaps to Group All 1's Together II
Medium

A swap is defined as taking two distinct positions in an array and swapping the values in them.

A circular array is defined as an array where we consider the first element and the last element to be adjacent.

Given a binary circular array nums, return the minimum number of swaps required to group all 1's present in the array together at any location.



Example 1:

Input: nums = [0,1,0,1,1,0,0]
Output: 1
Explanation: Here are a few of the ways to group all the 1's together:
[0,0,1,1,1,0,0] using 1 swap.
[0,1,1,1,0,0,0] using 1 swap.
[1,1,0,0,0,0,1] using 2 swaps (using the circular property of the array).
There is no way to group all 1's together with 0 swaps.
Thus, the minimum number of swaps required is 1.
Example 2:

Input: nums = [0,1,1,1,0,0,1,1,0]
Output: 2
Explanation: Here are a few of the ways to group all the 1's together:
[1,1,1,0,0,0,0,1,1] using 2 swaps (using the circular property of the array).
[1,1,1,1,1,0,0,0,0] using 2 swaps.
There is no way to group all 1's together with 0 or 1 swaps.
Thus, the minimum number of swaps required is 2.
Example 3:

Input: nums = [1,1,0,0,1]
Output: 0
Explanation: All the 1's are already grouped together due to the circular property of the array.
Thus, the minimum number of swaps required is 0.


Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
 */
public class MinimumSwapstoGroupAll1sTogetherII_2134 {
    public int minSwaps(int[] nums) {
        // Step 1: Count the total number of 1's
        int count1 = 0;
        for (int num : nums) {
            if (num == 1) {
                count1++;
            }
        }

        // Edge case: If there are no 1's or the entire array is 1's, no swaps are needed
        if (count1 == 0 || count1 == nums.length) {
            return 0;
        }

        // Step 2: Extend the array to handle the circular nature
        int[] extendedNums = new int[nums.length * 2];
        System.arraycopy(nums, 0, extendedNums, 0, nums.length);
        System.arraycopy(nums, 0, extendedNums, nums.length, nums.length);

        // Step 3: Initialize the sliding window
        int current1s = 0;
        for (int i = 0; i < count1; i++) {
            if (extendedNums[i] == 1) {
                current1s++;
            }
        }

        int max1sInWindow = current1s;

        // Step 4: Slide the window through the array
        for (int i = 1; i < nums.length; i++) {
            if (extendedNums[i - 1] == 1) {
                current1s--;
            }
            if (extendedNums[i + count1 - 1] == 1) {
                current1s++;
            }
            max1sInWindow = Math.max(max1sInWindow, current1s);
        }

        // Step 5: Calculate the minimum swaps
        int minSwaps = count1 - max1sInWindow;

        return minSwaps;
    }

    public static void main(String[] args) {
        MinimumSwapstoGroupAll1sTogetherII_2134 mstga1t = new MinimumSwapstoGroupAll1sTogetherII_2134();
        int[] nums = new int[] {1,1,1,0,0,1,0,1,1,0};
        System.out.println(mstga1t.minSwaps(nums));
    }
}
