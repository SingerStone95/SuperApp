package com.singerstone.jojo;

import java.util.Arrays;

public class n个骰子的点数 {
    public static void main(String[] args) {
        System.out.println( Arrays.toString(new n个骰子的点数().dicesProbability(2)));

    }

    // n = 1 ， dp0-dp5 = 1/6
    // n = 2 ，可以摇到的点数是：2-12   个数为 6n-n+1 = 5n + 1
    public double[] dicesProbability(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        for (int i = 2; i <= n; i++) { // i 用来确定数组长度
            double[] tmp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) { // 这里的 dp[] 是比当前少一个骰子的 dp 数组
                for (int k = 0; k < 6; k++) {
                    tmp[j + k] += dp[j] * 1 / 6; // 这里这个 1/6 是新增加的这个骰子摇中 k 的概率 ，dp[k] 指的是前 n-1 个筛子摇中 j 的概率
                }
            }
            dp = tmp;
        }
        return dp;
    }

}
