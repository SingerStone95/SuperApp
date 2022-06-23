package com.singerstone.jojo

class 链表 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Node(0, Node(1, Node(2, Node(3, null)))).let {




                printLinkList( revertLinkedList(it))


            }

        }


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


        fun revertLinkedList2(head: Node?): Node? {
            if (head?.next == null) {
                return head
            }
            val node = revertLinkedList2(head.next)

            head.next?.next = head
            head.next = null

            return node


        }

        fun printLinkList(head: Node?) {
            var node = head
            while (node != null) {
                print(node.value)
                node = node.next
            }

        }

    }


}


class Node(var value: Int?, var next: Node?) {

}