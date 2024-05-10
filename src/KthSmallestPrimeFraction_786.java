import java.util.Comparator;
import java.util.PriorityQueue;

/*
786. K-th Smallest Prime Fraction
Medium
Topics
Companies
You are given a sorted integer array arr containing 1 and prime numbers, where all the integers of arr are unique. You are also given an integer k.

For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].

Return the kth smallest fraction considered. Return your answer as an array of integers of size 2, where answer[0] == arr[i] and answer[1] == arr[j].



Example 1:

Input: arr = [1,2,3,5], k = 3
Output: [2,5]
Explanation: The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
The third fraction is 2/5.
Example 2:

Input: arr = [1,7], k = 1
Output: [1,7]


Constraints:

2 <= arr.length <= 1000
1 <= arr[i] <= 3 * 10^4
arr[0] == 1
arr[i] is a prime number for i > 0.
All the numbers of arr are unique and sorted in strictly increasing order.
1 <= k <= arr.length * (arr.length - 1) / 2


Follow up: Can you solve the problem with better than O(n2) complexity?
* */
public class KthSmallestPrimeFraction_786 {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(k, new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                double d1 = (double)arr[p1.i] / arr[p1.j];
                double d2 = (double)arr[p2.i] / arr[p2.j];
                if (d2 > d1) {
                    return 1;
                } else if (d2 == d1) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        for (int j = 1; j < arr.length; j++) {
            for (int i = 0; i < j; i++) {
                double fraction = (double) arr[i] / arr[j];
                if (maxHeap.size() < k) {
                    maxHeap.add(new Pair(i, j));
                    continue;
                }
                double maxHeapTop = (double) arr[maxHeap.peek().i] / arr[maxHeap.peek().j];
                if (maxHeap.size() == k && fraction < maxHeapTop) {
                    maxHeap.poll();
                    maxHeap.add(new Pair(i, j));
                }
            }
        }
        return new int[]{arr[maxHeap.peek().i], arr[maxHeap.peek().j]};
    }
    class Pair {
        int i;
        int j;
        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) {
        KthSmallestPrimeFraction_786 kspf = new KthSmallestPrimeFraction_786();
        int[] result = kspf.kthSmallestPrimeFraction(new int[]{1,2,3,5}, 3);
        for (int num : result) {
            System.out.printf("%d, ", num);
        }
    }
}
