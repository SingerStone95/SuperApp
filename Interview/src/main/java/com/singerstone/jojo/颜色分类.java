package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树的遍历.printArray;

public class 颜色分类 {

    public static void main(String[] args) {
        int[] array = new int[]{2, 0, 2, 1, 1, 0};
        new 颜色分类().sortColors2(array);
        printArray(array);



    }


    /**
     * 严格的一次遍历 双指针
     *
     */
    public void sortColors2(int[] nums) {
        int p0 = 0; // 第一个不是 p0 的位置
        int p2 = nums.length - 1; // 第一个不是 2 的位置
        for (int i = 0; i <= p2; i++) {
            while (i <= p2 && nums[i] == 2) {
                swap(nums, i, p2);
                p2--;
            }
            if (nums[i] == 0) {
                swap(nums, p0, i);
                p0++;
            }
        }
    }

    private void swap(int[] nums, int i, int i1) {
        int tmp = nums[i];
        nums[i] = nums[i1];
        nums[i1] = tmp;
    }
}
