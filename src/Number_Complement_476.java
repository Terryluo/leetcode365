/*
476. Number Complement
Easy

The complement of an integer is the integer you get when you flip all the 0's to 1's and all the 1's to 0's in its binary representation.

For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
Given an integer num, return its complement.



Example 1:

Input: num = 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
Example 2:

Input: num = 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.


Constraints:

1 <= num < 2^31
 */
public class Number_Complement_476 {
    public int findComplement(int num) {
        char[] binary = Integer.toBinaryString(num).toCharArray();
        for (int i = 0; i < binary.length; i++) {
            binary[i] = binary[i] == '1' ? '0' : '1';
        }
        return Integer.parseUnsignedInt(new String(binary), 2);
    }
}
