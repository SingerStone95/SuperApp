package com.singerstone.jojo;

public class 丑数 {

    public static void main(String[] args) {
        System.out.println(new 丑数().nthUglyNumber(10));
    }
// 由 2, 3 ,5 相乘得到的数称为丑数

    //输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
    // 由 2, 3 ,5 相乘得到的数称为丑数
    public int nthUglyNumber(int n) {
        int c2 = 1;
        int c3 = 1;
        int c5 = 1;
        int dp[] = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int p = dp[c2] * 2;
            int q = dp[c3] * 3;
            int r = dp[c5] * 5;
            dp[i] = Math.min(p, Math.min(q, r));
            // 这下面不能用 if else ，因为很有可能 p,q,r 相等 ，这种情况下都应该 +1
            if (dp[i] == p) {
                c2++;
            }
            if (dp[i] == q) {
                c3++;
            }

            if (dp[i] == r) {
                c5++;
            }
        }
        return dp[n];

    }
}
