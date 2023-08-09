package com.singerstone.jojo;

import static com.singerstone.jojo.二叉搜索树.buildSearchTree;
import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;
import static com.singerstone.jojo.二叉树构建.visitTree;

public class 二叉搜索树最小公共祖先 {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6};
        TreeNode root = makeTreeRecursion(array, 0, array.length);
        visitTree(root);
        System.out.println(lowestCommonAncestor(root, new TreeNode(4), new TreeNode(6)).value);
        System.out.println(lowestCommonAncestor2(root, new TreeNode(2), new TreeNode(6)).value);
    }

    // 递归方式
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.value > p.value && root.value > q.value) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.value < p.value && root.value < q.value) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }

    }


    // 循环方式
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        while (true) {
            if (root.value > p.value && root.value > q.value) {
                root = root.left;
            } else if (root.value < p.value && root.value < q.value) {
                root = root.right;
            } else {
                return root;
            }
        }

    }
}
