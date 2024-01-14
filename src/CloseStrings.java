import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
1657. Determine if Two Strings Are Close
Two strings are considered close if you can attain one from the other using the following operations:

Operation 1: Swap any two existing characters.
For example, abcde -> aecdb
Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
You can use the operations on either string as many times as necessary.

Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.



Example 1:

Input: word1 = "abc", word2 = "bca"
Output: true
Explanation: You can attain word2 from word1 in 2 operations.
Apply Operation 1: "abc" -> "acb"
Apply Operation 1: "acb" -> "bca"
Example 2:

Input: word1 = "a", word2 = "aa"
Output: false
Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
Example 3:

Input: word1 = "cabbba", word2 = "abbccc"
Output: true
Explanation: You can attain word2 from word1 in 3 operations.
Apply Operation 1: "cabbba" -> "caabbb"
Apply Operation 2: "caabbb" -> "baaccc"
Apply Operation 2: "baaccc" -> "abbccc"


Constraints:

1 <= word1.length, word2.length <= 10^5
word1 and word2 contain only lowercase English letters.

Hint 1:
Operation 1 allows you to freely reorder the string.
Hint 2:
Operation 2 allows you to freely reassign the letters' frequencies.
* */
public class CloseStrings {
    public boolean closeStrings(String word1, String word2) {
        // I want word1 and word2 have the same characters
        // I want the frequencies of characters are the same
        if (word1.length() != word2.length()) return false;

        Map<Character, Integer> map1 = new HashMap<>(); // save characters and frequencies of word1
        Map<Character, Integer> map2 = new HashMap<>(); // save characters and frequencies of word2
        Set<Character> chars = new HashSet<>();
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < word1.length(); i++) {
            map1.put(word1.charAt(i), map1.getOrDefault(word1.charAt(i), 0) + 1);
            map2.put(word2.charAt(i), map2.getOrDefault(word2.charAt(i), 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry1 : map1.entrySet()) {
            chars.add(entry1.getKey());
            frequencies.put(entry1.getValue(), frequencies.getOrDefault(entry1.getValue(), 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry2 : map2.entrySet()) {
            char c = entry2.getKey();
            Integer freq = entry2.getValue();
            if (!chars.contains(c)) {
                return false;
            }
            chars.remove(c);
            if (!frequencies.containsKey(freq) || frequencies.get(freq) == 0) {
                return false;
            }
            frequencies.put(freq, frequencies.get(freq) - 1);
        }
        return true;
    }
    // use the int array instead of map to save the data would be more efficient
    public boolean closeStrings2(String word1, String word2) {
        if (word1.length() != word2.length()) return false;

        int[] count1 = new int[26];
        for (char c : word1.toCharArray()) {
            count1[c - 'a']++;
        }
        int[] count2 = new int[26];
        for (char c : word2.toCharArray()) {
            if (count1[c - 'a'] == 0) return false;
            count2[c - 'a']++;
        }
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int cnt1 : count1) {
            frequencies.put(cnt1, frequencies.getOrDefault(cnt1, 0) + 1);
        }

        for (int cnt2 : count2) {
            if (!frequencies.containsKey(cnt2) || frequencies.get(cnt2) == 0) return false;
            frequencies.put(cnt2, frequencies.get(cnt2) - 1);
        }
        return true;
    }
}
