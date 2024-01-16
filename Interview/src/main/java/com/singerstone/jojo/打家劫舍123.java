package com.singerstone.jojo;

import static com.singerstone.jojo.二叉树构建.makeTreeRecursion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 打家劫舍123 {

    public static void main(String[] args) {
        Integer[] values = {0, 1, 2, 3, 4};
        // 0
        //1 2
        //34
        TreeNode root = makeTreeRecursion(values, 0, values.length);
        System.out.println(new 打家劫舍123().rob(root));

        int[] array = new int[]{1, 2, 3, 4, 5};
        System.out.println(new 打家劫舍123().rob1(array));
        System.out.println(new 打家劫舍123().rob2(array));

    }

    int rob1(int[] array) {
        int[] dp = new int[array.length];
        dp[0] = array[0];
        dp[1] = Math.max(array[0], array[1]);
        for (int i = 2; i < array.length; i++) {
            dp[i] = Math.max(dp[i - 1], array[i] + dp[i - 2]);
        }
        return dp[array.length - 1];
    }

    int rob2(int[] array) {
        int[] arr1 = Arrays.copyOfRange(array, 0, array.length - 1);
        int[] arr2 = Arrays.copyOfRange(array, 1, array.length);
        return Math.max(rob1(arr1), rob1(arr2));
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
        int outValue = Math.max(inMap.get(root.left), outMap.get(root.left)) + Math.max(
                inMap.get(root.right), outMap.get(root.right));
        inMap.put(root, inValue);
        outMap.put(root, outValue);
        max = Math.max(max, Math.max(inValue, outValue));
        // 返回当前
        return max;
    }

}
