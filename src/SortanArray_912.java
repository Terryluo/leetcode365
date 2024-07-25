/*
912. Sort an Array
Medium

Given an array of integers nums, sort the array in ascending order and return it.

You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.



Example 1:

Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).
Example 2:

Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
Explanation: Note that the values of nums are not necessairly unique.


Constraints:

1 <= nums.length <= 5 * 10^4
-5 * 104 <= nums[i] <= 5 * 10^4
 */
public class SortanArray_912 {
    public int[] sortArray(int[] nums) {
        // use quick sort to solve this problem
        if (nums == null) {
            return nums;
        }
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }
    private void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        // define a pivot and use the pivot to partition the array
        int pivotPos = partition(array, left, right);
        // pivot is already at its position, when we do the recursive call on
        // the two partitions, piovt should not be included in any of them.
        quickSort(array, left, pivotPos - 1);
        quickSort(array, pivotPos + 1, right);
    }

    private int partition(int[] array, int left, int right) {
        int pivotIndex = pivotIndex(left, right);
        int pivot = array[pivotIndex];
        // swap the pivot element to the rightmost position first
        swap(array, pivotIndex, right);
        int leftBound = left;
        int rightBound = right - 1;
        while (leftBound <= rightBound) {
            if (array[leftBound] < pivot) {
                leftBound++;
            } else if (array[rightBound] >= pivot) {
                rightBound--;
            } else {
                swap(array, leftBound++, rightBound--);
            }
        }
        // swap back the pivot element
        swap(array, leftBound, right);
        return leftBound;
    }

    // this is one of the ways defining the pivot,
    // pick random elemnt in the range of [left, right]
    private int pivotIndex(int left, int right) {
        return left + (int) (Math.random() * (right - left + 1));
    }

    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
