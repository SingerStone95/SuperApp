package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;

import java.util.ArrayList;

public class 二叉树最大深度 {
    public static void main(String[] args) {
        Integer[] values = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        TreeNode root = makeTreeRecursion(values, 0, values.length);
        System.out.println(maxDepth(root));

    }


    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
