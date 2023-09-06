package com.singerstone.jojo;

public class 跳跃游戏12 {
    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 1, 1, 4};
        System.out.println(new 跳跃游戏12().jump(array));

    }


    // 是否可达
    //输入：nums = [2,3,1,1,4]
    //输出：true
    //解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。

    // 考虑边界 2,0,0,1
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            // 先判断再更新
            if (max < i) {
                return false;
            }
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }

    // 输入: nums = [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
// 前提是题目保证了最后

    // 考虑边界 2 1 1 1 1 1
    public int jump(int[] nums) {
        int border = 0;
        int max = 0;
        int step = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > border) {
                border = max;
                step++;
            }
            max = Math.max(max, i + nums[i]);
        }
        return step;
    }


}
