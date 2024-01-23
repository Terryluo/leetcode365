import java.util.ArrayList;
import java.util.List;

/*
1239. Maximum Length of a Concatenated String with Unique Characters

You are given an array of strings arr. A string s is formed by the concatenation of a subsequence of arr that has unique characters.

Return the maximum possible length of s.

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.



Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All the valid concatenations are:
- ""
- "un"
- "iq"
- "ue"
- "uniq" ("un" + "iq")
- "ique" ("iq" + "ue")
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible longest valid concatenations are "chaers" ("cha" + "ers") and "acters" ("act" + "ers").
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
Explanation: The only string in arr has all 26 characters.


Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lowercase English letters.
* */
public class MaxLength_1239 {
    public int maxLength(List<String> arr) {
        List<Integer> combination = new ArrayList<>();//to store len of combination
        combination.add(0);
        int result = 0;
        for (String s : arr) {
            int curr = 0, dup = 0; // curr is the 32 bit to save the combination, dup assume to be 0.
            for (char c : s.toCharArray()) {
                dup |= curr & 1 << (c - 'a');
                if (dup > 0) break;
                curr |= 1 << (c - 'a');
            }
            if (dup > 0) continue;
            for (int i = combination.size() - 1; i >= 0; i--) {
                if ((combination.get(i) & curr) > 0) continue; // we have duplicate character inside
                int newWord = combination.get(i) | curr;
                combination.add(newWord);
                result = Math.max(result, Integer.bitCount(newWord));
            }
        }
        return result;
    }
}
