package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;

import java.util.LinkedList;

public class 二叉树完全性校验 {

    public static void main(String[] args) {
        Integer[] values = {0, 1,null,null, 2, 3};
        TreeNode root = makeTreeRecursion(values, 0, values.length);
        System.out.println(new 二叉树完全性校验().isCompleteTree(root));
    }

    /**
     * 思想：层序遍历，一旦遇到空节点 ，他的后续节点都应该为空 即：出现空就加标记位，如果还能继续遍历，就直接返回false
     */
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        boolean empty = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();
                if (node != null) {
                    if (empty) { //之前已经出现过 null ，现在又出现了非 null
                        return false;
                    }
                    queue.addLast(node.left);
                    queue.addLast(node.right);
                } else {
                    empty = true;
                }

            }
        }
        return true;
    }
}
