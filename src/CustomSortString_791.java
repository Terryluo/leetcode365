/*
791. Custom Sort String
Medium

You are given two strings order and s. All the characters of order are unique and were sorted in some custom order previously.

Permute the characters of s so that they match the order that order was sorted. More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.

Return any permutation of s that satisfies this property.



Example 1:

Input:  order = "cba", s = "abcd"

Output:  "cbad"

Explanation: "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".

Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.

Example 2:

Input:  order = "bcafg", s = "abcd"

Output:  "bcad"

Explanation: The characters "b", "c", and "a" from order dictate the order for the characters in s. The character "d" in s does not appear in order, so its position is flexible.

Following the order of appearance in order, "b", "c", and "a" from s should be arranged as "b", "c", "a". "d" can be placed at any position since it's not in order. The output "bcad" correctly follows this rule. Other arrangements like "bacd" or "bcda" would also be valid, as long as "b", "c", "a" maintain their order.



Constraints:

1 <= order.length <= 26
1 <= s.length <= 200
order and s consist of lowercase English letters.
All the characters of order are unique.
* */
public class CustomSortString_791 {
    // The best answer is to use array to save the dictionary and appearance of the array. Then use StringBuilder/StringBuffer to build the string.git git
    public String customSortString(String order, String s) {
        int[] dictionary = new int[26];
        for (char c : order.toCharArray()) {
            dictionary[c - 'a']++;
        }
        int[] record = new int[26];
        StringBuilder remain = new StringBuilder();
        // if it is not in the dictionary, we append to the end of remain string, if it is in the dictionary, we save
        // it's number
        for (char c : s.toCharArray()) {
            if (dictionary[c - 'a'] == 0) {
                remain.append(c);
            } else {
                record[c - 'a']++;
            }
        }
        StringBuilder result = new StringBuilder();
        for (char c : order.toCharArray()) {
            if (record[c - 'a'] > 0) {
                int duplicate = record[c - 'a'];
                while (duplicate > 0) {
                    result.append(c);
                    duplicate--;
                }
            }
        }
        return result.append(remain.toString()).toString();
    }
}
