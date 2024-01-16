package com.singerstone.jojo;

import static com.singerstone.jojo.二叉搜索树.buildSearchTree;
import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;

public class 二叉树的直径 {

    public static void main(String[] args) {
        Integer[] values = {1, 2, 3, 4, 5};
        TreeNode root = makeTreeRecursion(values, 0, values.length);
        System.out.println(diameterOfBinaryTree(root));
        System.out.println(new 二叉树的直径().diameterOfBinaryTree2(root));
    }


    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = diameterOfBinaryTree(root.left);
        int right = diameterOfBinaryTree(root.right);
        return Math.max(Math.max(deep(root.left) + deep(root.right), left), right);
    }

    public static int deep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(deep(root.left), deep(root.right)) + 1;
    }


    // 精简写法
    int max = Integer.MIN_VALUE;

    public int diameterOfBinaryTree2(TreeNode root) {
        dfs(root);
        return max;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        max = Math.max(max, left + right);

        return Math.max(left, right) + 1;


    }
}
