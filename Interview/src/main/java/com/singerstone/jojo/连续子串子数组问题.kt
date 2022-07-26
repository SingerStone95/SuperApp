package com.singerstone.jojo

class 连续子串子数组问题 {
    companion object {


        @JvmStatic
        fun main(args: Array<String>) {
            println(longestSubStr("a"))
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