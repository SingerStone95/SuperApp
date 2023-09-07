package com.singerstone.jojo;

/**
 * 兄弟问题
 * {@link 最长公共子序列}
 */
public class 最长重复子数组 {
    //最长公共子串

    public static void main(String[] args) {
        int[] array1 = new int[]{1, 2, 3, 4, 5};
        int[] array2 = new int[]{4, 5};

        System.out.println(new 最长重复子数组().findLength(array1, array2));
    }


    // dp[i][j] 表示 nums1[i:] 和 nums2[j:] 的最长公共子串(必须要包含i,j)
    // dp[i][j]= (nums1[i]==nums2[j])?dp[i+1][j+1]+1:0
    public int findLength(int[] nums1, int[] nums2) {
        // +1 的目的是方便边界的赋值，边界是 0 的dp 可以这么做免去两个循环的赋值
        int dp[][] = new int[nums1.length + 1][nums2.length + 1];
        int max = 0;
        for (int i = nums1.length - 1; i >= 0; i--) {
            for (int j = nums2.length - 1; j >= 0; j--) {
                dp[i][j] = (nums1[i] == nums2[j]) ? dp[i + 1][j + 1] + 1 : 0;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;

    }
}
