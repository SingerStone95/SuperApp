package com.singerstone.jojo;

import static com.singerstone.jojo.二叉搜索树.buildSearchTree;

public class 二叉搜索树与双向链表 {

    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6};
        //buildSearchTree(array, 0, array.length - 1);
        TreeNode node = new 二叉搜索树与双向链表()
                .treeToDoublyList(buildSearchTree(array, 0, array.length - 1));
        System.out.println(node);

    }

    // 中序遍历
    public TreeNode treeToDoublyList(TreeNode root) {
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    TreeNode pre = null;
    TreeNode head = null;

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (pre == null) {
            head = root;
        } else {
            root.left = pre;
            pre.right = root;
        }
        pre = root;
        dfs(root.right);
    }
}
