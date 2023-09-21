package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class 从上到下打印二叉树 {

    public static void main(String[] args) {
        Integer[] values = {0, 1, 2, 3, 4};
        TreeNode root = makeTreeRecursion(values, 0, values.length);
        System.out.println(new 从上到下打印二叉树().levelOrder(root));
        System.out.println(new 从上到下打印二叉树().levelOrder2(root));
        System.out.println(new 从上到下打印二叉树().levelOrder3(root));

    }

    LinkedHashMap<Integer, LinkedList<Integer>> map = new LinkedHashMap<>();

    public void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        List<Integer> level_list = map.get(level);
        if (level_list == null) {
            LinkedList<Integer> list = new LinkedList<>();
            list.add(root.value);
            map.put(level, list);
        } else {
            level_list.add(root.value);
        }
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);

    }

    //层序打印
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, 0);
        for (int i = 0; i < map.size(); i++) {
            result.add(map.get(i));
        }
        return result;
    }


    public void dfs2(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        LinkedList<Integer> level_list = map.get(level);
        if (level_list == null) {
            LinkedList<Integer> list = new LinkedList<>();
            if (level % 2 == 1) {
                list.addFirst(root.value);
            } else {
                list.add(root.value);
            }
            map.put(level, list);
        } else {
            if (level % 2 == 1) {
                level_list.addFirst(root.value);
            } else {
                level_list.add(root.value);
            }
        }
        dfs2(root.left, level + 1);
        dfs2(root.right, level + 1);
    }

    /**
     * Z 字形打印
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs2(root, 0);
        for (int i = 0; i < map.size(); i++) {
            result.add(map.get(i));
        }
        return result;
    }

    // 使用队列 Z 字形打印
    public List<List<Integer>> levelOrder3(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> array = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (flag) {
                    array.add(node.value);
                } else {
                    array.add(0, node.value);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(array);
            flag = !flag;
        }

        return result;
    }
}
