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
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode next = reverseList(head.next);
        head.next.next = head; // 这里不能用 next ，因为 next 一直指向原链表的结尾
        head.next = null;
        return next; // next 属于透传参数一直指向原链表的结尾
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
