package com.singerstone.jojo;

import static com.singerstone.jojo.排序链表.printListNode;

public class 删除链表节点 {

    public static void main(String[] args) {
        ListNode head = new ListNode(4, new ListNode(2,
                new ListNode(1, new ListNode(3, null))));
        printListNode(new 删除链表节点().deleteNode(head, 2));
    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode result = head;

        ListNode pre = null;
        while (head != null) {
            if (head.value == val) {
                if (pre == null) {
                    head = head.next;
                    return head;
                } else {
                    pre.next = head.next;
                    head.next = null;
                    break;
                }

            }
            pre = head;
            head = head.next;
        }
        return result;

    }

}
