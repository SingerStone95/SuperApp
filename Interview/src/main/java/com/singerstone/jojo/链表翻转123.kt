package com.singerstone.jojo

class 链表 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Node(0, Node(1, Node(2, Node(3, Node(4, Node(5, null)))))).let {
                //printLinkList(revertLinkedList(it))
                printLinkList(it)
                println()
                printLinkList(revertLinkListRange(it, 2, 4))
            }
        }


        // 非递归
        fun revertLinkedList(head: Node?): Node? {
            var first: Node? = null
            var second: Node? = head
            while (second != null) {
                val tmp = second.next
                second.next = first
                first = second
                second = tmp
            }
            return first
        }


        // 递归
        fun revertLinkedList2(head: Node?): Node? {
            if (head?.next == null) {
                return head
            }
            val node = revertLinkedList2(head.next)
            head.next?.next = head
            head.next = null

            return node
        }

        // 1->2->3->4->5

        private fun revertLinkListRange(head: Node?, left: Int, right: Int): Node? {
            val stub: Node? = Node(-1)
            stub?.next = head
            var pre = stub
            for (i in 0 until left - 1) {
                pre = pre?.next
            }
            val cur = pre?.next
            for (i in left until right) {
                val tmp = cur?.next
                cur?.next = tmp?.next
                tmp?.next = pre?.next
                pre?.next = tmp
            }

            return stub?.next
        }


    }


}

fun printLinkList(head: Node?) {
    var node = head
    while (node != null) {
        print(node.value)
        node = node.next
    }
    println()
}

class Node(var value: Int? = -1, var next: Node? = null)