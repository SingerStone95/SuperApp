package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;

public class 树的子结构 {

    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6};
        TreeNode root = makeTreeRecursion(array, 0, array.length);

        Integer[] array2 = {1, 2, 3};
        TreeNode root2 = makeTreeRecursion(array, 0, array.length);

        System.out.println(isSubStructure(root, root2));

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
        if (A == null) { //不能A为空B不为空
            return false;
        }
        if (A.value != B.value) {
            return false;
        }
        return isSub(A.left, B.left) && isSub(A.right, B.right);
    }


}
