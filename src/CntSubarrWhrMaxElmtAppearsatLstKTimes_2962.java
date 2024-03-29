/*
2962. Count Subarrays Where Max Element Appears at Least K Times
Medium

You are given an integer array nums and a positive integer k.

Return the number of subarrays where the maximum element of nums appears at least k times in that subarray.

A subarray is a contiguous sequence of elements within an array.



Example 1:

Input: nums = [1,3,2,3,3], k = 2
Output: 6
Explanation: The subarrays that contain the element 3 at least 2 times are: [1,3,2,3], [1,3,2,3,3], [3,2,3], [3,2,3,3], [2,3,3] and [3,3].
Example 2:

Input: nums = [1,4,2,1], k = 3
Output: 0
Explanation: No subarray contains the element 4 at least 3 times.


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 106
1 <= k <= 105
* */
public class CntSubarrWhrMaxElmtAppearsatLstKTimes_2962 {
    public long countSubarrays1(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        long result = 0;
        for(int i = 0;i < nums.length; i++) {
            if(nums[i] > max){
                max = nums[i];
            }
        }
        int count = 0, start = 0, end = 0; // setup two pointers
        while(end < nums.length) {
            if(nums[end] == max){
                count++;
            }
            if(count >= k){
                while(count >= k){
                    result += nums.length - end;
                    if(nums[start] == max){
                        count--;
                    }
                    start++;
                }
            }
            end++;
        }
        return result;
    }
    // best result
    public long countSubarrays(int[] nums, int k) {
        long res = 0;
        int max = 0, count = 0, n = nums.length;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int l = 0;
        for (int r = 0; r < n; r++) {
            if (nums[r] == max)
                count++;
            while (count >= k) {
                res += n - r;
                if (nums[l] == max)
                    count--;
                l++;
            }
        }
        return res;
    }
}
