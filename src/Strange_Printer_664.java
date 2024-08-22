import java.util.*;

/*
664. Strange Printer
Hard

There is a strange printer with the following two special properties:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any place and will cover the original existing characters.
Given a string s, return the minimum number of turns the printer needed to print it.



Example 1:

Input: s = "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".
Example 2:

Input: s = "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.


Constraints:

1 <= s.length <= 100
s consists of lowercase English letters.
 */
public class Strange_Printer_664 {
    public int strangePrinter(String s) {
        if (s == null || s.isEmpty()) return 0;

        // Remove consecutive duplicates
        List<Character> filteredChars = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (filteredChars.isEmpty() || c != filteredChars.get(filteredChars.size() - 1)) {
                filteredChars.add(c);
            }
        }

        int m = filteredChars.size();
        int[][] dp = new int[m][m];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        for (int i = 0; i < m; ++i) {
            dp[i][i] = 1;
        }

        // Precompute the next occurrence for each character
        Map<Character, Integer> lastSeen = new HashMap<>();
        int[] nextOccurrence = new int[m];
        Arrays.fill(nextOccurrence, -1);
        for (int i = m - 1; i >= 0; --i) {
            char c = filteredChars.get(i);
            if (lastSeen.containsKey(c)) {
                nextOccurrence[i] = lastSeen.get(c);
            }
            lastSeen.put(c, i);
        }

        // Fill the DP table
        for (int length = 2; length <= m; ++length) {
            for (int start = 0; start <= m - length; ++start) {
                int end = start + length - 1;
                // Initial case: print each character separately
                dp[start][end] = dp[start + 1][end] + 1;
                // Try to find a better solution by matching characters
                char currentChar = filteredChars.get(start);
                int nextPos = nextOccurrence[start];
                while (nextPos != -1 && nextPos <= end) {
                    dp[start][end] = Math.min(dp[start][end], dp[start][nextPos - 1] + (nextPos + 1 <= end ? dp[nextPos + 1][end] : 0));
                    nextPos = nextOccurrence[nextPos];
                }
            }
        }

        return dp[0][m - 1];
    }
}
