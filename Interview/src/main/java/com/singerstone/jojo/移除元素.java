package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树的遍历.printArray;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/9/12 21:45
 * @see {@link }
 */
public class 移除元素 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 2, 3, 3, 4};
        System.out.println(new 移除元素().removeElement(array,2));
        printArray(array);

    }


    public int removeElement(int[] nums, int val) {
        int p = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                continue;
            } else {
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
