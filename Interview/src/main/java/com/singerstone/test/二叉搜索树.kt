package com.singerstone.test

class 二叉搜索树 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = arrayOf(1, 5, 5, 4)

            println(isSearchTree(array, 0, array.size - 1))
        }

        //是否是二叉搜索树
        private fun isSearchTree(array: Array<Int>, start: Int, end: Int): Boolean {
            if (start >= end) {
                return true
            }
            var leftEnd = start
            // 找到右子树
            for (i in start until end) {
                if (array[i] <= array[end]) {
                    leftEnd = i
                } else {
                    break
                }
            }
            // 判断右子树大于root
            for (i in (leftEnd + 1) until end) {
                if (array[i] <= array[end]) {
                    return false
                }
            }
            return isSearchTree(array, start, leftEnd - 1) && isSearchTree(array, leftEnd, end - 1)
        }
    }


}