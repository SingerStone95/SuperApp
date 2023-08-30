package com.singerstone.jojo;

import static com.singerstone.jojo.排序链表.printListNode;

public class 两两交换链表中的节点 {

    public static void main(String[] args) {
        ListNode head = new ListNode(4, new ListNode(2,
                new ListNode(1, new ListNode(3, null))));
        printListNode(new 两两交换链表中的节点().swapPairs(head));
    }

    //1->2->3->4
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode cur = pre.next;
        while (cur != null) {
            ListNode next = cur.next;
            if (next == null) {
                break;
            }
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
            pre = cur;
            cur = cur.next;
        }

        return dummy.next;

    }

}
