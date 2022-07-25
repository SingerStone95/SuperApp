package com.singerstone.jojo

import kotlin.math.max

class 字符串子序列问题 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(longestSubStr("a"))
            println(longestConSubStr(arrayOf(100, 4, 200, 1, 3, 2, 5, 6)))

        }

        /**
         * 最长连续子序列 （序列可以不连续）
         */
        fun longestConSubStr(input: Array<Int>): Int {
            val set = hashSetOf<Int>()
            set.addAll(input)
            var result = 1
            for (i in input.indices) {
                if (!set.contains(input[i])) {
                    continue
                }
                set.remove(input[i])
                var left = i - 1
                var right = i + 1
                while (left >= 0) {
                    if (set.contains(left)) {
                        set.remove(input[left])
                        left--
                    } else {
                        break
                    }
                }
                while (right < input.size) {
                    if (set.contains(right)) {
                        set.remove(input[right])
                        right++
                    } else {
                        break
                    }
                }
                result = max(result, right - left - 1)

            }
            return result
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