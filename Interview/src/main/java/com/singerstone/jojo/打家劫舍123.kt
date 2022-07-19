package com.singerstone.jojo

import kotlin.math.max

class 打家劫舍123 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input = arrayOf(1, 2, 3, 4, 5)
            println(cashDp1(input))
            println(cashDp2(input))
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

        /**
         * 打家劫舍2 ，村民围成一个环.
         * 思路是分别计算去掉第一个和最后一个的最大收益
         * 及时分别去掉 1 ，5 计算最大值 但是这个是为啥呢？
         */
        fun cashDp2(array: Array<Int>): Int {
            var arr1 = array.copyOfRange(0, array.size - 1)
            var arr2 = array.copyOfRange(1, array.size)
            return max(cashDp1(arr1), cashDp1(arr2))
        }

        /**
         * 现在村民是树形结构
         *     1
         *   2   3
         * 4  5 6 7
         *
         *
         */
        fun cashDp3(array: Array<Int>): Int {


            return 0
        }
    }
}