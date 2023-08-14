package com.singerstone.jojo;

import static com.singerstone.jojo.二叉搜索树.buildSearchTree;

public class 验证二叉搜索树 {


    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6};
        buildSearchTree(array, 0, array.length - 1);
        System.out.println(isValidBST(buildSearchTree(array, 0, array.length - 1)));
    }

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.left.value >= root.value) {
            return false;
        } else if (root.right != null && root.right.value <= root.value) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

}
