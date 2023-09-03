package com.singerstone.jojo;

import static com.singerstone.jojo.排序链表.printListNode;

public class 重排链表 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2,
                new ListNode(3, new ListNode(4, new ListNode(5,null)))));

        new 重排链表().reorderList(head);
        printListNode(head);
    }

    //输入：head = [1,2,3,4]
    //输出：[1,4,2,3]

    // 12345
    ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }
        if (fast==slow){
            return;
        }

        pre.next = null;
        slow = reverse(slow);

        ListNode dummy = new ListNode(-1, null);

        ListNode cur = dummy;
        boolean left = true;
        while (slow != null || head != null) {

            if (slow == null) {
                cur.next = head;
                head = head.next;
            } else if (head == null) {
                cur.next = slow;
                slow = slow.next;

            } else {
                if (left) {
                    cur.next = head;
                    head = head.next;
                } else {
                    cur.next = slow;
                    slow = slow.next;
                }
                left = !left;

            }
            cur = cur.next;

        }

    }

}
