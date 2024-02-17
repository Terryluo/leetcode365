import java.util.*;

/*
1481. Least Number of Unique Integers after K Removals
Medium

Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.



Example 1:

Input: arr = [5,5,4], k = 1
Output: 1
Explanation: Remove the single 4, only 5 is left.
Example 2:
Input: arr = [4,3,1,1,3,3,2], k = 3
Output: 2
Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.


Constraints:

1 <= arr.length <= 10^5
1 <= arr[i] <= 10^9
0 <= k <= arr.length
* */
public class LeastNumberofUniqueIntegersafterKRemovals_1481 {
    // slow solution
    public int findLeastNumOfUniqueInts1(int[] arr, int k) {
        Map<Integer, Integer> integerAndFrequency = new HashMap<>();
        for (int num : arr) {
            integerAndFrequency.put(num, integerAndFrequency.getOrDefault(num, 0) + 1);
        }
        Comparator<Integer> frequencyComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer num1, Integer num2) {
                int freqCompare = integerAndFrequency.get(num1).compareTo(integerAndFrequency.get(num2));
                if (freqCompare == 0) {
                    return num1.compareTo(num2); // If frequencies are equal, compare numbers
                }
                return freqCompare;
            }
        };
        int remove = 0;
        Map<Integer, Integer> sortedFrequency = new TreeMap<>(frequencyComparator);
        sortedFrequency.putAll(integerAndFrequency);
        for (Map.Entry<Integer, Integer> entry : sortedFrequency.entrySet()) {
            if (k == 0) {
                break;
            }
            int freq = entry.getValue();
            if (freq <= k) {
                k -= freq;
                remove++;
            } else {
                k = 0;
            }
        }
        return integerAndFrequency.size() - remove;
    }

    // Optimized solution, 10 times faster than above solution
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Arrays.sort(arr);
        int[] frequency = new int[arr.length + 1];
        int cnt = 0;
        int length = 1;
        for (int i = 0; i < arr.length; i++) {
            if (i + 1 < arr.length && arr[i] == arr[i + 1]) {
                // if it is not the last element and the next element(arr[i + 1]) is the same to arr[i]
                length++;
            } else {
                cnt++;
                frequency[length]++;
                length = 1;
            }
        }
        /*
        * in {2,4,1,8,3,5,1,3} since 1 freq for 2, 4, 5, 8; 2 freq for 1, 3;
        * our frequency array would be {0, 4, 2, 0, 0, 0, 0, 0}
        * cnt would be 6
        * */
        for(int i = 1; i < frequency.length; i++) {
            // if k / frequency < frequency[i] for example if k = 3, k / 1 = 3 < frequency[1] = 4; we can only remove 3 elements at max
            int canRemove = Math.min(k / i, frequency[i]);
            cnt -= canRemove;
            k -= canRemove * i;
        }
        return cnt;
    }

    public static void main(String[] args) {
        LeastNumberofUniqueIntegersafterKRemovals_1481 lnouiakr = new LeastNumberofUniqueIntegersafterKRemovals_1481();
        int[] arr = {2,4,1,8,3,5,1,3};
        int k = 3;
        int result = lnouiakr.findLeastNumOfUniqueInts(arr, k); // expected 3
        System.out.println(result);
    }
}
