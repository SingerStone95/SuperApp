package com.singerstone.jojo;

public class 乘积最大子数组 {

    public static void main(String[] args) {
        int[] array = {2, 3, -2, 4};

        System.out.println(new 乘积最大子数组().maxProduct(array));

    }

    //dp[i][0] 以 i 结尾的子数组乘积存的最大值 max(num[i],num[i]*dp[i-1][0],num[i]*dp[i-1][1])
    //dp[i][1] 以 i 结尾的子数组乘积存的最大值 min(num[i],num[i]*dp[i-1][0],num[i]*dp[i-1][1])

    public int maxProduct(int[] nums) {

        int dp[][] = new int[nums.length][2];
        int max = nums[0];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(Math.max(nums[i], nums[i] * dp[i - 1][0]), nums[i] * dp[i - 1][1]);
            dp[i][1] = Math.min(Math.min(nums[i], nums[i] * dp[i - 1][0]), nums[i] * dp[i - 1][1]);
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }

        return max;

    }
}
