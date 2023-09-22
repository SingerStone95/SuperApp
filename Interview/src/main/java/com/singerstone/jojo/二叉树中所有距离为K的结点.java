package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 二叉树中所有距离为K的结点 {


    public static void main(String[] args) {
        Integer[] values = {0, 1, 2, 3, 4};
        TreeNode root = makeTreeRecursion(values, 0, values.length);
        System.out.println(new 二叉树中所有距离为K的结点().distanceK(root, root, 1));
    }

    Map<TreeNode, TreeNode> parentMap = new HashMap<>();
    List<Integer> result = new ArrayList<>();

    //分两步
    //1. 记录没一个节点的父节点
    //2. 从 target 向两边搜索
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        recordParent(root, null);
        dfs(target, null, k);
        return result;
    }

/*    private void dfs(TreeNode target, TreeNode from, int distance, int k) {
        if (target == null) {
            return;
        }
        if (distance == k) {
            result.add(target.value);
        }
        if (from != target.left) {  // 防止反向搜索
            dfs(target.left, target, distance + 1, k);
        }
        if (from != target.right) {
            dfs(target.right, target, distance + 1, k);
        }
        TreeNode parent = parentMap.get(target);
        if (from != parent) {
            dfs(parent, target, distance + 1, k);
        }

    }*/


    // 双向搜索 改方法不能这么用,要想办法防止反向搜索，可以考虑传递一下上一个的来源
    private void dfs(TreeNode target, TreeNode from, int k) {
        if (target == null) {
            return;
        }
        if (k < 0) {
            return;
        } else if (k == 0) {
            result.add(target.value);
            return;
        }
        if (target.left != from) {
            dfs(target.left, target, k - 1);
        }
        if (target.right != from) {
            dfs(target.right, target, k - 1);
        }
        TreeNode parent = parentMap.get(target);
        if (parent != from) {
            dfs(parent, target, k - 1);
        }

    }


    private void recordParent(TreeNode root, TreeNode parent) {
        if (root == null) {
            return;
        }
        parentMap.put(root, parent);
        recordParent(root.left, root);
        recordParent(root.right, root);
    }
}
