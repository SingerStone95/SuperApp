package com.singerstone.jojo.tencent2024;

import com.singerstone.jojo.TreeNode;

import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;

public class 客户端第二题 {
    /**
     * 给定二叉树，每个点价值是0或1，问从根节点到叶子节点组成的二进制数有多少个在l,r范围中
     * 遍历往下走，大于r返回，最后看是不是大于等于l
     *
     * @param args
     */
    public static void main(String[] args) {
        Integer[] array = {1, 1, 1, 1, 1, 0, 0};
        TreeNode root = makeTreeRecursion(array, 0, array.length);
        System.out.println(number_of_different_plans(root,7,7));
    }

    static int result = 0;

    public static int number_of_different_plans(TreeNode root, int l, int r) {
        dfs(root, l, r, 0);
        return result;
    }

    public static void dfs(TreeNode root, int l, int r, int sum) {
        if (root == null) {
            return;
        }
        int cur = (sum << 1) + root.value;
        if (cur > r) {
            return;
        }
        if (root.left == null && root.right == null && cur >= l && cur <= r) {
            result++;
            return;
        }
        dfs(root.left, l, r, cur);
        dfs(root.right, l, r, cur);
    }

}
