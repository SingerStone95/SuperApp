package com.singerstone.jojo;

/**
 * {@link 把数字翻译成字符串} 互为镜像问题
 */
public class 解码方法 {

    public static void main(String[] args) {
        System.out.println(new 解码方法().numDecodings("226"));
    }

   // a-z == 1-26 从1开始计数

    //输入：s = "12"
    //输出：2
    //解释：它可以解码为 "AB"（1 2）或者 "L"（12）
    // 特殊的 06 无法映射 06 ！= 6 应该输出 0
    // 再考虑边界 60

    // 不同于 把数字翻译成字符串 ,这里要分3种情况讨论
    // nums[i] 合法， 能独立成字母也能与nums[i-1] 成字母 dp[i]=dp[i-1]+dp[i-2] 比如 12
    // nums[i] 合法，只能独立成字母 dp[i]=dp[i-1]  比如 106
    // nums[i] 不合法，只能联合nums[i-1]成字母  比如：10
    // 其余情况直接返回0就行了

    // 比简单版本难在 0 不映射 字符，就会多出 0 不合法， 10 合法这种case
    // 简单版本单个数字是必定合法的

    /**
     * dp[n] 的含义是前 n 个数的解码个数
     */
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = (s.charAt(0) > '0' && s.charAt(0) <= '9') ? 1 : 0; // 前 1 个数不一定合法
        if (dp[1] == 0) {
            return 0;
        }
        for (int i = 2; i <= s.length(); i++) { //这里的 i 代表第几个数
            char c = s.charAt(i - 1);
            char last = s.charAt(i - 2);
            int combo = (last - '0') * 10 + (c - '0');
            if (c > '0' && c <= '9') {
                // 当前合法，与前面的组合也合法
                if (combo >= 10 && combo <= 26) {
                    dp[i] = dp[i - 1] + dp[i - 2];
                } else {  // 当前合法，与前面的组合不合法
                    dp[i] = dp[i - 1];
                }
            } else if (combo >= 10 && combo <= 26) {
                // 当前不合法，与前面的组合合法
                dp[i] = dp[i - 2];

            } else {
                // 输入不合法
                return 0;
            }
        }
        return dp[s.length()];


    }
}
