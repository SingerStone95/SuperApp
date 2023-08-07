package com.singerstone.jojo

class 回文问题 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input = "abccbcccc"
            println(palindromic2(input))

            println(isPalindromicNum(10011))

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

        // dp 版本 dp 含义：dp[i][j]代表当前的i-j区间是否是回文串
        //dp[i][j]=array[i+1]==arr[j-1]&&array[i]==array[j]
        fun palindromic2(source: String): String {
            if (source.length == 1) {
                return source
            }
            var array = Array(source.length) { IntArray(source.length) }
            var max = 1
            var result: String = source[0].toString()
            for (i in source.length - 1 downTo 0) {
                for (j in i until source.length) {
                    //123456
                    //66
                    //55 56
                    //44 45 46
                    //33 34 35 36
                    // 22 23 24 25 26
                    // 11 12 13 14 15 16
                    // 00 01 02 03 04 05 06
                    if (i == j) {
                        array[i][j] = 1
                        continue
                    }
                    if (source[i] == source[j] && (j - i <= 2 || array[i + 1][j - 1] == 1)) {
                        array[i][j] = 1
                        if (j - i + 1 > max) {
                            max = j - i + 1
                            result = source.substring(i, j + 1)
                        }
                    }

                }

            }
            return result
        }


        // 回文数字
        fun isPalindromicNum(num: Int): Boolean {
            var num_ = num
            var revers = 0
            while (num_ != 0) {
                revers = (revers * 10 + num_ % 10)
                num_ /= 10
            }
            return revers == num
        }
    }
}