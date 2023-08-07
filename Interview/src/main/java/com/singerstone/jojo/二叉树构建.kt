package com.singerstone.jojo

class 二叉树构建 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val array = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
            val root = makeTreeRecursion(array, 0, array.size)
            visitTree(root)

            println()
            visitTree(mirrorTree(root))
            println()
            visitTree(mirrorTree2(root))
        }

        /**
         * 递归创建二叉树，层序
         */
        @JvmStatic
        fun makeTreeRecursion(array: Array<Int>, nodeIndex: Int, len: Int): TreeNode? {
            if (nodeIndex >= len) {
                return null
            }
            val node = TreeNode(nodeIndex)
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


        /**
         * 二叉树的镜像
         * 0
         * 12
         * 34 56
         * 78 9
         *
         * 0
         * 21
         * 65 43
         * 9 87
         */
        fun mirrorTree(root: TreeNode?): TreeNode? {
            if (root == null) {
                return null
            }
            val list = arrayListOf<TreeNode?>()
            list.add(root)

            while (list.size != 0) {
                val node = list.removeFirst()
                // 这里需要理解，是先add 还是先交换顺序，其实是无所谓的，只要都遍历到就行
                // 还有就是务必不能把null添加进去列表！！！

                node?.left?.let {
                    list.add(it)
                }
                node?.right?.let {
                    list.add(it)
                }
                val tmp = node?.left
                node?.left = node?.right
                node?.right = tmp

            }
            return root
        }

        fun mirrorTree2(root: TreeNode?): TreeNode? {
            if (root == null) {
                return null
            }
            val tmp = root.left
            root.left = mirrorTree2(root.right)
            root.right = mirrorTree2(tmp)
            return root
        }
    }

}


class TreeNode(var value: Int) {
    constructor() : this(-1)

    var left: TreeNode? = null
    var right: TreeNode? = null
}