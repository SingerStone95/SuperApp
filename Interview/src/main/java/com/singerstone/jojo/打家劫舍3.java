package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;

import java.util.HashMap;
import java.util.Map;

public class 打家劫舍3 {

    public static void main(String[] args) {
        Integer[] values = {0, 1, 2, 3, 4};
        TreeNode root = makeTreeRecursion(values, 0, values.length);
        System.out.println(new 打家劫舍3().rob(root));
    }


    Map<TreeNode, Integer> inMap = new HashMap<>();
    Map<TreeNode, Integer> outMap = new HashMap<>();
    int max = 0;

    //4,1,null,2,null,3
    public int rob(TreeNode root) {
        if (root == null) {
            inMap.put(null, 0);
            outMap.put(null, 0);
            return 0;
        }
        rob(root.left);
        rob(root.right);
        int inValue = root.value + outMap.get(root.left) + outMap.get(root.right);
        // 这里要注意的是：不rob当前的节点，并不代表一定要rob子节点，所以这里要取最大值
        // int outValue = Math.max(inMap.get(root.left) + inMap.get(root.right), outMap.get(root.left) + outMap.get(root.right));
        int outValue = Math.max(inMap.get(root.left), outMap.get(root.left)) + Math.max(inMap.get(root.right), outMap.get(root.right));
        inMap.put(root, inValue);
        outMap.put(root, outValue);
        max = Math.max(max, Math.max(inValue, outValue));
        return max;
    }

}
