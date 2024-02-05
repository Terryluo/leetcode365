import java.util.HashMap;
import java.util.Map;

/*
76. Minimum Window Substring

Given two strings s and t of lengths m and n respectively, return the minimum window
substring
 of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.



Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.


Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.
* */
public class MinimumWindowSubstring_76 {
    public String minWindow1(String s, String t) {
        int count = 0;
        int slow = 0, fast = 0, len = Integer.MAX_VALUE, head = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        while (fast < s.length()) {
            if (map.containsKey(s.charAt(fast))) {
                if (map.get(s.charAt(fast)) > 0) {
                    count++;
                }
                map.put(s.charAt(fast), map.get(s.charAt(fast)) - 1);
            }
            fast++;
            while (count == t.length()) {
                if (len > fast - slow) {
                    len = fast - slow;
                    head = slow;
                }
                if (map.containsKey(s.charAt(slow))) {
                    map.put(s.charAt(slow), map.get(s.charAt(slow)) + 1);
                    if (map.get(s.charAt(slow)) > 0) {
                        count--;
                    }
                }
                slow++;
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(head, head + len);
    }
    // best solution
    public String minWindow(String s, String t) {
        int[] map= new int[123];
        int left = 0, right = 0, count = t.length(), sub_len = Integer.MAX_VALUE, start = 0;

        for(char c: t.toCharArray())
            map[c]++;

        char[] ch = s.toCharArray();
        while(right < s.length())
        {
            if(map[ch[right++]]-- > 0) count--;

            while(count == 0)
            {
                if((right - left) < sub_len) sub_len = right - (start = left);
                if(map[ch[left++]]++ == 0) count++;
            }
        }
        return sub_len == Integer.MAX_VALUE? "" : s.substring(start, start + sub_len);
    }
}
