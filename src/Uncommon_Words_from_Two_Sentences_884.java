import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
884. Uncommon Words from Two Sentences
Easy

A sentence is a string of single-space separated words where each word consists only of lowercase letters.

A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.

Given two sentences s1 and s2, return a list of all the uncommon words. You may return the answer in any order.



Example 1:

Input: s1 = "this apple is sweet", s2 = "this apple is sour"

Output: ["sweet","sour"]

Explanation:

The word "sweet" appears only in s1, while the word "sour" appears only in s2.

Example 2:

Input: s1 = "apple apple", s2 = "banana"

Output: ["banana"]



Constraints:

1 <= s1.length, s2.length <= 200
s1 and s2 consist of lowercase English letters and spaces.
s1 and s2 do not have leading or trailing spaces.
All the words in s1 and s2 are separated by a single space.
 */
public class Uncommon_Words_from_Two_Sentences_884 {
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> map = new HashMap<>();
        String[] stringArray1 = s1.split(" ");
        String[] stringArray2 = s2.split(" ");
        int size = 0;
        for (String s : stringArray1) {
            if (!map.containsKey(s)) {
                map.put(s, 1);
                size++;
            } else {
                map.put(s, map.get(s) + 1);
                if (map.get(s) == 2) {
                    size--;
                }
            }
        }
        for (String s : stringArray2) {
            if (!map.containsKey(s)) {
                map.put(s, 1);
                size++;
            } else {
                map.put(s, map.get(s) + 1);
                if (map.get(s) == 2) {
                    size--;
                }
            }
        }
        String[] result = new String[size];
        int index = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                result[index++] = entry.getKey();
            }
        }
        return result;
    }
}