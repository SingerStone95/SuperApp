package com.singerstone.jojo;

public class 在排序数组中查找数字 {

    public static void main(String[] args) {
        int[] array = new int[]{5,7,7,8,8,10};
        System.out.println(new  在排序数组中查找数字().search(array,8));
    }


    //nums = [5,7,7,8,8,10], target = 8
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 先找左边界 再遍历
        while (left < right) {
            int mid = (left + right) / 2;
            if (target <= nums[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int result = 0;
        while (left < nums.length && nums[left] == target) {
            left++;
            result++;

        }
        return result;

    }
}
