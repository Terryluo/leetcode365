/*
201. Bitwise AND of Numbers Range
Medium
Topics
Companies
Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.



Example 1:

Input: left = 5, right = 7
Output: 4
Example 2:

Input: left = 0, right = 0
Output: 0
Example 3:

Input: left = 1, right = 2147483647
Output: 0


Constraints:

0 <= left <= right <= 2^31 - 1
* */
public class BitwiseANDofNumbersRange_201 {
    /*
    5 -> 7: 101 & 110 & 111 = 100 & 111 = 100 = 4(binary)
    please remind that 1 & 2 = 0, 2 & 4 -> 010 & 100 = 0
    11 & 12 -> 1011 & 1100 -> 8
    12 & 13 & 14 -> 1100 & 1101 = 1100 & 1110 = 1100
    10 & 11 -> 1010 & 1011 = 1010 & 1100 -> 1000
    20 & 24 -> 10100
    * */
    public int rangeBitwiseAnd1(int left, int right) {
        if (left == 0) return 0;
        if (left == right) return left;
        int pre = 1;
        for (int i = 1; i < 32; i++) {
            int cur = pre * 2;
            if (cur <= left) {
                pre = cur;
                continue;
            } else if (cur <= right) {
                return 0;
            } else {
                if (left % 2 == 0) {
                    return left;
                }
                return pre;
            }
        }
        return pre;
    }
    // the best answer
    public int rangeBitwiseAnd(int left, int right) {
        int cnt = 0;
        while (left != right) {
            left >>= 1;
            right >>= 1;
            cnt++;
        }
        return (left << cnt);
    }
}
