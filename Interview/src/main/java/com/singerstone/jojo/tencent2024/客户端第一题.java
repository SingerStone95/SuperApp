package com.singerstone.jojo.tencent2024;

import com.singerstone.jojo.ListNode;

import static com.singerstone.jojo.NodeUtilKt.printLinkList;
import static com.singerstone.jojo.排序链表2023.printListNode;

public class 客户端第一题 {

    /**
     * 删除链表中值为k的数
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(2, new ListNode(3,
                new ListNode(2, new ListNode(1, null))));
        printListNode(deleteNode(head, 2));

    }

    // 1 -> 2 -> 3
    public static ListNode deleteNode(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;

        while (head != null) {
            if (head.value == k) {
                pre.next = head.next;
            } else {
                pre = head;
            }
            head = head.next;
        }

        return dummy.next;


    }
}
