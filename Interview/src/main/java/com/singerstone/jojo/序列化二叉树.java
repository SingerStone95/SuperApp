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

        TreeNode node=new 序列化二叉树().deserialize(serialize);
        visitTree(node);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        dfs(root);
        return result.substring(0, result.length() - 1);
    }

    String result = "";

    public void dfs(TreeNode root) {
        if (root == null) {
            result += "null,";
            return;
        }
        result += root.value;
        result += ",";
        dfs(root.left);
        dfs(root.right);
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

    TreeNode dfs2(ArrayList<String> arr) {
        if (arr.get(0).equals("null")) {
            arr.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(arr.get(0)));
        arr.remove(0);
        root.left = dfs2(arr);
        root.right = dfs2(arr);
        return root;

    }

}
