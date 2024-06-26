/*
1249. Minimum Remove to Make Valid Parentheses
Medium

Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.


Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"
Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.


Constraints:

1 <= s.length <= 10^5
s[i] is either'(' , ')', or lowercase English letter.
* */
public class MinimumRemovetoMakeValidParentheses_1249 {
    public String minRemoveToMakeValid(String s) {
        int open = 0, close = 0, flag = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '(') {
                open++;
                flag++;
            } else if(s.charAt(i) == ')' && flag > 0) {
                close++;
                flag--;
            }
        }
        int diff = Math.min(open, close);
        StringBuilder ans = new StringBuilder();
        open = diff;
        close = diff;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                if (open > 0) {
                    ans.append('(');
                    open--;
                }
                continue;
            }
            if (s.charAt(i) == ')') {
                if(close > 0 && close > open) {
                    ans.append(')');
                    close--;
                }
                continue;
            } else {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }
}
