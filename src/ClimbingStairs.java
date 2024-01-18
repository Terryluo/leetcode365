/*
70. Climbing Stairs
You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step


Constraints:

1 <= n <= 45
* */

public class ClimbingStairs {
    // will get Time Limit Exceed
    public int climbStairs1(int n) {
        // when n = 4, 1111, 121, 211, 112, 22 there are five ways to climb to the top
        // which matches to the fibonacci's number
        if (n < 3) {
            return n;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
    // will have n space
    public int climbStairs2(int n) {
        if (n < 3) return n;
        int[] result = new int[n];
        result[0] = 1;
        result[1] = 2;
        int i = 2;
        while (i < n) {
            result[i] = result[i - 1] + result[i - 2];
            i++;
        }
        return result[n - 1];
    }
    // best solution
    public int climbStairs(int n) {
        int a = 0, b = 1;
        while (n > 0) {
            int c = a + b;
            a = b;
            b = c;
            n--;
        }
        return a;
    }
}
