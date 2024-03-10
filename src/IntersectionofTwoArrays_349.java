/*
349. Intersection of Two Arrays
Easy

Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.



Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.


Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000
* */
public class IntersectionofTwoArrays_349 {
    public int[] intersection1(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int[] record = new int[1001];
        int intersect = 0;
        if (len1 < len2) {
            for (int num : nums1) {
                record[num]++;
            }
            for (int num : nums2) {
                if (record[num] != 0 && record[num] != -1) {
                    record[num] = -1;
                    intersect++;
                }
            }
        } else {
            for (int num : nums2) {
                record[num]++;
            }
            for (int num : nums1) {
                if (record[num] != 0 && record[num] != -1) {
                    record[num] = -1;
                    intersect++;
                }
            }
        }
        int[] result = new int[intersect];
        int index = 0;
        for (int i = 0; i < record.length; i++) {
            if (record[i] == -1) {
                result[index] = i;
                index++;
            }
        }
        return result;
    }
    // better solution
    public int[] intersection(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length, intersect = 0;
        int[] record = new int[1001];
        for (int num : nums1) {
            record[num] = 1;
        }
        for (int num : nums2) {
            if (record[num] == 1) {
                record[num] = 2;
                intersect++;
            }
        }
        int[] result = new int[intersect];
        int index = 0;
        int[] nums = len1 < len2 ? nums1 : nums2;
        for (int num : nums) {
            if (record[num] == 2) {
                result[index++] = num;
                record[num] = 1;// if we already added the number, we should avoid duplication
            }
        }
        return result;
    }
}
