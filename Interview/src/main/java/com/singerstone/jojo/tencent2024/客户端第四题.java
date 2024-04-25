package com.singerstone.jojo.tencent2024;

import java.util.*;

public class 客户端第四题 {
    /**
     * 小红非常喜欢一首叫做剪切线的歌，所以小红现在非常喜欢剪切各种东西。现在小红有—棵树，她需要剪切树上的一条边，将这棵树剪切成两棵树，
     * 小红想知道两棵树直径之差的绝对值的最小值是多少。树的直径:树上任意两节点之间最长的简单路径即为树的直径。
     * 输入描述第—行输入—个整数n(1<n<1000）
     * 表示树的节点数。接下来n一1行，每行输入两个正整数u, v(1 ≤u,v≤n)表示树上的边。
     * 输出描述输出—个整数表示答案。
     * 点数n满足1<=n<=1e3
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<Integer, List<Integer>> record = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            List<Integer> set_p = record.get(p);
            if (set_p == null) {
                set_p = new ArrayList<>();
            }
            set_p.add(q);
            record.put(p, set_p);
        }

        treeDiameter(record, 0);
        System.out.println(result);
    }

    /**
     * 0
     * 1 2 3
     * (4 5) (6 7) (8 9)
     */


    static int result = Integer.MAX_VALUE;

    public static void treeDiameter(Map<Integer, List<Integer>> record, int root) {
        List<Integer> children = record.get(root);
        if (children == null || children.isEmpty()) {
            return;
        }

        List<Integer> copy = new ArrayList<>(children);
        for (int child : copy) {
            // 孩子作为根节点的直径
            int child_length = treeLength(record, child);
            // 删除当前分支，并计算根节点的直径
            children.remove(Integer.valueOf(child));
            int root_length = treeLength(record, 0);
            result = Math.min(result, Math.abs(child_length - root_length));

            //还原当前分支
            children.add(child);
            treeDiameter(record, child);
        }


    }

    static int maxLength = 0;

    public static int treeLength(Map<Integer, List<Integer>> record, int root) {
        List<Integer> children = record.get(root);
        if (children == null || children.isEmpty()) {
            return 0;
        }
        if (children.size() == 1) {
            int deep = 1 + treeLength(record, children.get(0));
            maxLength = Math.max(deep, maxLength);
            return deep;
        }
        // 深度第一大和深度第二大的和
        int max_deep = 0;
        List<Integer> deeps = new ArrayList<>();
        for (int child : children) {
            max_deep = Math.max(treeLength(record, child), max_deep);
            deeps.add(max_deep);
        }
        Collections.sort(deeps);
        maxLength = Math.max(deeps.get(deeps.size() - 1) + deeps.get(deeps.size() - 2), maxLength);
        return max_deep + 1;
    }

}
