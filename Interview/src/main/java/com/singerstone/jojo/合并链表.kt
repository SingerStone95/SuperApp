package com.singerstone.jojo

class 合并链表 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var l1 = Node(0, Node(1, Node(2, Node(3, Node(4, Node(5, null))))))
            var l2 = Node(2, Node(3, Node(4, Node(5, Node(6, null)))))
            printLinkList(merge2(l1, l2))

        }

        // 递归版本
        fun merge2(l1: Node?, l2: Node?): Node? {
            if (l1 == null) {
                return l2
            }
            if (l2 == null) {
                return l1
            }
            if (l1.value!! < l2.value!!) {
                l1.next = merge(l1.next, l2)
                return l1
            } else {
                l2.next = merge(l2.next, l1)
                return l2
            }

        }

        // 标准版本
        fun merge1(l11: Node?, l22: Node?): Node? {
            var l1 = l11
            var l2 = l22
            val stub = Node(-1)
            var tail = stub
            while (l1 != null && l2 != null) {
                if (l1.value!! < l2.value!!) {
                    tail.next = l1
                    l1 = l1.next
                } else {
                    tail.next = l2
                    l2 = l2.next
                }
                tail = tail.next!!
            }
            if (l1 == null) tail.next = l2 else tail.next = l1
            return stub.next

        }

        // 自己手撸的垃圾算法版本
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