package com.singerstone.test

import com.singerstone.cas.Queue

class 二叉树构建 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
            val root = makeTreeRecursion(array, 0, array.size)
            visitTree(root)
        }

        /**
         * 递归创建二叉树，层序
         */
        private fun makeTreeRecursion(array: Array<Int>, nodeIndex: Int, len: Int): TreeNode? {
            if (nodeIndex >= len) {
                return null
            }
            val node = TreeNode()
            node.value = nodeIndex
            node.left = makeTreeRecursion(array, nodeIndex * 2 + 1, len)
            node.right = makeTreeRecursion(array, nodeIndex * 2 + 2, len)
            return node
        }


        /**
         * 层序遍历二叉树
         */
        @JvmStatic
         fun visitTree(root: TreeNode?) {
            if (root == null) {
                return
            }
            val queue = arrayListOf<TreeNode>()
            queue.add(root)

            while (queue.size > 0) {
                val node = queue.removeFirst()
                print(node.value)
                node.left?.let {
                    queue.add(it)
                }
                node.right?.let {
                    queue.add(it)
                }
            }

        }
    }


    class TreeNode {
        var value: Int = -1
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}