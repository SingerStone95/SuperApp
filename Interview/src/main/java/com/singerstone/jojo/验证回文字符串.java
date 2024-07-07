package com.singerstone.jojo;

public class 验证回文字符串 {
    public static void main(String[] args) {
        System.out.println(new 验证回文字符串().isPalindrome("race a car"));

    }

    /**
     * 示例 1：
     * <p>
     * 输入: s = "A man, a plan, a canal: Panama"
     * 输出：true
     * 解释："amanaplanacanalpanama" 是回文串。
     * 示例 2：
     * <p>
     * 输入：s = "race a car"
     * 输出：false
     * 解释："raceacar" 不是回文串。
     * 示例 3：
     * <p>
     * 输入：s = " "
     * 输出：true
     * 解释：在移除非字母数字字符之后，s 是一个空字符串 "" 。
     * 由于空字符串正着反着读都一样，所以是回文串。
     */
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < s.length() && !isChar(s.charAt(left))) {
                left++;
            }
            while (right >= 0 && !isChar(s.charAt(right))) {
                right--;
            }
            if (left >= right) {
                return true;
            }
            if (!isSame(s.charAt(left), s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;

    }

    private boolean isChar(char a) {
        return (a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z') || (a >= '0' && a <= '9');
    }

    private boolean isSame(char a, char b) {
        return Character.toLowerCase(a) == Character.toLowerCase(b);
    }

}
