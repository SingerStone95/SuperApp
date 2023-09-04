package com.singerstone.jojo;

public class 连续子数组的最大和 {

    public static void main(String[] args) {
        System.out.println(new 连续子数组的最大和().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

    public int maxSubArray(int[] nums) {
        int last_max = nums[0];
        int result = last_max;
        for (int i = 1; i < nums.length; i++) {
            last_max = Math.max(nums[i], last_max + nums[i]);
            result = Math.max(result, last_max);
        }
        return result;

    }

}
