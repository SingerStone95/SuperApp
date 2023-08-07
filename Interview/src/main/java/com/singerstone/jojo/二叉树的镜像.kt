package com.singerstone.jojo

import com.singerstone.jojo.二叉树构建.Companion.visitTree

class 二叉树的镜像 {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val array = arrayOf(0, 1, 2, 3, 4, 5, 6)
            val root = 二叉树构建.makeTreeRecursion(array, 0, array.size)
            visitTree(mirrorTree(root))
        }

        fun mirrorTree(root: TreeNode?): TreeNode? {
            if (root == null) {
                return null
            }
            val left = mirrorTree(root.left)
            val right = mirrorTree(root.right)
            root.left = right
            root.right = left
            return root
        }

    }
}