import java.util.HashMap;
import java.util.Map;

/*
992. Subarrays with K Different Integers
Hard

Given an integer array nums and an integer k, return the number of good subarrays of nums.

A good array is an array where the number of different integers in that array is exactly k.

For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.



Example 1:

Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
Example 2:

Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].


Constraints:

1 <= nums.length <= 2 * 10^4
1 <= nums[i], k <= nums.length
*
* */
public class SubarrayswithKDifferentIntegers_992 {
    /*
Intuition:
First you may have feeling of using sliding window.
Then this idea get stuck in the middle.

This problem will be a very typical sliding window,
if it asks the number of subarrays with at most K distinct elements.

Just need one more step to reach the following equation:
exactly(K) = atMost(K) - atMost(K-1)


Explanation
Write/copy a helper function of sliding window,
to get the number of subarrays with at most K distinct elements.
Done.
Complexity:
Time O(N) for two passes.
Space O(K) at most K elements in the counter

Of course, you can merge 2 for loops into one, if you like.
    *
    * */
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }
    int atMostK(int[] A, int K) {
        int i = 0, res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int j = 0; j < A.length; ++j) {
            if (count.getOrDefault(A[j], 0) == 0) K--;
            count.put(A[j], count.getOrDefault(A[j], 0) + 1);
            while (K < 0) {
                count.put(A[i], count.get(A[i]) - 1);
                if (count.get(A[i]) == 0) K++;
                i++;
            }
            res += j - i + 1;
        }
        return res;
    }
    // Best solution
    public int subarraysWithKDistinct1(int[] nums, int k) {
        int[] numFreq = new int[nums.length + 1];
        int distinct = 0, start = 0, minEnd = 0, total = 0;
        while (distinct == k || minEnd < nums.length) {
            while (distinct < k && minEnd < nums.length)
                if (numFreq[nums[minEnd++]]++ == 0)
                    distinct++;
            int maxEnd = minEnd;
            while (maxEnd < nums.length && numFreq[nums[maxEnd]] > 0)
                maxEnd++;
            while (distinct == k) {
                if (numFreq[nums[start++]]-- == 1) {
                    distinct--;
                }
                total += (maxEnd - minEnd + 1);
            }
        }
        return total;
    }
}
