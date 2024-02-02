/*
1291. Sequential Digits

An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.



Example 1:

Input: low = 100, high = 300
Output: [123,234]
Example 2:

Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]


Constraints:

10 <= low <= high <= 10^9
* */
import java.util.ArrayList;
import java.util.List;

public class SequentialDigits_1291 {
    public List<Integer> sequentialDigits(int low, int high) {
        int window = 2;
        String digits = "123456789";
        List<Integer> result = new ArrayList<>();
        while (window < 10) {
            for (int i = 0; i + window <= digits.length(); i++) {
                int num = Integer.valueOf(digits.substring(i, i + window));
                if (num < low) {
                    continue;
                } else if (num > high) {
                    return result;
                } else {
                    result.add(num);
                }
            }
            window++;
        }
        return result;
    }

    public static void main(String[] args) {
        SequentialDigits_1291 sd = new SequentialDigits_1291();
        List<Integer> result = sd.sequentialDigits(100000000, 1000000000);
        for (int num : result) {
            System.out.printf("%d, ", num);
        }
    }
}
