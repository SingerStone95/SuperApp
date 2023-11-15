package com.singerstone.jojo;

import static com.singerstone.jojo.排序链表2023.printListNode;

public class 两数相加链表2 {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(7, new ListNode(2,
                new ListNode(4, new ListNode(3, null))));
        ListNode head2 = new ListNode(5, new ListNode(6,
                new ListNode(4,null)));

        printListNode(new 两数相加链表2().addTwoNumbers(head1, head2));
    }


    //输入：l1 = [7,2,4,3], l2 = [5,6,4]
    //输出：[7,8,0,7]
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        int ext = 0;
        ListNode dummy = new ListNode(-1, null);
        ListNode head = dummy;
        while (l1 != null || l2 != null) {
            int sum = 0;
            if (l1 != null && l2 == null) {
                sum += l1.value;
            } else if (l1 == null && l2 != null) {
                sum += l2.value;
            } else {
                sum = l1.value + l2.value;
            }
            sum += ext;
            if (sum > 9) {
                ext = 1;
            } else {
                ext = 0;
            }
            head.next = new ListNode(sum % 10, null);
            head = head.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (ext > 0) {
            head.next = new ListNode(ext, null);
        }
        return reverse(dummy.next);

    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;

    }
}
