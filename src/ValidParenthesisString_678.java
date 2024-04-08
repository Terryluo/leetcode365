/*
678. Valid Parenthesis String
Medium

Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".


Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "(*)"
Output: true
Example 3:

Input: s = "(*))"
Output: true


Constraints:

1 <= s.length <= 100
s[i] is '(', ')' or '*'.
* */
public class ValidParenthesisString_678 {
    public boolean checkValidString(String s) {
        int right = 0, left = 0, star = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                right++;
            } else if (s.charAt(i) == ')') {
                left++;
            } else if (s.charAt(i) == '*') {
                star++;
            }
            if (left > right + star) {
                return false;
            }
        }
        return star >= Math.abs(right - left);
    }

    public static void main(String[] args) {
        ValidParenthesisString_678 vps = new ValidParenthesisString_678();
        String s = "(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())";
        System.out.println(vps.checkValidString(s));
    }
}
