package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.makeTreePreOrder;
import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;
import static com.singerstone.jojo.二叉树构建.visitTree;

import com.sun.org.apache.bcel.internal.generic.NEW;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 寻找重复子树 {

    public static void main(String[] args) {
        Integer[] values = {2,2,2,3,null,3,null};
        TreeNode root = makeTreeRecursion(values, 0, values.length);
        List<TreeNode> result = new 寻找重复子树().findDuplicateSubtrees(root);
        for (TreeNode node : result) {
            visitTree(node);

        }
    }

    //序列化二叉树思路一样
    //先找到每个子树的字符串表示方式，然后用 hashmap 来判断重复
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return result;

    }

    Map<String, Integer> map = new HashMap<>();
    ArrayList<TreeNode> result = new ArrayList<>();

    String dfs(TreeNode root) {
        if (root == null) {
            return " ";
        }
        String cur = root.value + "-";
        String left = dfs(root.left);
        String right = dfs(root.right);
        String key = cur + left + right;
        Integer count = map.get(key);
        if (count == null) {
            map.put(key, 1);
        } else {
            int new_count = count + 1;
            map.put(key, new_count);
            if (new_count == 2) {
                result.add(root);
            }
        }
        return key;

    }
}
