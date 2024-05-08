import java.util.ArrayDeque;
import java.util.Deque;

/*
506. Relative Ranks
Easy

You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition. All the scores are guaranteed to be unique.

The athletes are placed based on their scores, where the 1st place athlete has the highest score, the 2nd place athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:

The 1st place athlete's rank is "Gold Medal".
The 2nd place athlete's rank is "Silver Medal".
The 3rd place athlete's rank is "Bronze Medal".
For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's rank is "x").
Return an array answer of size n where answer[i] is the rank of the ith athlete.



Example 1:

Input: score = [5,4,3,2,1]
Output: ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
Explanation: The placements are [1st, 2nd, 3rd, 4th, 5th].
Example 2:

Input: score = [10,3,8,9,4]
Output: ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
Explanation: The placements are [1st, 5th, 3rd, 2nd, 4th].



Constraints:

n == score.length
1 <= n <= 10^4
0 <= score[i] <= 10^6
All the values in score are unique.
* */
public class RelativeRanks_506 {
    public String[] findRelativeRanks(int[] score) {
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> buffer = new ArrayDeque<>();
        for (int i = 0; i < score.length; i++) {
            if (stack.isEmpty() || score[i] > score[stack.peekFirst()]) {
                stack.offerFirst(i);
            } else if (score[i] > score[stack.peekLast()]) {
                while (!stack.isEmpty() && score[i] > score[stack.peekLast()]) {
                    buffer.offerFirst(stack.pollLast());
                }
                stack.offerLast(i);
                while (!buffer.isEmpty()) {
                    stack.offerLast(buffer.pollFirst());
                }
            } else {
                stack.offerLast(i);
            }
        }
        String[] result = new String[score.length];
        int rank = 0;
        while (!stack.isEmpty()) {
            int idx = stack.pollFirst();
            rank++;
            if (rank == 1) {
                result[idx] = "Gold Medal";
            } else if (rank == 2) {
                result[idx] = "Silver Medal";
            } else if (rank == 3) {
                result[idx] = "Bronze Medal";
            } else {
                result[idx] = String.valueOf(rank);
            }
        }
        return result;
    }
}
