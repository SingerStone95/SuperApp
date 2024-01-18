package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.visitTree;

import java.util.ArrayList;
import java.util.List;

public class 二叉树的还原先序中序Java {

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inoder = {9, 3, 15, 20, 7};

        visitTree(buildTree(preorder, inoder));

    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, preorder.length - 1);
    }

    /**
     * int[] preorder = {3, 9, 20, 15, 7}; int[] inoder = {9, 3, 15, 20, 7};
     */
    public static TreeNode buildTree(int[] preorder, int pl, int pr, int[] inorder, int il,
                                     int ir) {

        if (pl > pr) {
            return null;
        }
        int im = il;
        for (int i = il; i <= ir; i++) {
            if (preorder[pl] == inorder[i]) {
                im = i;
            }
        }
        int left_len = im - il;
        TreeNode root = new TreeNode(preorder[pl]);

        root.left = buildTree(preorder, pl + 1, pl + left_len, inorder, il, im - 1);
        root.right = buildTree(preorder, pl + 1 + left_len, pr, inorder, im + 1, ir);
        return root;
    }


}
