import java.util.*;

/*
264. Ugly Number II
Medium

An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

Given an integer n, return the nth ugly number.



Example 1:

Input: n = 10
Output: 12
Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
Example 2:

Input: n = 1
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.


Constraints:

1 <= n <= 1690
 */
public class Ugly_Number_II_264 {
    public int nthUglyNumber(int n) {
        int[] primes = {2, 3, 5};
        PriorityQueue<Long> uglyHeap = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>();

        uglyHeap.add(1L);
        visited.add(1L);

        long curr = 1L;
        for (int i = 0; i < n; i++) {
            curr = uglyHeap.poll();
            for (int prime : primes) {
                long new_ugly = curr * prime;
                if (!visited.contains(new_ugly)) {
                    uglyHeap.add(new_ugly);
                    visited.add(new_ugly);
                }
            }
        }
        return (int) curr;
    }

    private int[] ugly() {
        int[] uglyList = new int[1690];
        uglyList[0] = 1;
        int ugly, i2 = 0,i3 = 0, i5 = 0;

        for (int i = 1; i < 1690; i++) {
            ugly = Math.min(Math.min(uglyList[i2] * 2, uglyList[i3] * 3), uglyList[i5] * 5);
            uglyList[i] = ugly;

            if (ugly == uglyList[i2] * 2) ++i2;
            if (ugly == uglyList[i3] * 3) ++i3;
            if (ugly == uglyList[i5] * 5) ++i5;
        }
        return uglyList;
    }

    public static void main(String[] args) {
        Ugly_Number_II_264 un2 = new Ugly_Number_II_264();
        int result = un2.nthUglyNumber(1690);
        System.out.println(result);
        System.out.println(un2.ugly()[1689]);
    }
}
