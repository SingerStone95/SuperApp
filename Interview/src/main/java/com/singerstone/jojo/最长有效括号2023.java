package com.singerstone.jojo;

public class 最长有效括号2023 {
    public static void main(String[] args) {
        System.out.println(new 最长有效括号2023().longestValid("()()()))"));

    }

    /**
     * dp[i] 定义为以i为结尾的括号的最长
     */
    int longestValid(String input) {
        int max = 0;
        int[] dp = new int[input.length()];
        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') {
                dp[i] = 0;
            } else { // 当前是 )
                if (input.charAt(i - 1) == '(') { // 前一个是 (
                    dp[i] = ((i - 2 >= 0) ? dp[i - 2] : 0) + 2;
                    max = Math.max(max, dp[i]);
                } else { // 前一个是 )
                    if ((i - dp[i - 1] - 1 >= 0) && input.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + 2 + ((i - dp[i - 1] - 2>=0)? dp[i - 2 - dp[i - 1]]:0);
                        max = Math.max(max, dp[i]);
                    } else {
                        dp[i] = 0;
                    }
                }

            }

        }
        return max;


    }
}
