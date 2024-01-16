package com.singerstone.jojo

fun printLinkList(head: Node?) {
    var node = head
    while (node != null) {
        print(node.value)
        node = node.next
    }
    println()
}

class Node(@JvmField var value: Int? = -1, @JvmField var next: Node? = null)

class ListNode(@JvmField var value: Int? = -1, @JvmField var next: ListNode? = null)