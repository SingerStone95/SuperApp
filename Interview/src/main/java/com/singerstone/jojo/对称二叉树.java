package com.singerstone.jojo;

public class 对称二叉树 {

    public static void main(String[] args) {
        Integer[] array = new Integer[]{0, 1, 1, 2, 2, 2, null};
        TreeNode root = 二叉树构建.makeTreeRecursion(array, 0, array.length);
        System.out.println(new 对称二叉树().isMirror(root));

    }

    /**
     * 最暴力的方式就是先 中左右遍历 再中右左遍历 ，然后对比
     * 优化写法如下可以在一次递归完成，本质上是一样的
     */
    boolean isMirror(TreeNode node) {
        return isMirror(node, node);
    }

    boolean isMirror(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (n1 == null || n2 == null) {
            return false;
        }
        if (n1.value != n2.value) {
            return false;
        }
        // n1 -> 中左右
        // n2 -> 中右左
        return isMirror(n1.left, n2.right) && isMirror(n1.right, n2.left);

    }
}