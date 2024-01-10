package com.singerstone.jojo;

import static com.singerstone.jojo.二叉搜索树.buildSearchTree;
import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;

import java.util.ArrayList;
import java.util.List;

public class 二叉树中和为某一值的路径 {

    public static void main(String[] args) {
        Integer[] array = {0, 1, 2, 3, 4, 5, 6};
        TreeNode root = makeTreeRecursion(array, 0, array.length);
        System.out.println(new 二叉树中和为某一值的路径().pathSum(root, 7));
    }

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        pathSum2(root, target, new ArrayList<>(), result);
        return result;
    }


    public void pathSum(TreeNode root, int target, List<Integer> temp, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null && target == root.value) {
            result.add(temp);
        }
        temp.add(root.value);
        pathSum(root.left, target - root.value, new ArrayList<>(temp), result);
        pathSum(root.right, target - root.value, new ArrayList<>(temp), result);
    }

    // temp array 这里做点一点点优化，不要每一层都去 new 一个新的
    public void pathSum2(TreeNode root, int target, List<Integer> temp, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        temp.add(root.value);
        if (root.left == null && root.right == null && target == root.value) {
            result.add(new ArrayList<>(temp));
        }
        pathSum2(root.left, target - root.value, temp, result);
        pathSum2(root.right, target - root.value, temp, result);
        temp.remove(temp.size() - 1);
    }

}
