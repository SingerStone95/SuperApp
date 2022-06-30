package com.singerstone.jojo

class 回文问题 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input = "abccbccccccc"
            println(palindromic(input))

        }

        // 经典回文子串非 dp 解法
        fun palindromic(source: String): String {
            if (source.length <= 1) {
                return source
            }

            var max = 1
            var result = source[0].toString()
            for (i in 0..source.length - 2) {
                val p1 = getPalindromic(source, i, i)
                val p2 = getPalindromic(source, i, i + 1)
                val tmp = if (p1.length > p2.length) p1 else p2
                if (tmp.length > max) {
                    max = tmp.length
                    result = tmp
                }
            }
            return result
        }

        fun getPalindromic(source: String, index11: Int, index22: Int): String {
            var index1 = index11
            var index2 = index22
            while (index1 >= 0 && index2 < source.length && source[index1] == source[index2]) {
                index1--
                index2++
            }
            // 先回退一个进度，然后substring
            return source.substring(++index1, --index2 + 1)
        }


        // 回文数字
        fun isPalindromicNum(num: Int): Boolean {


            return false
        }
    }
}