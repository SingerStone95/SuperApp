package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树的遍历.printArray;

import java.util.ArrayList;

public class 从尾到头打印链表 {

    public static void main(String[] args) {
        ListNode head = new ListNode(4, new ListNode(2,
                new ListNode(1, new ListNode(3, null))));
        printArray(new 从尾到头打印链表().reversePrint(head));
    }

    ArrayList<Integer> result = new ArrayList<>();

    public int[] reversePrint(ListNode head) {

        dfs(head);
        int[] array = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            array[i] = result.get(i);
        }

        return array;
    }

    public void dfs(ListNode head) {
        if (head == null) {
            return;
        }
        dfs(head.next);
        result.add(head.value);

    }

}
