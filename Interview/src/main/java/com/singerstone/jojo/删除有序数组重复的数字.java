package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树的遍历.printArray;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/9/12 21:43
 * @see {@link }
 */
public class 删除有序数组重复的数字 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 2, 3, 3, 4};
        new 删除有序数组重复的数字().removeDuplicates(array);
        printArray(array);

    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int p = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[p]) {
                swap(nums, ++p, i);
            }
        }

        return p + 1;
    }

    private void swap(int[] nums, int i, int i1) {
        int tmp = nums[i];
        nums[i] = nums[i1];
        nums[i1] = tmp;
    }
}
