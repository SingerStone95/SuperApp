package com.singerstone.jojo;

public class 寻找峰值 {

    public static void main(String[] args) {
        System.out.println(new 寻找峰值().findPeakElement(new int[]{1, 2, 3, 1}));
    }


    //输入：nums = [1,2,3,1]
    //输出：2
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;

    }
}
