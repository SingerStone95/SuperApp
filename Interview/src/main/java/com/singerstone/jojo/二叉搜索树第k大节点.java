package com.singerstone.jojo;

import static com.singerstone.jojo.二叉搜索树.buildSearchTree;

public class 二叉搜索树第k大节点 {

    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6};

        TreeNode root = buildSearchTree(array, 0, array.length - 1);
        System.out.println(new 二叉搜索树第k大节点().kthLargest(root, 2));

    }


    public int kthLargest(TreeNode root, int k) {
        dfs(root, k);
        return result;
    }

    int tmp = 0;
    int result = 0;


    // 第K大 ，从右到左遍历
    void dfs(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        dfs(root.right, k);

        tmp++;
        if (tmp == k) {
            result = root.value;
        }
        dfs(root.left, k);

    }
}
