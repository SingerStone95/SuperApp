package com.singerstone.jojo;

public class 盛最多水的容器 {
    public static void main(String[] args) {
        int[] array = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new 盛最多水的容器().maxArea(array));
    }


    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int result = 0;
        while (left < right) {
            result = Math.max(result, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }

        }

        return result;
    }
}
