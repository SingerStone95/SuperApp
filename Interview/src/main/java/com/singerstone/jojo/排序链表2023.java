package com.singerstone.jojo;

public class 排序链表2023 {

    public static void main(String[] args) {
        ListNode head = new ListNode(4, new ListNode(2,
                new ListNode(1, new ListNode(3, null))));

        printListNode(new 排序链表2023().sortList(head));

    }

    public static void printListNode(ListNode head) {
        while (head != null) {
            System.out.print(head.value);
            head = head.next;

        }
        System.out.println();

    }

     ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = slow;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }
        pre.next = null;
        return mergeList(sortList(head), sortList(slow));
    }

    //4 2
    // 1 3
    ListNode mergeList(ListNode n1, ListNode n2) {
        if (n1 == n2) {
            return n1;
        }
        if (n1 == null) {
            return n2;
        } else if (n2 == null) {
            return n1;
        } else {
            if (n1.value <= n2.value) {
                n1.next = mergeList(n1.next, n2);
                return n1;
            } else {
                n2.next = mergeList(n1, n2.next);
                return n2;
            }
        }

    }
}
