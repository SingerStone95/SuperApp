package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;
import static com.singerstone.jojo.二叉树构建.visitTree;

public class 二叉树最大路径和 {


    public static void main(String[] args) {
        Integer[] values = {0, 1, 2, 3, 4};
        TreeNode root = makeTreeRecursion(values, 0, values.length);
        System.out.println(new 二叉树最大路径和().maxPathSum(root));
    }


    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxPathSum(root.left);
        int right = maxPathSum(root.right);
        //返回当前节点贡献值 ,必须是包含了当前节点
        return root.value + Math.max(Math.max(left, 0), Math.max(right, 0));
    }


}
