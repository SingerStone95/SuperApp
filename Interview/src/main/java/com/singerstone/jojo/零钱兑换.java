package com.singerstone.jojo;

public class 零钱兑换 {

    public static void main(String[] args) {
        int[] coins = new int[]{1,2};
        System.out.println(new 零钱兑换().coinChange(coins, 3));
    }

    //输入：coins = [1, 2, 5], amount = 11
    //输出：3
    //解释：11 = 5 + 5 + 1
    // dp[i] 表示 i 块钱的时候最小个数
    // dp[i] = min(dp[i-x])+1
    // dp[0]=0; // 推导而来
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            // 这个地方很巧妙 不能设置为 Integer.MAX_VALUE 因为不能找零的 dp 都会返回这个默认值
            // Integer.MAX_VALUE + 1 就会溢出变为负值  ，
            // 比如 dp[1] =  Integer.MAX_VALUE ，
            // dp[2] 就会返回 dp[1]+1 恰好变为负值被返回，结果就错了
            int min = amount + 1;
            for (int coin : coins) {
                int diff = i - coin;
                if (diff >= 0) {
                    min = Math.min(min, dp[diff] + 1);
                }

            }
            dp[i] = min;
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
