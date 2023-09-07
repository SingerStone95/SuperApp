package com.singerstone.jojo;

/**
 * 兄弟问题
 * {@link 最长重复子数组}
 */
public class 最长公共子序列 {
    public static void main(String[] args) {
        System.out.println(new 最长公共子序列().longestCommonSubsequence("abcde", "ace"));
    }

    //输入：text1 = "abcde", text2 = "ace"
    //输出：3
    //解释：最长公共子序列是 "ace" ，它的长度为 3 。
    // dp[i][j] 表示 text1[i:] 和 text2[j:] 子数组的最长子序列个数
    // dp[i][j]=text1[i]==text2[j]?dp[i+1][j+1]+1:Math.max(dp[i][j + 1], dp[i + 1][j]
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        int result = 0;
        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                dp[i][j] = text1.charAt(i) == text2.charAt(j) ? dp[i + 1][j + 1] + 1 : Math.max(dp[i][j + 1], dp[i + 1][j]);
                result = Math.max(result, dp[i][j]);
            }
        }
        return result;
    }

    //
}
