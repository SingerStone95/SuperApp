package com.singerstone.jojo

import com.singerstone.jojo.二叉树构建.Companion.visitTree

class 二叉树还原1 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inOrder = arrayOf(9, 3, 15, 20, 7)
            val postOrder = arrayOf(9, 15, 7, 20, 3)
            val node = buildTree(inOrder, postOrder, 0, inOrder.size - 1, 0, postOrder.size - 1)
            visitTree(node)
        }


        /**
         * 根据中序和后序还原二叉树
         */
        private fun buildTree(inOrder: Array<Int>, postOrder: Array<Int>, il: Int, ir: Int, pl: Int, pr: Int): TreeNode? {
            if (il > ir || pl > pr) {
                return null
            }
            val rootV = postOrder[pr]
            val root = TreeNode()
            root.value = rootV
            var pi = il
            for (i in il..ir) {
                if (rootV == inOrder[i]) {
                    pi = i
                }
            }
            val leftLen = pi - il
            root.left = buildTree(inOrder, postOrder, il, pi - 1, pl, pl + leftLen - 1)
            root.right = buildTree(inOrder, postOrder, pi + 1, ir, pl + leftLen, pr - 1)
            return root
        }
    }


}