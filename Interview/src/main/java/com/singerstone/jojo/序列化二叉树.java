package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;
import static com.singerstone.jojo.二叉树构建.visitTree;

import java.util.ArrayList;

public class 序列化二叉树 {

    public static void main(String[] args) {
        Integer[] values = {0, 1, 2, 3, 4};
        TreeNode root = makeTreeRecursion(values, 0, values.length);
        System.out.println();
        String serialize = new 序列化二叉树().serialize(root);
        System.out.println(serialize);

        TreeNode node = new 序列化二叉树().deserialize(serialize);
        visitTree(node);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String result = dfs(root);
        return result.substring(0, result.length() - 1);
    }

    // 先序遍历序列化
    public String dfs(TreeNode root) {
        if (root == null) {
            return "null,";
        }
        return root.value + "," + dfs(root.left) + dfs(root.right);
    }

    //中-左-右
    TreeNode dfs2(ArrayList<String> arr) {
        if (arr.get(0).equals("null")) {
            arr.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(arr.get(0)));
        arr.remove(0);
        root.left = dfs2(arr);
        root.right = dfs2(arr);
        return root;
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] list = data.split(",");
        ArrayList<String> arr = new ArrayList();
        for (String node : list) {
            arr.add(node);
        }

        return dfs2(arr);
    }


}