/*
1653. Minimum Deletions to Make String Balanced
Medium

You are given a string s consisting only of characters 'a' and 'b'​​​​.

You can delete any number of characters in s to make s balanced. s is balanced if there is no pair of indices (i,j) such that i < j and s[i] = 'b' and s[j]= 'a'.

Return the minimum number of deletions needed to make s balanced.



Example 1:

Input: s = "aababbab"
Output: 2
Explanation: You can either:
Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").
Example 2:

Input: s = "bbaaaaabb"
Output: 2
Explanation: The only solution is to delete the first two characters.


Constraints:

1 <= s.length <= 105
s[i] is 'a' or 'b'​​.
 */
public class MinimumDeletionstoMakeStringBalanced_1653 {
    public int minimumDeletions(String s) {
        //find for every index the number of Bs before it and the number of A's after it
        /*
                a   a   b   a   b   b   a   b
           a    3   2   2   1   1   1   0   0
           b    0   0   0   1   1   2   3   3
         */
        // 'A's after current index
        int[] asaci = new int[s.length()];
        // 'B's before current index
        int[] bsbci = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == 'b') {
                bsbci[i] = bsbci[i - 1] + 1;
            } else {
                bsbci[i] = bsbci[i - 1];
            }
        }
        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i + 1) == 'a') {
                asaci[i] = asaci[i + 1] + 1;
            } else {
                asaci[i] = asaci[i + 1];
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            int total = asaci[i] + bsbci[i];
            if (total < result) {
                result = total;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MinimumDeletionstoMakeStringBalanced_1653 mdmsb = new MinimumDeletionstoMakeStringBalanced_1653();
        mdmsb.minimumDeletions("aababbab");
    }
}
