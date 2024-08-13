import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
40. Combination Sum II
Medium

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.



Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output:
[
[1,2,2],
[5]
]


Constraints:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
 */
public class Combination_Sum_II_40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> curr = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        dfs(candidates, target, 0, curr, result);
        return result;
    }
    private void dfs(int[] candidates, int remain, int s, List<Integer> curr, List<List<Integer>> result) {
        if (remain == 0) {
            result.add(new ArrayList<>(curr));
            return;
        }
        for (int i = s; i < candidates.length; i++) {
            if (i > s && candidates[i] == candidates[i - 1]) continue;
            if (candidates[i] > remain) break;
            curr.add(candidates[i]);
            dfs(candidates, remain - candidates[i], i + 1, curr, result);
            curr.remove(curr.size() - 1);
        }
    }
}
