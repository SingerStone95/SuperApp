package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;

public class 二叉搜索树的后序遍历 {

    public static void main(String[] args) {
        System.out.println(new  二叉搜索树的后序遍历().verifyPostorder(new int[]{1,3,2,6,5}));
    }

    // 左右中
    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorder(postorder, 0, postorder.length - 1);
    }

    public boolean verifyPostorder(int[] postorder, int left, int right) {
        if (left >= right) {
            return true;
        }
        int root = postorder[right];

        int split = left;
        while (split < right && postorder[split] < root) {
            split++;
        }
        for (int i = split; i < right; i++) {
            if (postorder[i] < root) {
                return false;
            }
        }

        return verifyPostorder(postorder, left, split - 1) && verifyPostorder(postorder, split,
                right-1);
    }
}
