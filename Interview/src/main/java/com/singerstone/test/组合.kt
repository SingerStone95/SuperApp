package com.singerstone.test

class 组合 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = arrayOf(1, 5, 3, 4)

            println(isSearchTree(array, 0, array.size - 1))
        }

        private fun isSearchTree(array: Array<Int>, start: Int, end: Int): Boolean {
            if (start >= end) {
                return true
            }
            var rightStart = start
            // 找到右子树
            for (i in start until end) {
                if (array[i] <= array[end]) {
                    rightStart = i
                } else {
                    break
                }
            }
            // 判断右子树大于root
            for (i in (rightStart + 1) until end) {
                if (array[i] < array[end]) {
                    return false
                }
            }
            return isSearchTree(array, start, rightStart - 1) && isSearchTree(array, rightStart, end - 1)
        }
    }
}