package com.singerstone.jojo;

import static com.singerstone.jojo.排序链表2023.printListNode;

public class 翻转链表 {

    public static void main(String[] args) {
        ListNode head = new ListNode(4, new ListNode(2,
                new ListNode(1, new ListNode(3, null))));
        printListNode(new 翻转链表().reverseList(head));
    }

    /**
     * 递归写法
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        dfs(head);
        return result;
    }

    ListNode result = null;

    public ListNode dfs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode next = dfs(head.next); // 利用递归栈的性质
        if (next == null) { //递归最后一个节点了，作为头结点记录一下
            result = head;
        } else {
            next.next = head;
            head.next = null;
        }
        return head;
    }

    /**
     * 迭代写法
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
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
