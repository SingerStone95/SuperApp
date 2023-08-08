package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.visitTree;

import java.util.ArrayList;
import java.util.List;

public class 二叉树的还原2J {

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inoder = {9, 3, 15, 20, 7};

        visitTree(buildTree(preorder, inoder));

    }

    public static int[] list2IntArray(List<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    //preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        if (preorder.length == 1 || inorder.length == 1) {
            return new TreeNode(preorder[0]);
        }
        int index = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                index = i;
                break;
            }
        }
        TreeNode root = new TreeNode(preorder[0]);
        List<Integer> sub_left_inoder = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            sub_left_inoder.add(inorder[i]);
        }
        if (sub_left_inoder.size() > 0) {
            ArrayList<Integer> sub_left_preoder = new ArrayList<>();
            for (int i = 1; i < 1 + sub_left_inoder.size(); i++) {
                sub_left_preoder.add(preorder[i]);

            }
            root.left =
                    buildTree(list2IntArray(sub_left_preoder), list2IntArray(sub_left_inoder));
        } else {
            root.left = (null);
        }

        List<Integer> sub_right_inoder = new ArrayList<>();
        for (int i = index + 1; i < inorder.length; i++) {
            sub_right_inoder.add(inorder[i]);
        }
        if (sub_right_inoder.size() > 0) {
            ArrayList<Integer> sub_right_preoder = new ArrayList<>();
            for (int i = 1 + sub_left_inoder.size(); i < preorder.length; i++) {
                sub_right_preoder.add(preorder[i]);
            }
            root.right = (
                    buildTree(list2IntArray(sub_right_preoder), list2IntArray(sub_right_inoder)));
        } else {
            root.right = (null);
        }

        return root;


    }

}
