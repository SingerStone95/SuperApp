package com.singerstone.jojo;

import static com.singerstone.jojo.排序链表2023.printListNode;

public class 旋转链表2023 {


    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2,
                new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        printListNode(new 旋转链表2023().rotateRight(head, 2));

    }

    //输入：head = [1,2,3,4,5], k = 2
    //输出：[4,5,1,2,3]
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        int count = 1;
        while (cur != null && cur.next != null) {
            count++;
            cur = cur.next;
        }
        k = k % count;
        cur.next = head; //链接成环
        int step = count - k;
        for (int i = 0; i < step - 1; i++) {
            head = head.next;
        }
        ListNode result = head.next;
        head.next = null;
        return result;

    }
}
