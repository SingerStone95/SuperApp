package com.singerstone.jojo

import kotlin.math.max
import kotlin.math.min

class 直方图最大矩形 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(getMaxRec(arrayOf(2, 1, 5, 6, 2, 3)))
        }

        fun getMaxRec(array: Array<Int>): Int {
            var result = 0
            var lastMaxHeight = 0
            var lastMaxRec = 0
            for (h in array) {
                // 递推公式错误
                result = max(h, min(h,lastMaxHeight)+lastMaxRec)

            }

            return result
        }
    }
}