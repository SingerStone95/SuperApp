package com.singerstone.jojo

class 字符串转换 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input = "    -21492222222222222asds"
            println(atoi2(input))
        }

        fun atoi(input: String): Int {
            var pi = 0
            while (input[pi] == ' ') {
                pi++
            }
            var flag = 0 // 0是正数
            if (input[pi] == '+' || input[pi] == '-') {
                if (input[pi] == '-') {
                    flag = 1
                }
                pi++
            }
            var result = 0
            while (pi < input.length && input[pi] in '0'..'9') {
                if (result > Int.MAX_VALUE / 10) {
                    return if (flag == 1) {
                        Int.MAX_VALUE
                    } else {
                        Int.MIN_VALUE
                    }
                }
                var cur = input[pi] - '0'
                if (result == Int.MAX_VALUE / 10) {
                    if (flag == 1 && cur > 8) {
                        return Int.MIN_VALUE
                    }
                    if (flag == 0 && cur > 7) {
                        return Int.MAX_VALUE
                    }
                }
                result = result * 10 + cur
                pi++
            }

            return result * if (flag == 0) 1 else -1
        }

        // 正则解法
        fun atoi2(input: String): Long {
            val trim = input.trim()
            val r1 = "^[+-]?[0-9]+".toRegex()
            return r1.find(trim)?.value?.toLong() ?: 0
        }
    }
}