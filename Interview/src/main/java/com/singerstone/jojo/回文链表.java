package com.singerstone.jojo;

public class 回文链表 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2,
                new ListNode(2, new ListNode(3, null))));
        System.out.println(new 回文链表().isPalindrome(head));
    }

    // 1 2 3 4 5
    // 1 2 3 4 5 6
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;

        }
        slow = reverse(slow);
        while (head != null && slow != null) {
            if (head.value != slow.value) {
                return false;
            }
            head = head.next;
            slow = slow.next;

        }
        return true;


    }

    ListNode reverse(ListNode head) {
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
