/*
300. Longest Increasing Subsequence
Medium

Given an integer array nums, return the length of the longest strictly increasing subsequence.

Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1


Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104


Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
*/

public class LengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // tbl[i]: the smallest ending value of all the ascending subsequences with length i
        int[] tbl = new int[nums.length + 1];
        // at the very beginning we have a length 1 ascending subsequence, while we're traversing the array, we will update existing tbl[i] and find new longer
        // ascending subsequence
        tbl[1] = nums[0];
        int result = 1;
        for (int i = 0; i < nums.length; i++) {
            //tbl is guaranteed to be in ascending order!!
            // from tbl, find the best(longest) ascending subsequence, which can concatenate nums[i] to form a new one
            // int index = largestSmaller(tbl, 1, result, nums[i]); this one is incorrect
            int index = find(tbl, 1, result, nums[i]);
            // there are two cases
            if (index == result) {
                // if the current number is bigger than tbl[result], which is the largest number in the subsequence, we append the number to the end
                result++;
                tbl[result] = nums[i];
            } else {
                // we find a better ascending subsequence, tbl[index + 1] must smaller than nums[i], so we update tbl[index + 1]
                tbl[index + 1] = nums[i];
            }
        }
        return result;
    }

    private int largestSmaller(int[] tbl, int left, int right, int target) {
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (tbl[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    private int find(int[] tbl, int left, int right, int target) {
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (tbl[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        for (int j = right; j >= left; j--) {
            if (tbl[j] < target) {
                return j;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        LengthOfLIS lol = new LengthOfLIS();
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        int result = lol.lengthOfLIS(nums);
        System.out.println(result);
    }
}
