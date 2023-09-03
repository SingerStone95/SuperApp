package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树的遍历.printArray;

public class 在排序数组中查找第一个和最后一个位置 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 1, 1, 2, 3, 3, 3, 3, 3, 4, 5, 5, 5, 5, 5, 5};

        printArray(new 在排序数组中查找第一个和最后一个位置().searchRange(array, 1));

    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int left = 0;
        int right = nums.length - 1;
        int start = -1;
        int end = -1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        start = nums[left] == target ? left : -1;
        left = 0;
        right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2 + 1;
            if (target >= nums[mid]) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        end = nums[left] == target ? left : -1;
        return new int[]{start, end};

    }

}
