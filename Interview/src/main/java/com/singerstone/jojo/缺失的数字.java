package com.singerstone.jojo;

public class 缺失的数字 {

    public static void main(String[] args) {
        int[] array = new int[]{0};
        System.out.println(new 缺失的数字().missingNumber(array));
    }

    // 1 2 3 5 6
    public int missingNumber(int[] nums) {
        // 容易漏掉的case
        if (nums[nums.length - 1] == nums.length - 1) {
            return nums.length;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;


    }
}
