package com.singerstone.jojo;

import static com.singerstone.jojo.排序链表2023.printListNode;

public class 翻转链表 {

    public static void main(String[] args) {
        ListNode head = new ListNode(4, new ListNode(2,
                new ListNode(1, new ListNode(3, null))));
        printListNode(new 翻转链表().reverseList(head));
    }

    // 递归解法
    public ListNode reverseList(ListNode head) {
        dfs2(head);
        return result;

    }

    ListNode pre;

    void dfs2(ListNode head) {
        if (head == null) {
            return;
        }
        dfs2(head.next);
        if (pre == null) {
            result = head;
        } else {
            pre.next = head;
        }
        pre = head;
        head.next = null;

    }


    ListNode result = null;

    public ListNode dfs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode next = dfs(head.next); // 利用递归栈的性质
        if (next == null) {
            result = head;
        } else {
            next.next = head;
            head.next = null;
        }
        return head;
    }

    public ListNode reverseList2(ListNode head) {

        ListNode pre = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }


}
