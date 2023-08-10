package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;

import java.util.ArrayList;
import java.util.List;

public class 二叉树的所有路径 {


    public static void main(String[] args) {
        Integer[] values = {0, 1, 2, 3, 4};
        TreeNode root = makeTreeRecursion(values, 0, values.length);
        System.out.println(new 二叉树的所有路径().binaryTreePaths(root));
    }

    public List<String> binaryTreePaths(TreeNode root) {
        binaryTreePaths(root, "");
        return result;
    }

    List<String> result = new ArrayList<>();

    public void binaryTreePaths(TreeNode root, String tmp) {
        if (root == null) {
            return;
        }
        if (tmp.equals("")) {
            tmp = tmp + root.value;
        } else {
            tmp = tmp + "->" + root.value;
        }

        if (root.left == null && root.right == null) {
            result.add(tmp);
        }
        binaryTreePaths(root.left, tmp);
        binaryTreePaths(root.right, tmp);
    }
}
