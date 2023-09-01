package com.singerstone.jojo;

import static com.singerstone.jojo.排序链表.printListNode;

public class 两数相加链表 {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(6, new ListNode(2,
                new ListNode(1, new ListNode(3, null))));
        ListNode head2 = new ListNode(4, new ListNode(2,
                new ListNode(1, new ListNode(3, null))));

        printListNode(new 两数相加链表().addTwoNumbers(head1, head2));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1, null);
        ListNode head = dummy;

        int ext = 0;
        while (l1 != null || l2 != null) {
            int sum;
            if (l1 != null && l2 == null) {
                sum = l1.value;
            } else if (l1 == null && l2 != null) {
                sum = l2.value;
            } else {
                sum = l1.value + l2.value;
            }
            sum += ext;
            head.next = new ListNode(sum % 10, null);
            head = head.next;
            if (sum >= 10) {
                ext = 1;
            } else {
                ext = 0;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (ext!=0){
            head.next=new ListNode(ext, null);
        }
        return dummy.next;

    }
}
