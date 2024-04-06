package com.singerstone.jojo.tencent2024;

import com.singerstone.jojo.ListNode;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class 后端第二题 {

    /**
     * 小红拿到了一个链表。她准备将这个链表断裂成两个链表，再拼接到一起，使得链表从头节点到尾部升序。你能帮小红判断能否达成目的吗？
     * 给定的为一个链表数组，你需要对于数组中每个链表进行一次“是”或者“否”的答案回答，并返回布尔数组。
     * 每个链表的长度不小于 2，且每个链表中不包含两个相等的元素。所有链表的长度之和保证不超过10^5
     * 输入
     * [(1.2,3),(2,3,1).(3,2,1)]
     * 输出
     * 复制 [true,true,false]
     */
    public static void main(String[] args) {
        ListNode head1 = new ListNode(7, new ListNode(8,
                new ListNode(9, new ListNode(8, null))));
        ListNode head2 = new ListNode(7, new ListNode(8,
                new ListNode(1, new ListNode(2, null))));
        List<ListNode> input = new ArrayList<>();
        input.add(head1);
        input.add(head2);
        System.out.println(new 后端第二题().canSorted(input));

    }

    List<Boolean> canSorted(List<ListNode> lists) {
        List<Boolean> result = new ArrayList<>();
        for (ListNode head : lists) {
            ListNode pre = null;
            ListNode curHead = head;
            int count = 0;
            while (head != null) {
                if (pre != null && head.value < pre.value) {
                    count++;
                    if (count > 1) {
                        result.add(false);
                        break;
                    }
                }
                pre = head;
                head = head.next;
            }
            if (count <= 1 && curHead.value > pre.value) {
                result.add(true);
            } else {
                result.add(false);
            }

        }
        return result;

    }

}
