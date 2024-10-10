import java.util.ArrayList;
import java.util.List;

/*
962. Maximum Width Ramp
Medium
Topics
Companies
A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i] <= nums[j]. The width of such a ramp is j - i.

Given an integer array nums, return the maximum width of a ramp in nums. If there is no ramp in nums, return 0.



Example 1:

Input: nums = [6,0,8,2,1,5]
Output: 4
Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] = 0 and nums[5] = 5.
Example 2:

Input: nums = [9,8,1,0,1,9,4,0,4,1]
Output: 7
Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] = 1 and nums[9] = 1.


Constraints:

2 <= nums.length <= 5 * 10^4
0 <= nums[i] <= 5 * 10^4
 */
public class Maximum_Width_Ramp_962 {
    public int maxWidthRamp1(int[] nums) {
        List<Integer> stack = new ArrayList<>();
        int max = 0;
        for (int j = 0; j < nums.length; j++) {
            if (stack.isEmpty() || nums[j] < nums[stack.get(stack.size() - 1)]) {
                stack.add(j);
                continue;
            }
            for (int i = stack.size() - 1; i >= 0; i--) {
                if (nums[stack.get(i)] <= nums[j]) {
                    max = Math.max(max, j - stack.get(i));
                } else {
                    break;
                }
            }
        }
        return max;
    }
    // best solution use binary search to solve this problem
    public int maxWidthRamp(int[] nums) {
        int left = 0; // smallest width
        int right = nums.length - 1; //largest width
        int max = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // if the mid(width) is possible in the array, then move left to mid + 1
            if (possible(nums, mid)) {
                max = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return max;
    }

    private boolean possible(int[] nums, int width) {
        int i = 0, j = width;
        int min = nums[i];
        while (j < nums.length) {
            if (min <= nums[j]) {
                return true;
            }
            j++;
            min = Math.min(min, nums[++i]);
        }
        return false;
    }

    public static void main(String[] args) {
        Maximum_Width_Ramp_962 mwr = new Maximum_Width_Ramp_962();
        int[] nums = {6,0,8,2,1,5};
        int result = mwr.maxWidthRamp(nums);
        System.out.println(result);
    }
}
