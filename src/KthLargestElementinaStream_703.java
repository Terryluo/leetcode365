import java.util.Comparator;
import java.util.PriorityQueue;

/*
703. Kth Largest Element in a Stream
Easy

Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Implement KthLargest class:

KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.


Example 1:

Input
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
Output
[null, 4, 5, 5, 8, 8]

Explanation
KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
kthLargest.add(3);   // return 4
kthLargest.add(5);   // return 5
kthLargest.add(10);  // return 5
kthLargest.add(9);   // return 8
kthLargest.add(4);   // return 8


Constraints:

1 <= k <= 104
0 <= nums.length <= 104
-104 <= nums[i] <= 104
-104 <= val <= 104
At most 104 calls will be made to add.
It is guaranteed that there will be at least k elements in the array when you search for the kth element.
 */
public class KthLargestElementinaStream_703 {
    PriorityQueue<Integer> minHeap = null;
    public KthLargestElementinaStream_703(int k, int[] nums) {
        minHeap = new PriorityQueue<>(k);
        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.add(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(num);
            }
        }
    }

    public int add(int val) {
        if (minHeap.isEmpty()) {
            minHeap.add(val);
        } else if (val > minHeap.peek()) {
            minHeap.poll();
            minHeap.add(val);
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,8,2};
        KthLargestElementinaStream_703 kles = new KthLargestElementinaStream_703(3, nums);
        System.out.println(kles.add(3));
        System.out.println(kles.add(5));
        System.out.println(kles.add(10));
        System.out.println(kles.add(9));
    }
}
