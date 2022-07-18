package com.singerstone.jojo

import kotlin.math.max

class 打家劫舍123 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input = arrayOf(1, 2, 3, 4, 5)
            print(cashDp1(input))
        }

        // 打家劫舍 1
        // dp[i]=max(dp(i-1),arr[i]+dp[i-2])
        fun cashDp1(array: Array<Int>): Int {
            val dp = arrayOfNulls<Int>(array.size)
            dp[0] = array[0]
            dp[1] = max(array[0], array[1])
            for (i in 2 until array.size) {
                dp[i] = max(dp[i - 1]!!, array[i] + dp[i - 2]!!)
            }

            return dp[array.size - 1]!!


        }

        fun cashDp2(array: Array<Int>): Int {


            return 0
        }

        fun cashDp3(array: Array<Int>): Int {


            return 0
        }
    }
}