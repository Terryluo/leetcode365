import java.util.*;

/*
1636. Sort Array by Increasing Frequency
Easy
Topics

Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.

Return the sorted array.



Example 1:

Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
Example 2:

Input: nums = [2,3,1,3,2]
Output: [1,3,3,2,2]
Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
Example 3:

Input: nums = [-1,1,-6,4,5,-6,1,4,1]
Output: [5,-1,4,4,-6,-6,1,1,1]


Constraints:

1 <= nums.length <= 100
-100 <= nums[i] <= 100
 */
public class SortArraybyIncreasingFrequency_1636 {
    public int[] frequencySort(int[] nums) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int number : nums) {
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
        }

        // Step 2: Create a TreeMap with a custom comparator to sort by frequency
        TreeMap<Integer, Integer> sortedByFrequency = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int freqCompare = frequencyMap.get(a).compareTo(frequencyMap.get(b));
                if (freqCompare != 0) {
                    return freqCompare;
                } else {
                    return b.compareTo(a); // If frequencies are equal, sort by the natural order of the keys
                }
            }
        });
        sortedByFrequency.putAll(frequencyMap);
        int[] result = new int[nums.length];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : sortedByFrequency.entrySet()) {
            int size = entry.getValue();
            int val = entry.getKey();
            while (size > 0) {
                result[idx++] = val;
                size--;
            }
        }
        return result;
    }
}
