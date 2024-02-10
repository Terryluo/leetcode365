/*
647. Palindromic Substrings

Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.



Example 1:

Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:

Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
* */
public class PalindromicSubstrings_647 {
    public int countSubstrings(String s) {
        /*
        *   a   b   c
        *   0   1   2   3
        * */
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (isPalindrome(s, j, i)) {
                    result++;
                }
            }
        }
        return result;
    }

    private boolean isPalindrome(String s, int start, int end) {
        if (end == start) return true;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromicSubstrings_647 ps = new PalindromicSubstrings_647();
        int result = ps.countSubstrings("aaa");
        System.out.println(result);
    }
}
