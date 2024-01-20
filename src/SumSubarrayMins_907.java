import java.util.ArrayDeque;
import java.util.Deque;

/*
907. Sum of Subarray Minimums
Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.



Example 1:

Input: arr = [3,1,2,4]
Output: 17
Explanation:
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.
Example 2:

Input: arr = [11,81,94,43,3]
Output: 444


Constraints:

1 <= arr.length <= 3 * 10^4
1 <= arr[i] <= 3 * 10^4
* */
public class SumSubarrayMins_907 {
    public int sumSubarrayMins(int[] arr) {
        /*
        3,1,2,4
        ple[1, 2, 1, 1]
        nle[1, 3, 2, 1]
        3 + 6 + 4 + 4
        */
        int modulo = 1000000007;
        // stack is the Monotonically Increasing Stack
        Deque<Integer> stack = new ArrayDeque<>();
        int[] ple = new int[arr.length];
        int[] nle = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            nle[i] = arr.length - i;
        }
        // ple is to store the number of elements to the left of the current element that are greater than the current element
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                nle[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            ple[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res = (int) ((res  + (long) arr[i] * ple[i] * nle[i]) % modulo);
        }
        return res;
    }
    public static void main(String[] args) {
        SumSubarrayMins_907 solution = new SumSubarrayMins_907();
        int[] nums = {3, 1, 2, 4};
        int[] nums2 = {11,81,94,43,3};
        int result = solution.sumSubarrayMins(nums2);
        System.out.println(result);
    }
}
