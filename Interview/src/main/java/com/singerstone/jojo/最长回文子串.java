package com.singerstone.jojo;

public class 最长回文子串 {

    public static void main(String[] args) {
        System.out.println(new 最长回文子串().longestPalindrome2("aabba"));

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


    // dp 解法
    public String longestPalindrome2(String s) {
        if (s.length() == 1) {
            return s;
        }

        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        int max = 0;
        int start = -1;
        for (int L = 2; L <= s.length(); L++) { // 外层循环是长度（保证长度短的先计算）
            for (int i = 0; i < s.length(); i++) { // 内层循环是起点
                int j = i + L - 1;
                if (j >= s.length()) {
                    break;
                }
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i + 1 <= 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }

                } else {
                    dp[i][j] = false;
                }

                if (dp[i][j]) {
                    max = Math.max(max, j - i + 1);
                    start = i;
                }


            }

        }
        return s.substring(start, start + max);


    }
}
