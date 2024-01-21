public class HouseRobber_198 {
    public int rob(int[] nums) {
        // corner case: if we have only one house
        if (nums.length <= 1) {
            return nums[0];
        }
        // dp means the maximum amount of money we can rob up to current house
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        int result = Math.max(dp[0], dp[1]);
        for (int i = 2; i < nums.length; i++) {
            int currentMax = 0;
            for (int j = 0; j < i - 1; j++) {
                // find out the maximum money get from dp[0] to dp[i - 2]
                if (dp[j] > currentMax) {
                    currentMax = dp[j];
                }
            }
            dp[i] = nums[i] + currentMax;
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        HouseRobber_198 hr = new HouseRobber_198();
        int[] nums = {1, 2, 3, 1};
        System.out.println(hr.rob(nums));
    }
}
