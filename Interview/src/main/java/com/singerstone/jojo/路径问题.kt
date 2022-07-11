package com.singerstone.jojo

class 路径问题 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(uniquePath(3, 3))
            var trick = Array(3) { IntArray(3) }
            for (i in 0 until 3) {
                for (j in 0 until 3) {
                    trick[i][j] = 0
                }
            }
            trick[1][1] = 1
            println(uniquePath2(3, 3, trick))
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

        fun uniquePath2(m: Int, n: Int, trick: Array<IntArray>): Int {
            // 初始化dp
            var dp = Array(m + 1) { IntArray(n + 1) }
            dp[0][1] = 1 // 这个其实是为了dp[1][1] 的赋值
            // 多一行（列）的原因是第一行第一列不能简单的赋值为1 是需要计算的
            for (i in 1..m) {
                for (j in 1..n) {
                    if (trick[i - 1][j - 1] == 1) {
                        dp[i][j] = 0
                        continue
                    }
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                }
            }
            return dp[m][m]
        }

    }
}