package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.visitTree;

public class 二叉树还原中序后序java {

    public static void main(String[] args) {
        int[] inOrder = new int[]{9, 3, 15, 20, 7};
        int[] postOrder = new int[]{9, 15, 7, 20, 3};
        visitTree(new 二叉树还原中序后序java().buildTree(inOrder, postOrder));
    }

    /**
     * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
     * 输出：[3,9,20,null,null,15,7]
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder, int il, int ir, int pl, int pr) {
        if (il > ir) {
            return null;
        }
        int mi = il;
        for (int i = il; i <= ir; i++) {
            if (inorder[i] == postorder[pr]) {
                mi = i;
            }
        }
        int left_len = mi - il;
        TreeNode root = new TreeNode(postorder[pr]);
        root.left = buildTree(inorder, postorder, il, mi - 1, pl, pl + left_len - 1);
        root.right = buildTree(inorder, postorder, mi + 1, ir, pl + left_len, pr - 1);
        return root;
    }
}
