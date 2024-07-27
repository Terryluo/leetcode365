import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
2976. Minimum Cost to Convert String I
Medium

You are given two 0-indexed strings source and target, both of length n and consisting of lowercase English letters. You are also given two 0-indexed character arrays original and changed, and an integer array cost, where cost[i] represents the cost of changing the character original[i] to the character changed[i].

You start with the string source. In one operation, you can pick a character x from the string and change it to the character y at a cost of z if there exists any index j such that cost[j] == z, original[j] == x, and changed[j] == y.

Return the minimum cost to convert the string source to the string target using any number of operations. If it is impossible to convert source to target, return -1.

Note that there may exist indices i, j such that original[j] == original[i] and changed[j] == changed[i].



Example 1:

Input: source = "abcd", target = "acbe", original = ["a","b","c","c","e","d"], changed = ["b","c","b","e","b","e"], cost = [2,5,5,1,2,20]
Output: 28
Explanation: To convert the string "abcd" to string "acbe":
- Change value at index 1 from 'b' to 'c' at a cost of 5.
- Change value at index 2 from 'c' to 'e' at a cost of 1.
- Change value at index 2 from 'e' to 'b' at a cost of 2.
- Change value at index 3 from 'd' to 'e' at a cost of 20.
The total cost incurred is 5 + 1 + 2 + 20 = 28.
It can be shown that this is the minimum possible cost.
Example 2:

Input: source = "aaaa", target = "bbbb", original = ["a","c"], changed = ["c","b"], cost = [1,2]
Output: 12
Explanation: To change the character 'a' to 'b' change the character 'a' to 'c' at a cost of 1, followed by changing the character 'c' to 'b' at a cost of 2, for a total cost of 1 + 2 = 3. To change all occurrences of 'a' to 'b', a total cost of 3 * 4 = 12 is incurred.
Example 3:

Input: source = "abcd", target = "abce", original = ["a"], changed = ["e"], cost = [10000]
Output: -1
Explanation: It is impossible to convert source to target because the value at index 3 cannot be changed from 'd' to 'e'.


Constraints:

1 <= source.length == target.length <= 105
source, target consist of lowercase English letters.
1 <= cost.length == original.length == changed.length <= 2000
original[i], changed[i] are lowercase English letters.
1 <= cost[i] <= 106
original[i] != changed[i]
 */
public class MinimumCosttoConvertStringI_2976 {
    int[][] saveResult = new int[26][26];
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // create a map costOfChange that related to changed and cost
        Map<Character, Map<Character, Integer>> costOfChange = new HashMap<>();
        for (int i = 0; i < original.length; i++) {
            if (!costOfChange.containsKey(original[i])) {
                Map<Character, Integer> map = new HashMap<>();
                map.put(changed[i], cost[i]);
                costOfChange.put(original[i], map);
            } else {
                costOfChange.get(original[i]).put(changed[i], cost[i]);
            }
        }
        // use dfs to find the cost for single character
        long finalCost = 0l;
        for (int j = 0; j < source.length(); j++) {
            char s = source.charAt(j);
            char t = target.charAt(j);
            if (s != t) {
                int min = findMin(costOfChange, s, t);
                if (min == -1) {
                    return -1;
                }
                finalCost += min;
            }
        }
        // get the final cost
        return finalCost;
    }

    private int findMin(Map<Character, Map<Character, Integer>> costOfChange, char s, char t) {
        if (saveResult[s - 'a'][t - 'a'] != 0) {
            // enhance the performance
            return saveResult[s - 'a'][t - 'a'];
        }
        int[] curr = new int[1];
        int[] result = new int[]{Integer.MAX_VALUE};
        Set<Character> set = new HashSet<>();
        dfs(costOfChange, s, t, set, curr, result);
        if (result[0] == Integer.MAX_VALUE) {
            // No path
            return -1;
        }
        saveResult[s - 'a'][t - 'a'] = result[0];
        return saveResult[s - 'a'][t - 'a'];
    }

    private void dfs(Map<Character, Map<Character, Integer>> costOfChange, char s, char t, Set<Character> set, int[] curr, int[] result) {
        if (s == t) {
            if (curr[0] < result[0]) {
                result[0] = curr[0];
            }
            return;
        }
        if (!costOfChange.containsKey(s) || set.contains(s)) {
            return;
        }
        for (Map.Entry<Character, Integer> entry : costOfChange.get(s).entrySet()) {
            curr[0] += entry.getValue();
            set.add(s);
            dfs(costOfChange, entry.getKey(), t, set, curr, result);
            curr[0] -= entry.getValue();
            set.remove(s);
        }
    }

    public static void main(String[] args) {
        MinimumCosttoConvertStringI_2976 mccs = new MinimumCosttoConvertStringI_2976();
        String source = "abcd";
        String target = "acbe";
        char[] original = mccs.stringToChar(new String[]{"a","b","c","c","e","d"});
        char[] changed = mccs.stringToChar(new String[]{"b","c","b","e","b","e"});
        int[] cost = new int[]{2,5,5,1,2,20};
        long result = mccs.minimumCost(source, target, original, changed, cost);
        System.out.println(result);
    }

    private char[] stringToChar(String[] s) {
        char[] c = new char[s.length];
        int index = 0;
        for (String sc : s) {
            c[index++] = sc.charAt(0);
        }
        return c;
    }
}
