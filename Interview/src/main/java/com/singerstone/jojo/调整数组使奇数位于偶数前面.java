package com.singerstone.jojo;

public class 调整数组使奇数位于偶数前面 {
    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 5};
        System.out.println(new 调整数组使奇数位于偶数前面().exchange(array));
        for (int num : array) {
            System.out.print(num);
        }
        System.out.println();

    }

    public int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < (nums.length - 1) && nums[left] % 2 == 1) {
                left++;
            }
            while (right >= 0 && nums[right] % 2 == 0) {
                right--;
            }
            if (left < right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        return nums;
    }
}
