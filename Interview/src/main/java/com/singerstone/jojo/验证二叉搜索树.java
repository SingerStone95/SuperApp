package com.singerstone.jojo;

import static com.singerstone.jojo.二叉搜索树.buildSearchTree;

public class 验证二叉搜索树 {


    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6};
        buildSearchTree(array, 0, array.length - 1);
        System.out.println(isValidBST(buildSearchTree(array, 0, array.length - 1)));
    }

    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

    }

    public static boolean isValidBST(TreeNode root, int low, int high) {
        if (root == null) {
            return true;
        }

        if (root.value <= low || root.value >= high) {
            return false;
        }
        return isValidBST(root.left, low, root.value) && isValidBST(root.right, root.value, high);
    }

}
