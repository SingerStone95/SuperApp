package com.singerstone.jojo;

public class 有序数组中的单一元素 {

    public static void main(String[] args) {
        System.out.println(new 有序数组中的单一元素().singleNonDuplicate(new int[]{1, 1, 2, 3, 3}));
    }

    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid % 2 == 0) {
                if (nums[mid] == nums[mid + 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }

            } else {
                if ((mid - 1 >= 0) && nums[mid] == nums[mid - 1]) {
                    left = mid + 1;
                } else {
                    right = mid;

                }

            }

        }
        return nums[left];

    }


}
