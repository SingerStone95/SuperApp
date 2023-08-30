package com.singerstone.jojo;

public class 两个链表的第一个公共节点 {

    public static void main(String[] args) {

    }

    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode n1 = headA;
        ListNode n2 = headB;

        while (n1 != n2) {

            //这里要注意 不能先  n1.next 再比较
            if (n1 == null) {
                n1 = headB;
            } else {
                n1 = n1.next;
            }

            if (n2 == null) {
                n2 = headA;
            } else {
                n2 = n2.next;
            }

        }
        return n1;

    }
}
