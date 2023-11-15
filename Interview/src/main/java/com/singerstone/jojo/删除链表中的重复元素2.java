package com.singerstone.jojo;

import static com.singerstone.jojo.排序链表2023.printListNode;

public class 删除链表中的重复元素2 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(1,
                new ListNode(2, new ListNode(5, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(7, null)))))))));
        printListNode(new 删除链表中的重复元素2().deleteDuplicates(head));
    }


    //输入：head = [1,1,2,5,3,3,4,4,7]
    //输出：[2,5,7]
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        while (head != null) {
            ListNode next = head.next;
            boolean hasRaped = false;
            while (next != null && next.value == head.value) {
                hasRaped = true;
                next = next.next;
            }
            if (hasRaped){
                pre.next = next; // 有重复的元素就不要移动pre,只需要改变pre的next
            }else {
                pre = head;
            }
            head = next;

        }

        return dummy.next;


    }
}
