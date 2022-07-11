package com.singerstone.jojo

class 旋转链表 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Node(0, Node(1, Node(2, Node(3, Node(4, Node(5, null)))))).let {

                //printLinkList(roteList(4, it))

                println(lastKNode(1, it))


            }
        }

        // 旋转链表
        fun roteList(n: Int, head: Node): Node? {
            // 先求出链表长度
            if (head == null) {
                return head
            }
            var len = 1
            var cur = head
            while (cur.next != null) {
                len++
                cur = cur.next!!
            }
//            print(len)
            val m = n % len //真实旋转的长度
            cur.next = head //首位相连

            cur = head
            for (i in 1 until len - m) {
                cur = cur.next!!
            }
            val tmp = cur.next
            cur.next = null

            return tmp

        }

        /**
         * 倒数第K个
         */
        fun lastKNode(k: Int, head: Node?): Int? {
            head ?: return null
            var k = k - 1
            var p1 = head
            var p2 = head
            while (p1?.next != null) {
                p1 = p1.next
                if (k <= 0) {
                    p2 = p2?.next
                }
                k--
            }
            return p2?.value
        }

    }
}