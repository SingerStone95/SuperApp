package com.singerstone.jojo;

public class 树的子结构 {

    public static void main(String[] args) {

    }


    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return isSub(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    public static boolean isSub(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        if (A.value != B.value) {
            return false;
        }
        return isSub(A.left, B.left) && isSub(A.right, B.right);
    }


}
