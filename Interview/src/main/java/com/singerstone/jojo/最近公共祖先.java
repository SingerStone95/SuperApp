package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;

public class 最近公共祖先 {

    public static void main(String[] args) {

        Integer[] values = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        TreeNode root = makeTreeRecursion(values, 0, values.length);
        最近公共祖先 test = new 最近公共祖先();
        test.lowestCommonAncestor(root, new TreeNode(3), new TreeNode(8));
        System.out.println(test.result.value);
    }

    TreeNode result = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        isInSubTree(root, p, q);
        return result;
    }

    public boolean isInSubTree(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean left = isInSubTree(root.left, p, q);
        boolean right = isInSubTree(root.right, p, q);
        if (result != null) {
            return true;
        }
        if (left && right) {
            //root
            result = root;
        } else if ((root.equals(p) || root.equals(q)) && (left || right)) {
            //root
            result = root;
        }
        return root.equals(p) || root.equals(q) || left || right;

    }
}
