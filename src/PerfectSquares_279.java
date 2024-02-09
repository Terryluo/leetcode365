/**
 279. Perfect Squares

 Given an integer n, return the least number of perfect square numbers that sum to n.

 A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.



 Example 1:

 Input: n = 12
 Output: 3
 Explanation: 12 = 4 + 4 + 4.
 Example 2:

 Input: n = 13
 Output: 2
 Explanation: 13 = 4 + 9.


 Constraints:

 1 <= n <= 10^4
 */
public class PerfectSquares_279 {
    public int numSquares(int n) {
        /*
        0   1   2   3   4   5   6   7   8
        0   1   2   3   1   2   3   4   2
        * */
        int[] result = new int[n + 1];
        for (int i = 1; i <= Math.sqrt(n); i++) {
            result[i * i] = 1;
        }
        for (int i = 2; i < n + 1; i++) {
            if (result[i] != 0) {
                continue;
            }
            result[i] = i;
            for (int j = 0; j <= i / 2; j++) {
                result[i] = Math.min(result[i], result[j] + result[i - j]);
            }
        }
        return result[n];
    }

    public static void main(String[] args) {
        PerfectSquares_279 ps = new PerfectSquares_279();
        int result = ps.numSquares(12);
        System.out.println(result);
    }
}
