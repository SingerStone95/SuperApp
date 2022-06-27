package com.singerstone.jojo

class IP地址 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input = "25525511135"
            val result = mutableListOf<String>()
            val tmp = ""
            ipConfig(input, result, 4, tmp)
            print(result)
        }

        private fun ipConfig(input: String, result: MutableList<String>, seg: Int, tmp: String) {
            //seg : 表示进入了第 4-seg 段 ，0 开始计数
            if (seg == 0) { // 因为没有第4段 所以递归在这里结束
                if (input.isEmpty()) { // 恰好把数都用完
                    result.add(tmp)
                }
            } else {
                //每一段最多3位数
                for (i in 1..3) {
                    if (input.length >= i && isValid(input.substring(0, i))) {
                        ipConfig(input.substring(i), result, seg - 1, if (seg == 1) "$tmp${input.substring(0, i)}" else "$tmp${input.substring(0, i)}.")
                    }
                }
            }
        }

        private fun isValid(substring: String): Boolean {
            if (substring.isEmpty()) {
                return false
            }
            if (substring.length > 1 && substring[0] == '0') {
                return false
            }
            return substring.toInt() in 0..255
        }

    }
}