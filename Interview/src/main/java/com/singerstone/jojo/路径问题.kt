package com.singerstone.jojo

class 路径问题 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            print(uniquePath(7, 3))
        }

        // leetcode 62
        // 怎么把二维数组转化为一维数组
        fun uniquePath(m: Int, n: Int): Int {
            var dp = Array(m) { IntArray(n) }
            //dp[i][j]=dp[i-1][j]+dp[i][j-1]
            for (i in 0 until m) {
                for (j in 0 until n) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                    }
                }
            }
            return dp[m - 1][n - 1]

        }

        fun uniquePath2(m: Int, n: Int): Int {

            return 0

        }

    }
}