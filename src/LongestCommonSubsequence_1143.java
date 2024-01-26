/*
1143. Longest Common Subsequence

Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.



Example 1:

Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.


Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
* */
public class LongestCommonSubsequence_1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        /*
        *   a    a   b   c   d   e
        * a 1    1   1   1   1   1
        * a 1    2   2   2   2   2
        * c 1    2   2   3   3   3
        * e 1    2   2   3   3   4
        * */
        //dp[i][j] represents the longest common subsequence of text1[0 ... i] & text2[0 ... j].
        int[][] dp = new int[text1.length()][text2.length()];
        //base case
        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        for (int i = 1; i < text1.length(); i++) {
            dp[i][0] = text1.charAt(i) == text2.charAt(0) ? 1 : dp[i - 1][0];
        }
        for (int j = 1; j < text2.length(); j++) {
            dp[0][j] = text1.charAt(0) == text2.charAt(j) ? 1 : dp[0][j - 1];
        }
        for (int m = 1; m < text1.length(); m++) {
            for (int n = 1; n < text2.length(); n++) {
                dp[m][n] = text1.charAt(m) == text2.charAt(n) ? dp[m - 1][n - 1] + 1 : Math.max(dp[m - 1][n], dp[m][n - 1]);
            }
        }
        return dp[text1.length() - 1][text2.length() - 1];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence_1143 lcs = new LongestCommonSubsequence_1143();
        String text1 = "aace", text2 = "aabcde";
        int result = lcs.longestCommonSubsequence(text1, text2);
        System.out.println(result);
    }
}
