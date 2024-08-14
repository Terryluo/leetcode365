import java.util.Arrays;
import java.util.PriorityQueue;

/*
719. Find K-th Smallest Pair Distance
Hard

The distance of a pair of integers a and b is defined as the absolute difference between a and b.

Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.



Example 1:

Input: nums = [1,3,1], k = 1
Output: 0
Explanation: Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
Example 2:

Input: nums = [1,1,1], k = 2
Output: 0
Example 3:

Input: nums = [1,6,1], k = 3
Output: 5


Constraints:

n == nums.length
2 <= n <= 10^4
0 <= nums[i] <= 10^6
1 <= k <= n * (n - 1) / 2
 */
public class Find_K_th_Smallest_Pair_Distance_719 {
    // this method will Time Limit Exceeded
    public int smallestDistancePair1(int[] nums, int k) {
        // create a maxHeap that collect the K-th smallest numbers
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, (a, b) -> b - a);
        // find out difference between paires
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int diff = Math.abs(nums[i] - nums[j]);
                if (maxHeap.size() < k) {
                    maxHeap.add(diff);
                } else {
                    if (diff < maxHeap.peek()) {
                        maxHeap.poll();
                        maxHeap.add(diff);
                    }
                }
            }
        }
        return maxHeap.peek();
        // Time complexity O(klogk * n^2)
    }

    public int smallestDistancePair(int[] nums, int k) {
        // use bucket sort to reduce time complexity
        int[] bucket = new int[1000001];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int diff = Math.abs(nums[i] - nums[j]);
                bucket[diff]++;
            }
        }
        int index = 0;
        while (k > 0) {
            if (bucket[index] == 0) {
                index++;
                continue;
            } else {
                k -= bucket[index];
                if (k <= 0) {
                    break;
                }
                index++;
            }
        }
        return bucket[index];
    }
    // best solution use binary search
    public int smallestDistancePair2(int[] nums, int k) {
        Arrays.sort(nums);
        int low = 0;
        int high = nums[nums.length-1]-nums[0];
        while(low<high){
            int mid = low +(high-low)/2;
            int count = countPairsWithSumMid(nums,mid);
            if(count>=k){
                high=mid;
            } else {
                low = mid+1;
            }
        }
        return low;
    }
    int countPairsWithSumMid(int[] nums, int sum){
        int count=0;
        int left=0;

        for(int right=1;right<nums.length;right++){
            while(nums[right]-nums[left]>sum){
                left++;
            }
            count+=right-left;
        }
        return count;

    }

}
