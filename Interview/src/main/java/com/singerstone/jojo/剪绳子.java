package com.singerstone.jojo;

public class 剪绳子 {

    public static void main(String[] args) {
        System.out.println(new 剪绳子().cuttingRope2(10));

    }

    // dp 解法
    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int[] dp = new int[n + 1];
        for (int i = 4; i <= n; i++) {
            for (int j = 2; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }

        return dp[n];
    }

    /**
     *  这个方法主要是数学证明 证明是分成 3 的相等小段
     *  可以达到最大值
     */
    public int cuttingRope2(int n) {
        if (n <= 3) {
            return n - 1;
        }

        int c = n / 3;
        int y = n % 3;
        if (y == 0) {
            return (int) Math.pow(3, c);
        } else if (y == 1) {
            return (int) Math.pow(3, c - 1) * 2;
        } else {
            return (int) Math.pow(3, c) * 2;
        }
    }

}
