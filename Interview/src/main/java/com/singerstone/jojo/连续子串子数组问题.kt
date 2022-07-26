package com.singerstone.jojo

import kotlin.math.min

/**
 * 连续子串按之前的规律，通常都是DP问题 ，一般转化为以 array[i] 结尾的子串问题
 */
class 连续子串子数组问题 {
    companion object {


        @JvmStatic
        fun main(args: Array<String>) {
            println(longestSubStr("aaaabbbcfse"))
            println(shortSumSubArray(arrayOf(2, 3, 1, 2, 4, 3), 7))
        }

        /**
         * 最短子数组之和
         * 滑动窗口
         */
        fun shortSumSubArray(array: Array<Int>, sum: Int): Int {
            var result = Int.MAX_VALUE
            var tmp = 0
            var left = 0
            var right = 0
            while (right < array.size) {
                while (tmp < sum && right < array.size) {
                    tmp += array[right++]
                }
                while (tmp >= sum) {
                    result = min(result, right - left)
                    tmp -= array[left++]
                }
            }
            return if (result == array.size + 1) 0 else result
        }

        /**
         *   aabcacbd 最长不重复子串 子串必须要连续
         */
        fun longestSubStr(source: String): String {
            if (source.isNullOrEmpty()) {
                return source
            }
            var left = 0
            var map = mutableMapOf<Char, Int>()
            var max = 0
            var result = ""
            for ((right, i) in source.indices.withIndex()) {
                if (map[source[i]] != null) {
                    if (map[source[i]]!! >= left) {
                        // 上一次出现是在窗口之内
                        left = map[source[i]]!! + 1
                    }
                }
                map[source[i]] = i
                if (right - left + 1 > max) {
                    max = right - left + 1
                    result = source.substring(left, right + 1)
                }
            }

            return result
        }
    }
}