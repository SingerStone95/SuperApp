package com.singerstone.jojo

class 链表其他 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var l1 = Node(0, Node(1, Node(2, Node(3, Node(4, Node(5, null))))))
            var l2 = Node(2, Node(3, Node(4, Node(5, Node(6, null)))))
            printLinkList(merge(l1, l2))

        }

        // 自己手撸的垃圾算法
        fun merge(l11: Node?, l22: Node?): Node? {
            var l1 = l11
            var l2 = l22
            var head: Node? = null
            var tail: Node? = null
            while (!(l1 == null && l2 == null)) {
                if (l1 == null) {
                    if (head == null) {
                        head = l2
                    } else {
                        tail?.next = l2
                    }
                    break
                } else if (l2 == null) {
                    if (head == null) {
                        head = l1
                    } else {
                        tail?.next = l1
                    }
                    break
                } else {
                    if (l1.value!! < l2.value!!) {
                        if (head == null) {
                            head = l1
                            tail = head
                        } else {
                            tail?.next = l1
                            tail = tail?.next
                        }
                        l1 = l1.next
                    } else {
                        if (head == null) {
                            head = l2
                            tail = head
                        } else {
                            tail?.next = l2
                            tail = tail?.next
                        }
                        l2 = l2.next
                    }

                }
            }
            return head

        }
    }
}