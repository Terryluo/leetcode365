/*
*
*
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
        long result = fpwlp.largestPerimeter(new int[]{1,12,1,2,5,50,3});
        System.out.println(result);
    }
}
