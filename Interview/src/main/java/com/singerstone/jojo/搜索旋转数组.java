package com.singerstone.jojo;

public class 搜索旋转数组 {

    public static void main(String[] args) {
        int[] array = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(new 搜索旋转数组().search(array, 2));

    }

    // [4,5,6,7,0,1,2]
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < nums[right]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;

                } else {
                    left = mid + 1;

                }

            }

        }
        return nums[left] == target ? left : -1;


    }

}
