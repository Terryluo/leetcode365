/*
1544. Make The String Great
Easy

Given a string s of lower and upper case English letters.

A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:

0 <= i <= s.length - 2
s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
To make the string good, you can choose two adjacent characters that make the string bad and remove them. You can keep doing this until the string becomes good.

Return the string after making it good. The answer is guaranteed to be unique under the given constraints.

Notice that an empty string is also good.



Example 1:

Input: s = "leEeetcode"
Output: "leetcode"
Explanation: In the first step, either you choose i = 1 or i = 2, both will result "leEeetcode" to be reduced to "leetcode".
Example 2:

Input: s = "abBAcC"
Output: ""
Explanation: We have many possible scenarios, and all lead to the same answer. For example:
"abBAcC" --> "aAcC" --> "cC" --> ""
"abBAcC" --> "abBA" --> "aA" --> ""
Example 3:

Input: s = "s"
Output: "s"


Constraints:


1 <= s.length <= 100
s contains only lower and upper case English letters.
* */
public class MakeTheStringGreat_1544 {
    public String makeGood(String s) {
        /*
        l e E e e t c o d e
                f
            s
        */
        char[] c = s.toCharArray();
        int slow = 1;
        int fast = 1;
        while (fast < s.length()) {
            if (slow == 0 || Math.abs(c[fast] - c[slow - 1]) != 32) {
                swap(c, slow, fast);
                slow++;
            } else if (Math.abs(c[fast] - c[slow - 1]) == 32) {
                slow--;
            }
            fast++;
        }
        return String.copyValueOf(c, 0, slow);
    }
    private void swap(char[] c, int slow, int fast) {
        char temp = c[slow];
        c[slow] = c[fast];
        c[fast] = temp;
    }
}
