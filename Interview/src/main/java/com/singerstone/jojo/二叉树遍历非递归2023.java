package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 二叉树遍历非递归2023 {

    /**
     * [0, 1, 3, 7, 8, 4, 9, 2, 5, 6]
     * [7, 3, 8, 1, 9, 4, 0, 5, 2, 6]
     * [7, 8, 3, 9, 4, 1, 5, 6, 2, 0]
     *
     */
    public static void main(String[] args) {
        Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        TreeNode root = makeTreeRecursion(array, 0, array.length);
        System.out.println(preOrder(root));
        System.out.println(inOrder(root));
        System.out.println(postOrder(root));

    }

    //中左右 入栈的时候添加结果
    public static List<Integer> preOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || root != null) {
            if (root != null) {
                result.add(root.value); // 关键代码
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                root = node.right;

            }

        }
        return result;
    }

    // 左中右 出栈的时候添加结果
    public static List<Integer> inOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                result.add(node.value); // 关键代码
                root = node.right;
            }
        }
        return result;
    }


    // 左右中 由 中右左 变形而来
    public static List<Integer> postOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || root != null) {
            if (root != null) {
                result.add(0, root.value); // 关键代码
                stack.push(root); // 关键代码
                root = root.right; // 先走右子树
            } else {
                TreeNode node = stack.pop();
                root = node.left;

            }

        }
        return result;
    }

}
