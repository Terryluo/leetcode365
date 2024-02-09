import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
368. Largest Divisible Subset

Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.



Example 1:

Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.
Example 2:

Input: nums = [1,2,4,8]
Output: [1,2,4,8]


Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 2 * 10^9
All the integers in nums are unique.
* */
public class LargestDivisibleSubset_368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] size = new int[n];
        int[] preIndex = new int[n];
        Arrays.fill(preIndex, -1);
        int maxSize = 0, maxIndex = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && size[j] + 1 > size[i]) {
                    size[i] = size[j] + 1;
                    preIndex[i] = j;
                    if (size[i] > maxSize) {
                        maxSize = size[i];
                        maxIndex = i;
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        while(maxIndex != -1) {
            result.add(nums[maxIndex]);
            maxIndex = preIndex[maxIndex];
        }
        return result;
    }

    public static void main(String[] args) {
        LargestDivisibleSubset_368 lds = new LargestDivisibleSubset_368();
        int[] nums = {1, 2, 3};
        List<Integer> result = lds.largestDivisibleSubset(nums);
        for (Integer i : result) {
            System.out.printf("%d, ", i);
        }
    }
}
