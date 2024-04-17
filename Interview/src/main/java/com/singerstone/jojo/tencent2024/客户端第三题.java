package com.singerstone.jojo.tencent2024;

import com.singerstone.jojo.TreeNode;

import java.util.*;

public class 客户端第三题 {
    /**
     * 小红拿到了—棵树，她定义路径的权值为路径上所有节点的权值之和。小红想知道，有多少条权值为3的路径?
     * 我们定义，u->v和v->u为同—条路径。输入描述第—行输入—个正整数n，代表节点的数量。第二行输入n个正整数ai，代表每个节点的权值。接下来的n ―1行，每行输入两个正整数u,v，代表节点u和节点v有一条路径连接。输出描述—个整数，代表权值为3的路径数量。
     * 存在四种路径，儿子1、儿子2->自己->父亲，儿子->自己,儿子1->自己->儿子2,讨论一下即可
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] value = new int[n];
        for (int i = 0; i < n; i++) {
            // 每个节点的权重
            value[i] = scanner.nextInt();
        }
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
        dfs(0, -1, record, value);
        System.out.println(result);

    }

    static int result = 0;

    private static void dfs(int root, int parent, Map<Integer, List<Integer>> record, int[] value) {
        List<Integer> children = record.get(root);
        if (children == null) {
            return;
        }
        for (int i = 0; i < children.size(); i++) {
            int child = children.get(i);
            // 自己-儿子
            if (value[root] + value[child] == 3) {
                result++;
            }
            // 父亲-自己-儿子
            if (parent != -1) {
                if (value[parent] + value[root] + value[child] == 3) {
                    result++;
                }
            }
            // 儿子-自己-儿子
            for (int j = i + 1; j < children.size(); j++) {
                int child2 = children.get(j);
                if (value[child2] + value[child] + value[root] == 3) {
                    result++;
                }
            }
            dfs(child, root, record, value);

        }

    }


}
