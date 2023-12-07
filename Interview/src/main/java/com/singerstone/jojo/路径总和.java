package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;

public class 路径总和 {

    public static void main(String[] args) {
        Integer[] values = {0, 1, 2, 3, 4};
        TreeNode root = makeTreeRecursion(values, 0, values.length);
        System.out.println(hasPathSum(root, 4));
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return targetSum == 0;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.value;
        }
        return hasPathSum(root.left, targetSum - root.value) || hasPathSum(root.right, targetSum - root.value);
    }

    /**
     * 输入：root = [], targetSum = 0
     * 输出：false
     * 解释：由于树是空的，所以不存在根节点到叶子节点的路径。
     */
    //这么写因为没有处理 root 为空数的情况，不能ac , 主要是题目限制空树不为 0
    public static boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return targetSum == 0;
        }
        return hasPathSum2(root.left, targetSum - root.value) || hasPathSum2(root.right, targetSum - root.value);
    }

}
