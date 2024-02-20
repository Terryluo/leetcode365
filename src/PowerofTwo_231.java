/*
231. Power of Two
Easy
Topics
Companies
Given an integer n, return true if it is a power of two. Otherwise, return false.

An integer n is a power of two, if there exists an integer x such that n == 2x.



Example 1:

Input: n = 1
Output: true
Explanation: 2^0 = 1
Example 2:

Input: n = 16
Output: true
Explanation: 24 = 16
Example 3:

Input: n = 3
Output: false


Constraints:

-2^31 <= n <= 2^31 - 1
* */

public class PowerofTwo_231 {
    public boolean isPowerOfTwo1(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        for (int i = 1; i < 32; i++) {
            int power = 2 << i;
            if (power == n) {
                return true;
            }
        }
        return false;
    }
    // O(logn) solution
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        if (n % 2 == 1) return false;
        return isPowerOfTwo(n / 2);
    }

    public static void main(String[] args) {
        PowerofTwo_231 pot = new PowerofTwo_231();
        System.out.println(pot.isPowerOfTwo(16));
    }
}
