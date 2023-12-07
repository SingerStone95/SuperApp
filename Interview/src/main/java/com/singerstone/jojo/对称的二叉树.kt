package com.singerstone.jojo

class 对称的二叉树 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = arrayOf(0, 1, 1, 2, 3, 3, 2)
            val root = 二叉树构建.makeTreeRecursion(array, 0, array.size)
            二叉树构建.visitTree(root)
            print(isSymmetric(root))

        }

        fun isSymmetric(root: TreeNode?): Boolean {
            return isSymmetric(root, root)
        }

        fun isSymmetric(A: TreeNode?, B: TreeNode?): Boolean {
            if (A == null && B == null) {
                return true
            }
            if (A == null || B == null || A.value != B.value) {
                return false
            }
            return isSymmetric(A.left, B.right) && isSymmetric(A.right, B.left)
        }


    }
}