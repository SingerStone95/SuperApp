package com.singerstone.jojo;

/**
 * {@link 接雨水} 类似问题
 */
public class 盛最多水的容器 {
    public static void main(String[] args) {
        int[] array = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new 盛最多水的容器().maxArea(array));
    }


    /**
     * 双指针：矮的那一边向中间收拢才有机会获得最大值
     * @param height
     * @return
     */
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
