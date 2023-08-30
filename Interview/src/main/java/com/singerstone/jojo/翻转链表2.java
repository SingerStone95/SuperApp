package com.singerstone.jojo;

import static com.singerstone.jojo.排序链表.printListNode;

public class 翻转链表2 {


    public static void main(String[] args) {
        ListNode head = new ListNode(4, new ListNode(2,
                new ListNode(1, new ListNode(3, null))));
        printListNode(reverseBetween(head,2,4));
    }

    //-1->1->2->3->4->5
    //-1->1->3->2->4->5
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode virtual = new ListNode(-1, head);
        ListNode pre = virtual;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;

        for (int i = 0; i < right - left; i++) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;

        }
        return virtual.next;
    }
}
