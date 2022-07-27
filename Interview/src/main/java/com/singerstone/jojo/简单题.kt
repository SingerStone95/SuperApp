package com.singerstone.jojo

class 简单题 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(containDup(arrayOf(1, 2, 2), 2))
            println(dumDNA("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"))
        }

        /**
         * 时候包含重复的值距离最大是K
         */
        private fun containDup(array: Array<Int>, k: Int): Boolean {
            val map = mutableMapOf<Int, Int>()
            for (i in array.indices) {
                if (map.containsKey(array[i]) && (i - map[array[i]]!!) <= k) {
                    return true
                } else {
                    map[array[i]] = i
                }
            }
            return false
        }

        /**
         * 重复DNA序列
         * DNA 长度为10
         * 只要包含一组重复的元素就加入到结果中
         */
        private fun dumDNA(input: String): ArrayList<String> {
            var result = arrayListOf<String>()
            var set = hashSetOf<String>()
            for (i in 0 until input.length - 9) {
                if (i + 9 < input.length) {
                    val tmp = input.substring(i, i + 10)
                    if (set.contains(tmp)) {
                        result.add(tmp)
                    } else {
                        set.add(tmp)
                    }
                }
            }
            return result
        }
    }
}