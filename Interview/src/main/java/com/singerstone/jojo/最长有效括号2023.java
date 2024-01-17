package com.singerstone.jojo;

public class 最长有效括号2023 {
    public static void main(String[] args) {
        System.out.println(new 最长有效括号2023().longestValid("()()()))"));

    }

    /**
     * dp[i] 定义为以i为结尾的括号的最长
     *  ( : 直接是 dp[i] = 0;
     *  ) : 判断 ) 的前一个是 ( : dp[i]=dp[i-2]+2
     *    : 判断 ) 的前一个是 ) : 先判断 arr[i - dp[i-1] - 1] 是否为 ( ,是的话 dp=2+dp[i-1]+dp[i-2-dp[i-1]]
     */
    int longestValid(String input) {
        int max = 0;
        int[] dp = new int[input.length()];
        dp[0] = 0;
        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') { // 左括号不可能作为结尾，直接赋值0
                dp[i] = 0;
            } else { // 当前是 ) ，就要判断前面一个 ( 和 )
                if (input.charAt(i - 1) == '(') { // 前一个是 (
                    dp[i] = ((i - 2 >= 0) ? dp[i - 2] : 0) + 2;
                    max = Math.max(max, dp[i]);
                } else { // 前一个是 )
                    // 有和当前 ) 组成的 (
                    if ((i - dp[i - 1] - 1 >= 0) && input.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + 2 + ((i - dp[i - 1] - 2 >= 0) ? dp[i - 2 - dp[i - 1]] : 0);
                        max = Math.max(max, dp[i]);
                    } else {
                        // 当前 ( 无匹配
                        dp[i] = 0;
                    }
                }
            }
        }
        return max;


    }
}
