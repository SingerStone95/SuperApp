package com.singerstone.jojo;

import static com.singerstone.jojo.排序链表.printListNode;

public class 合并两个排序链表 {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(3,
                new ListNode(5, new ListNode(7, null))));
        ListNode head2 = new ListNode(2, new ListNode(4,
                new ListNode(6, new ListNode(8, null))));

        printListNode(mergeTwoLists(head1,head2));
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else {
            if (l1.value < l2.value) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            }else {
                l2.next = mergeTwoLists(l2.next, l1);
                return l2;
            }
        }

    }

}
