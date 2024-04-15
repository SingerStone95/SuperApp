package com.singerstone.jojo.tencent2024;

import com.singerstone.jojo.TreeNode;

import java.util.*;

public class 客户端第三题 {
    /**
     * 给定一颗树，每个节点右价值1或2，问有多少条简单路径价值为3，路径的价值是包含节点的价值，u->v,v->u只算一条
     * 点数n满足1<=n<=1e5
     * 存在四种路径，儿子1、儿子2->自己->父亲，儿子->自己,儿子1->自己->儿子2,讨论一下即可
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<Integer, List<Integer>> record = new HashMap<>();
        int root = -1;
        for (int i = 0; i < n - 1; i++) {
            int p = scanner.nextInt();
            if (i == 0) {
                root = p;
            }
            int q = scanner.nextInt();
            List<Integer> set_p = record.get(p);
            if (set_p == null) {
                set_p = new ArrayList<>();
            }
            set_p.add(q);
            record.put(p, set_p);
        }
        dfs(root, -1, record);
        System.out.println(result);

    }

    static int result = 0;

    private static void dfs(int root, int parent, Map<Integer, List<Integer>> record) {
        List<Integer> children = record.get(root);

        for (int child : children) {

        }
        for (int i = 0; i < children.size(); i++) {
            int child = children.get(i);
            // 自己-儿子
            if (root + child == 3) {
                result++;
            }
            // 父亲-自己-儿子
            if (parent != -1) {
                if (parent + root + child == 3) {
                    result++;
                }
            }
            // 儿子-自己-儿子
            for (int j = i + 1; j < children.size(); j++) {
                int child2 = children.get(j);
                if (child2 + child + root == 3) {
                    result++;
                }
            }
            dfs(child, root, record);

        }

    }


}
