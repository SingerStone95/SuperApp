package com.singerstone.jojo

class 二叉树的遍历 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
            val root = 二叉树构建.makeTreeRecursion(array, 0, array.size)


        }


        /**
         * 先序遍历
         */
        fun preOderTree(treeNode: TreeNode?) {
            var treeNode = treeNode
            var list = mutableListOf<TreeNode>()

            while (treeNode != null) {
                list.add(treeNode)
                treeNode = treeNode.left!!

            }
        }

    }
}