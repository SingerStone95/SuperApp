package com.singerstone.jojo;

public class 树的子结构 {

    public static void main(String[] args) {

    }


    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return isSub(A, B) || isSubStructure(A.getLeft(), B) || isSubStructure(A.getRight(), B);
    }

    public static boolean isSub(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        if (A.getValue() != B.getValue()) {
            return false;
        }
        return isSub(A.getLeft(), B.getLeft()) && isSub(A.getRight(), B.getRight());
    }


}
