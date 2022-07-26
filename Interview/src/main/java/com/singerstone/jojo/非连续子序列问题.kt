package com.singerstone.jojo

import kotlin.math.max

class 非连续子序列问题 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            println(longestConSubStr(arrayOf(100, 4, 200, 1, 3, 2, 5, 8)))

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
                var left = input[i]
                var right = input[i]
                while (true) {
                    left--
                    if (set.contains(left)) {
                        set.remove(left)
                    } else {
                        break
                    }
                }
                while (true) {
                    right++
                    if (set.contains(right)) {
                        set.remove(right)
                    } else {
                        break
                    }
                }
                result = max(result, right - left - 1)

            }
            return result
        }

    }
}