package com.singerstone.jojo;

public class 判断子序列 {
    public static void main(String[] args) {

        System.out.println(new 判断子序列().isSubsequence("abc","ahbgdc"));
    }

    /**
     * 示例 1：
     * <p>
     * 输入：s = "abc", t = "ahbgdc"
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：s = "axc", t = "ahbgdc"
     * 输出：false
     */
    public boolean isSubsequence(String s, String t) {
        int left = 0;
        int right = 0;
        while (left < s.length() && right < t.length()) {
            if (s.charAt(left)==t.charAt(right)){
                left++;
            }
            right++;
        }
        return left == s.length();
    }
}
