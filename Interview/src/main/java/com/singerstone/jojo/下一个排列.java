package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树的遍历.printArray;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/9/10 17:16
 * @see {@link }
 */
public class 下一个排列 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3};
        new 下一个排列().nextPermutation(array);
        printArray(array);
    }

    //输入：nums = [1,2,3]
    //输出：[1,3,2]

    // 特别的 : 3 2 1

    /**
     * 1. 从右往前找到第一个变小的数的index min
     * 2. 从右往左找到第一个比 min 大的数 max
     * 3. 交换 4. min+1~结尾 翻转。（不要包含 min）
     * 思想：左边找一较大的数和右边找一个较小的数交换，较大的书尽量靠右，交换完了之后 min+1~结尾一定是降序，将其翻转成升序
     */

    public void nextPermutation(int[] nums) {
        int min = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                min = i;
                break;
            }
        }
        if (min == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        int max = min;
        for (int i = nums.length - 1; i > min; i--) {
            if (nums[i] > nums[min]) {
                max = i;
                break;
            }
        }
        swap(nums, min, max);
        reverse(nums, min + 1, nums.length - 1);
    }

    private void swap(int[] nums, int min, int max) {
        int tmp = nums[min];
        nums[min] = nums[max];
        nums[max] = tmp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

}
