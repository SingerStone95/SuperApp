package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树的遍历.printArray;

public class 轮转数组 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        new 轮转数组().rotate(nums, 3);
        printArray(nums);

    }

    /**
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * 示例 1:
     * <p>
     * 输入: nums = [1,2,3,4,5,6,7], k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 示例 2:
     * <p>
     * 输入：nums = [-1,-100,3,99], k = 2
     * 输出：[3,99,-1,-100]
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int count = 0;
        int start = 0;
        while (start < nums.length) { // 外层循环保证替换不死锁
            boolean isFirst = true;
            int curIndex = start;
            int nexValue = nums[curIndex];
            while (count < nums.length) {
                if (!isFirst && curIndex == start) {
                    break;
                }
                isFirst = false;
                int nextIndex = (curIndex + k) % nums.length;
                int tmp = nums[nextIndex];
                nums[nextIndex] = nexValue;
                nexValue = tmp;
                curIndex = nextIndex;
                count++;
            }
            start++;
        }
    }
}
