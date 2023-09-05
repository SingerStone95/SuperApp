package com.singerstone.jojo;

public class 最长回文子串 {

    public static void main(String[] args) {
        System.out.println(new 最长回文子串().longestPalindrome("aabba"));

    }


    // 非 dp 解法
    public String longestPalindrome(String s) {
        String result = "";

        for (int i = 0; i < s.length() - 1; i++) {
            String sub1 = subPalindrome(s, i, i + 1);
            String sub2 = subPalindrome(s, i, i);
            if (sub1.length() > sub2.length()) {
                if (result.length() < sub1.length()) {
                    result = sub1;
                }
            } else {
                if (result.length() < sub2.length()) {
                    result = sub2;
                }
            }
        }
        return result;

    }

    public String subPalindrome(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        return s.substring(start + 1, end - 1 + 1);
    }
}
