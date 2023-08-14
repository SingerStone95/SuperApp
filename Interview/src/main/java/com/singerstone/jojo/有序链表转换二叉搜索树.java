package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.visitTree;

public class 有序链表转换二叉搜索树 {
    public static void main(String[] args) {

        ListNode head = new ListNode(0, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null))))));
        TreeNode result = new 有序链表转换二叉搜索树().sortedListToBST(head);
        visitTree(result);
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        return dfs(head, null);
    }

    public TreeNode dfs(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode mid = getMid(left, right);
        TreeNode root = new TreeNode(mid.value);
        root.left = dfs(left, mid);
        root.right = dfs(mid.next, right);
        return root;
    }

    // right 不被包含
    ListNode getMid(ListNode left, ListNode right) {
        ListNode fast = left;
        ListNode slow = left;
        while (fast != right && fast.next != right) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;

    }
}


