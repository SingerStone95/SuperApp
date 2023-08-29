package com.singerstone.jojo;

public class 链表中倒数第K个节点 {

    public static void main(String[] args) {
        ListNode head = new ListNode(4, new ListNode(2,
                new ListNode(1, new ListNode(3, null))));
        System.out.println(new 链表中倒数第K个节点().getKthFromEnd(head, 2).value);
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode slow = head;

        int i = 1;
        while (head != null) {
            if (i > k) {
                slow = slow.next;
            }

            head = head.next;
            i++;

        }

        return slow;

    }
}
