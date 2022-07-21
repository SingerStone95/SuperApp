package com.singerstone.jojo

import com.singerstone.jojo.二叉树构建.Companion.visitTree

class 二叉搜索树 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = arrayOf(1, 2, 3, 4, 5, 6)

//            println(isSearchTree(array, 0, array.size - 1))


            inOderTree(buildSearchTree(array, 0, array.size - 1))

        }

        fun inOderTree(root: TreeNode?) {
            if (root == null) {
                return
            }
            inOderTree(root.left)
            print(root.value)
            inOderTree(root.right)
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

        /**
         * 构建二叉搜索树
         *
         *
         *
         */
        private fun buildSearchTree(array: Array<Int>, left: Int, right: Int): TreeNode? {
            if (left > right) {
                return null
            }
            val mid = left + (right - left) / 2
            val treeNode = TreeNode()
            treeNode.value = array[mid]
            treeNode.left = buildSearchTree(array, left, mid - 1)
            treeNode.right = buildSearchTree(array, mid + 1, right)
            return treeNode
        }
    }


}