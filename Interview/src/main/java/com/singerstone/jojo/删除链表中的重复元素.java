package com.singerstone.jojo;

import static com.singerstone.jojo.排序链表.printListNode;

public class 删除链表中的重复元素 {
    public static void main(String[] args) {
        ListNode head = new ListNode(2, new ListNode(2,
                new ListNode(2, new ListNode(2, null))));
        printListNode(new 删除链表中的重复元素().deleteDuplicates(head));
    }


    //输入：head = [1,1,2]
    //输出：[1,2]

    public ListNode deleteDuplicates(ListNode head) {
        ListNode result = head;
        ListNode pre = null;
        while (head != null) {
            if (pre != null && pre.value == head.value) {
                pre.next = head.next;
                head = head.next;
                continue;
            }

            pre = head;
            head = head.next;
        }

        return result;

    }
}
