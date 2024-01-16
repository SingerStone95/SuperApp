package com.singerstone.jojo;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/9/13 21:25
 * @see {@link 盛最多水的容器}
 */
public class 接雨水 {

    public static void main(String[] args) {
        int[] array = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(new 接雨水().trap2(array));
    }

    //输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
    //输出：6
    // 笨办法：用两个数组保存左边的最大值 ，右边的最大值
    public int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }

        int[] leftMax = new int[height.length];
        int[] ritMax = new int[height.length];
        leftMax[0] = height[0];
        ritMax[height.length - 1] = height[height.length - 1];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            ritMax[i] = Math.max(ritMax[i + 1], height[i]);
        }

        int result = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(leftMax[i - 1], ritMax[i + 1]);
            if (min > height[i]) {
                result += min - height[i];
            }
        }

        return result;

    }

    // 双指针法
    // 从两头往中间，小的指针移动并与同边的最大值做差
    // [0,1,0,2,1,0,1,3,2,1,2,1]
    public int trap2(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int result = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                result += Math.max(0, leftMax - height[left]);
                leftMax = Math.max(leftMax, height[left]);
                left++;
            } else {
                result += Math.max(0, rightMax - height[right]);
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }

        }
        return result;


    }
}
