package com.singerstone.jojo;

import java.util.Arrays;

public class 最长递增子序列 {
    public static void main(String[] args) {
        int[] array = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(new 最长递增子序列().lengthOfLIS(array));

    }

    //输入：nums = [10,9,2,5,3,7,101,18]
    //输出：4
    //解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
    //dp[i] 表示以 nums[i] 结尾的最长连续子序列(必须要包含 nums[i])
    //dp[i]= for(j:0-i) nums[j]<nums[i]? max(dp[i],dp[j]+1):continue
    public int lengthOfLIS(int[] nums) {
        int dp[] = new int[nums.length];
        int result = 0;
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) { // 可以和 nums[i] 组成连续递增序列
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);

        }


        return result;

    }
}
