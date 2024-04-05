/*
205. Isomorphic Strings
Easy

Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.



Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true
* */
public class IsomorphicStrings_205 {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] record = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            if (cs == ct) {
                if (record[cs - 'a'] != 0) {
                    return false;
                } else {
                    record[cs - 'a'] = -26; // means here is equal
                }
            } else {
                int diff = (int) ct - cs;
                if (record[cs - 'a'] == 0 || record[cs - 'a'] == diff) {
                    // we never faced before
                    record[cs - 'a'] = diff;
                } else {
                    // we saw they are equal
                    return false;
                }
            }
        }
        return true;
    }
}
