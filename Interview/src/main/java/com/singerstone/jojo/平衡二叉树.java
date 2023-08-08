package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;

public class 平衡二叉树 {
    public static void main(String[] args) {
        Integer[] values = {0, null, 2, null, null, 5, 6};
        TreeNode root = makeTreeRecursion(values, 0, values.length);
        System.out.println(isBalanced(root));
    }

    public static boolean isBalanced(TreeNode root) {
        return dfs(root) != -1;

    }

    public static int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        if (left == -1) {
            return -1;
        }
        int right = dfs(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) < 2 ? (Math.max(left, right) + 1) : -1;

    }

}
