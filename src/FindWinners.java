import java.util.*;

/*
2225. Find Players With Zero or One Losses
Solved
Medium
Topics
Companies
Hint
You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the player winneri defeated player loseri in a match.

Return a list answer of size 2 where:

answer[0] is a list of all players that have not lost any matches.
answer[1] is a list of all players that have lost exactly one match.
The values in the two lists should be returned in increasing order.

Note:

You should only consider the players that have played at least one match.
The testcases will be generated such that no two matches will have the same outcome.


Example 1:

Input: matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
Output: [[1,2,10],[4,5,7,8]]
Explanation:
Players 1, 2, and 10 have not lost any matches.
Players 4, 5, 7, and 8 each have lost one match.
Players 3, 6, and 9 each have lost two matches.
Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].
Example 2:

Input: matches = [[2,3],[1,3],[5,4],[6,4]]
Output: [[1,2,5,6],[]]
Explanation:
Players 1, 2, 5, and 6 have not lost any matches.
Players 3 and 4 each have lost two matches.
Thus, answer[0] = [1,2,5,6] and answer[1] = [].


Constraints:

1 <= matches.length <= 10^5
matches[i].length == 2
1 <= winneri, loseri <= 10^5
winneri != loseri
All matches[i] are unique.
* */
public class FindWinners {
    //Time Limit Exceeded solution
    public List<List<Integer>> findWinners1(int[][] matches) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> winners = new ArrayList<>();
        List<Integer> lostOneMatch = new ArrayList<>();
        Set<Integer> lostTwoMathches = new HashSet<>();
        for (int[] match : matches) {
            Integer winner = match[0];
            Integer loser = match[1];
            // process loser
            if (!lostTwoMathches.contains(loser)) {
                if (lostOneMatch.contains(loser)) {
                    lostOneMatch.remove(loser);
                    lostTwoMathches.add(loser);
                } else if (winners.contains(loser)) {
                    winners.remove(loser);
                    lostOneMatch.add(loser);
                } else {
                    lostOneMatch.add(loser);
                }
            }
            // process winner
            if (!lostTwoMathches.contains(winner)) {
                if (!winners.contains(winner) && !lostOneMatch.contains(winner)) {
                    winners.add(winner);
                }
            }
        }
        Collections.sort(winners);
        Collections.sort(lostOneMatch);
        result.add(winners);
        result.add(lostOneMatch);
        return result;
    }
    // will fail if input is [[1, 10000]]
    public List<List<Integer>> findWinners2(int[][] matches) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> winners = new ArrayList<>();
        List<Integer> lostOneMatch = new ArrayList<>();
        Map<Integer, Integer> loses = new HashMap<>();
        int max = -1;
        for (int[] match : matches) {
            max = Math.max(max, Math.max(match[0], match[1]));
            loses.put(match[1], loses.getOrDefault(match[1], 0) + 1);
        }
        for (int i = 1; i <= max; i++) {
            if (!loses.containsKey(i)) {
                winners.add(i);
            } else if (loses.get(i) == 1) {
                lostOneMatch.add(i);
            }
        }
        result.add(winners);
        result.add(lostOneMatch);
        return result;
    }

    public List<List<Integer>> findWinners(int[][] matches) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> winners = new ArrayList<>();
        List<Integer> lostOneMatch = new ArrayList<>();
        Map<Integer, Integer> loses = new TreeMap<>();
        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];
            if (!loses.containsKey(winner)) {
                loses.put(winner, 0);
            }
            if (!loses.containsKey(loser) || loses.get(loser) == 0) {
                loses.put(loser, -1);
            } else if (loses.get(loser) == -1) {
                loses.put(loser, -2);
            }
        }
        for (Map.Entry<Integer, Integer> entry : loses.entrySet()) {
            if (entry.getValue() == 0) {
                winners.add(entry.getKey());
            } else if (entry.getValue() == -1) {
                lostOneMatch.add(entry.getKey());
            }
        }
        result.add(winners);
        result.add(lostOneMatch);
        return result;
    }

    public static void main(String[] args) {
        FindWinners fw = new FindWinners();
        int[][] matches = new int[][]{{1, 3}, {2, 3},{3, 6},{5, 6},{5, 7}, {4, 5}, {4, 8}, {4, 9}, {10, 4}, {10, 9}};
        //[[1,2],[2,1]]
        List<List<Integer>> result = fw.findWinners(matches);
        for (Integer i : result.get(0)) {
            System.out.printf("%d,", i);
        }
        System.out.println();
        for (Integer i : result.get(1)) {
            System.out.printf("%d,", i);
        }
    }
}
