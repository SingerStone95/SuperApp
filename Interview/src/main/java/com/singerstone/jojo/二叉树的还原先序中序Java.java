package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.visitTree;

import java.util.ArrayList;
import java.util.List;

public class 二叉树的还原先序中序Java {

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inoder = {9, 3, 15, 20, 7};

        visitTree(buildTree(preorder, inoder));
        visitTree(buildTree2(preorder, 0, preorder.length - 1, inoder, 0, preorder.length - 1));

    }

    public static int[] list2IntArray(List<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * int[] preorder = {3, 9, 20, 15, 7}; int[] inoder = {9, 3, 15, 20, 7};
     */
    public static TreeNode buildTree2(int[] preorder, int pl, int pr, int[] inorder, int il,
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

        root.left = buildTree2(preorder, pl + 1, pl + 1 + left_len, inorder, il, il + left_len);
        root.right = buildTree2(preorder, pl + left_len + 1, pr, inorder, il + left_len + 1, ir);
        return root;
    }

    //    preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        if (preorder.length == 1 || inorder.length == 1) {
            return new TreeNode(preorder[0]);
        }
        // 在 inorder 里面找到根节点位置
        int index = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                index = i;
                break;
            }
        }
        TreeNode root = new TreeNode(preorder[0]);
        List<Integer> leftInOrder = new ArrayList<>();
        // [0-index) 作为 leftInOrder
        for (int i = 0; i < index; i++) {
            leftInOrder.add(inorder[i]);
        }
        // 用 leftOrder 的长度去取 leftPreOder [1:length]
        if (leftInOrder.size() > 0) {
            ArrayList<Integer> leftPreOrder = new ArrayList<>();
            for (int i = 1; i < 1 + leftInOrder.size(); i++) {
                leftPreOrder.add(preorder[i]);
            }
            root.left =
                    buildTree(list2IntArray(leftPreOrder), list2IntArray(leftInOrder));
        } else {
            root.left = null;
        }

        //[index+1,len) 作为 rightInOrder
        List<Integer> rightInOrder = new ArrayList<>();
        for (int i = index + 1; i < inorder.length; i++) {
            rightInOrder.add(inorder[i]);
        }
        // 用 leftOrder 的长度去取 rightPreOder [1+:end]
        if (rightInOrder.size() > 0) {
            ArrayList<Integer> rightPreOrder = new ArrayList<>();
            for (int i = 1 + leftInOrder.size(); i < preorder.length; i++) {
                rightPreOrder.add(preorder[i]);
            }
            root.right =
                    buildTree(list2IntArray(rightPreOrder), list2IntArray(rightInOrder));
        } else {
            root.right = null;
        }

        return root;


    }

}
