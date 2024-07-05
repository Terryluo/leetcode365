import java.util.*;
import java.util.stream.Collectors;

/*
46. Permutations
Solved
Medium
Topics
Companies
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]


Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
 */
public class Permutations_46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permutation(nums, 0, result);
        return result;
    }

    private void permutation(int[] nums, int index, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(Arrays.stream(nums)
                    .boxed()
                    .collect(Collectors.toCollection(ArrayList::new)));
            return;
        }
        Set<Integer> used = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (used.contains(nums[i])) {
                continue;
            }
            used.add(nums[i]);
            swap(nums, index, i);
            permutation(nums, index + 1, result);
            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        Permutations_46 permutations_46 = new Permutations_46();
        List<List<Integer>> result = permutations_46.permute(new int[]{1, 2, 3, 3});
        int count = 0;
        for (List<Integer> r : result) {
            for (Integer n : r) {
                System.out.printf("%d, ", n);
            }
            System.out.println();
            count++;
        }
        System.out.println("Total " + count + " permutations.");
    }
}
