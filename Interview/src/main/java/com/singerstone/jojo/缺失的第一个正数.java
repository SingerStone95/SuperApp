package com.singerstone.jojo;

public class 缺失的第一个正数 {
    public static void main(String[] args) {
        System.out.println(new 缺失的第一个正数().firstMissingPositive(new int[]{1,2,3}));
    }

    /**
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,0]
     * 输出：3
     * 示例 2：
     * <p>
     * 输入：nums = [3,4,-1,1]
     * 输出：2
     * 示例 3：
     * <p>
     * 输入：nums = [7,8,9,11,12]
     * 输出：1
     */
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // nums[i] != nums[nums[i] - 1] 这一步很重要，如果要交换的目标和当前一样，就不要再交换了
            while (nums[i] >= 1 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;

    }

    private void swap(int[] nums, int i, int i1) {
        int tmp = nums[i];
        nums[i] = nums[i1];
        nums[i1] = tmp;
    }


}
