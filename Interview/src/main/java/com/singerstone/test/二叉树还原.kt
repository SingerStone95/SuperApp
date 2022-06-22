package com.singerstone.test

import org.mockito.InOrder

class 二叉树还原 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inOrder = arrayOf(9, 3, 15, 20, 7)
            val postOrder = arrayOf(9, 15, 7, 20, 3)
            val node = buildTree(inOrder, postOrder, 0, inOrder.size - 1, 0, postOrder.size - 1)
            二叉树构建.visitTree(node)
        }


        private fun buildTree(inOrder: Array<Int>, postOrder: Array<Int>, il: Int, ir: Int, pl: Int, pr: Int): 二叉树构建.TreeNode? {
            if (il > ir || pl > pr) {
                return null
            }
            val rootV = postOrder[pr]
            val root = 二叉树构建.TreeNode()
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