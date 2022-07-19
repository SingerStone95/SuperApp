package com.singerstone.jojo

import com.singerstone.jojo.二叉树构建.Companion.makeTreeRecursion

class 二叉树的遍历 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
            preOrderTree(makeTreeRecursion(array, 0, array.size))
            println()

            preOrderTree2(makeTreeRecursion(array, 0, array.size))
            println()

            inOderTree(makeTreeRecursion(array, 0, array.size))
            println()
            postOrderTree(makeTreeRecursion(array, 0, array.size))
            println()
            postOrderTree2(makeTreeRecursion(array, 0, array.size))


        }

        /**
         *      1
         *   2      3
         * 4  5   6   7
         *
         * 进栈的顺序是先序遍历 1245367
         *
         */

        private fun preOrderTree(head: TreeNode?) {
            head ?: return
            var p = head
            val mutableList = mutableListOf<TreeNode>()
            while (mutableList.size > 0 || p != null) {
                if (p != null) {
                    print(p.value)
                    mutableList.add(p)
                    p = p.left
                } else {
                    val node = mutableList.removeAt(mutableList.size - 1)
                    p = node.right
                }
            }
        }

        /**
         * 进栈的顺序是先序遍历 1245367
         */
        private fun preOrderTree2(head: TreeNode?) {
            head ?: return
            val mutableList = mutableListOf<TreeNode>()
            mutableList.add(head)
            while (mutableList.size > 0) {
                val node = mutableList.removeAt(mutableList.size - 1)
                print(node.value)
                node.right?.let {
                    mutableList.add(it)
                }
                node.left?.let {
                    mutableList.add(it)
                }
            }
        }

        /**
         *      1
         *   2      3
         * 4  5   6   7
         *
         * 出栈的顺序是中序序遍历
         * 4251637
         */
        private fun inOderTree(head: TreeNode?) {
            head ?: return
            var p = head
            val mutableList = mutableListOf<TreeNode>()
            while (mutableList.size > 0 || p != null) {
                if (p != null) {
                    mutableList.add(p)
                    p = p.left
                } else {
                    val node = mutableList.removeAt(mutableList.size - 1)
                    print(node.value)
                    p = node.right
                }
            }

        }

        /**
         *      1
         *   2      3
         * 4  5   6   7
         *
         * 后序遍历 左右中 一般是由 中右左 倒序演变而来
         * 4526731
         *
         */
        fun postOrderTree(head: TreeNode?) {
            head ?: return
            var p = head
            val mutableList = mutableListOf<TreeNode>()
            var result = mutableListOf<Int>()
            while (mutableList.size > 0 || p != null) {
                if (p != null) {
                    mutableList.add(p)
                    result.add(0, p.value) // 倒序
                    p = p.right //这里是向右
                } else {
                    val node = mutableList.removeAt(mutableList.size - 1)
                    p = node.left // 向左
                }

            }
            print(result)
        }

        fun postOrderTree2(head: TreeNode?) {
            head ?: return
            val mutableList = mutableListOf<TreeNode>()
            mutableList.add(head)
            var result = mutableListOf<Int>()
            while (mutableList.size > 0) {
                val node = mutableList.removeAt(mutableList.size - 1)
                result.add(0, node.value)
                node.left?.let {
                    mutableList.add(it)
                }
                node.right?.let {
                    mutableList.add(it)
                }
            }
            print(result)

        }

    }
}