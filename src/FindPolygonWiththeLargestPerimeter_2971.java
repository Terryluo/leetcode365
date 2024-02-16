/*
2971. Find Polygon With the Largest Perimeter
Medium

You are given an array of positive integers nums of length n.

A polygon is a closed plane figure that has at least 3 sides. The longest side of a polygon is smaller than the sum of its other sides.

Conversely, if you have k (k >= 3) positive real numbers a1, a2, a3, ..., ak where a1 <= a2 <= a3 <= ... <= ak and a1 + a2 + a3 + ... + ak-1 > ak, then there always exists a polygon with k sides whose lengths are a1, a2, a3, ..., ak.

The perimeter of a polygon is the sum of lengths of its sides.

Return the largest possible perimeter of a polygon whose sides can be formed from nums, or -1 if it is not possible to create a polygon.



Example 1:

Input: nums = [5,5,5]
Output: 15
Explanation: The only possible polygon that can be made from nums has 3 sides: 5, 5, and 5. The perimeter is 5 + 5 + 5 = 15.
Example 2:

Input: nums = [1,12,1,2,5,50,3]
Output: 12
Explanation: The polygon with the largest perimeter which can be made from nums has 5 sides: 1, 1, 2, 3, and 5. The perimeter is 1 + 1 + 2 + 3 + 5 = 12.
We cannot have a polygon with either 12 or 50 as the longest side because it is not possible to include 2 or more smaller sides that have a greater sum than either of them.
It can be shown that the largest possible perimeter is 12.
Example 3:

Input: nums = [5,5,50]
Output: -1
Explanation: There is no possible way to form a polygon from nums, as a polygon has at least 3 sides and 50 > 5 + 5.


Constraints:

3 <= n <= 105
1 <= nums[i] <= 109
* */
import java.util.Arrays;

public class FindPolygonWiththeLargestPerimeter_2971 {
    public long largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        long result = -1;
        long temp = nums[0] + nums[1];
        for (int i = 2; i< nums.length; i++) {
            if (temp > nums[i]) {
                // here it is a polygon, we update the result
                result = temp + nums[i];
            }
            // continue to try if there is a polygon. e.g. 1, 1, 2, 3 is a polygon
            temp += nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        FindPolygonWiththeLargestPerimeter_2971 fpwlp = new FindPolygonWiththeLargestPerimeter_2971();
        long result = fpwlp.largestPerimeter(new int[]{300005055,352368231,311935527,315829776,327065463,388851949,319541150,397875604,311309167,391897750,366860048,359976490,325522439,390648914,359891976,369105322,350430086,398592583,354559219,372400239,344759294,379931363,308829137,335032174,336962933,380797651,378305476,336617902,393487098,301391791,394314232,387440261,316040738,388074503,396614889,331609633,374723367,380418460,349845809,318514711,308782485,308291996,375362898,397542455,397628325,392446446,368662132,378781533,372327607,378737987});
        System.out.println(result);// result should be 17876942274
    }
}
