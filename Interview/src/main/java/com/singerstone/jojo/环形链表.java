package com.singerstone.jojo;

public class 环形链表 {

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }

        }
        return false;
    }

}
