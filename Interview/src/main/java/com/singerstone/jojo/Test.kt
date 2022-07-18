package com.singerstone.jojo

class Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var l1 = Node(0, Node(1, Node(2, Node(3, Node(4, Node(5, null))))))
            printLinkList(l1)
            val array = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
            val root = makeTreeRecursion(array, 0, array.size)
            visitTree(root)
            Thread(
                object:Runnable{
                    override fun run(){
                        print("yogachen")
                
                    }}).start()    
             Thread.sleep(1000)

        }
      
}




}


    // 下面是环境搭建

        class TreeNode {
            var value: Int = -1
            var left: TreeNode? = null
            var right: TreeNode? = null
        }

        class Node(var value: Int? = -1, var next: Node? = null)

        fun printLinkList(head: Node?) {
            var node = head
            while (node != null) {
                print(node.value)
                node = node.next
            }
            println()
        }

              /**
         * 递归创建二叉树，层序
         */
        fun makeTreeRecursion(array: Array<Int>, nodeIndex: Int, len: Int): TreeNode? {
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
