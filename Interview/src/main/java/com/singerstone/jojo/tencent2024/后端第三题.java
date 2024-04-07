package com.singerstone.jojo.tencent2024;

import java.util.*;

public class 后端第三题 {

    /**
     * 小红拿到了一个有 n 个节点的无向图，这个图初始并不是连通图。
     * 现在小红想知道，添加恰好一条边使得这个图连通，有多少种不同的加边方案
     * 输入描述
     * 第一行输入两个正整数n，m，用空格隔开。
     * 接下来的m行，每行输入两个正整数u，v，代表节点u和节点v之间有一条边连接。
     * 1≤n,m≤105 1≤u,v≤n
     * 保证给出的图是不连通的。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        // 构造图
        Map<Integer, HashSet<Integer>> record = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            HashSet<Integer> set_u = record.get(u);
            if (set_u == null) {
                set_u = new HashSet<>();
                set_u.add(v);
                record.put(u, set_u);
            } else {
                set_u.add(v);
            }
            HashSet<Integer> set_v = record.get(v);
            if (set_v == null) {
                set_v = new HashSet<>();
                set_v.add(u);
                record.put(v, set_v);
            } else {
                set_v.add(v);
            }
        }
        if (record.keySet().size() != n) {
            // 存在一个游离节点,那么结果就是 (n-1) * 1 = n-1
            System.out.println(n - 1);
            return;
        }
        // dfs
        List<HashSet<Integer>> result = new ArrayList<>();
        HashSet<Integer> tmp = new HashSet<>();
        for (Integer u : record.keySet()) {
            boolean skip = false;
            for (HashSet<Integer> set_dfs : result) {
                if (set_dfs.contains(u)) {
                    skip = true;
                    break;
                }
            }
            if (skip) {
                continue;
            }
            dfs(u, record, tmp);
            if (!tmp.isEmpty()) {
                result.add(new HashSet<>(tmp));
            }
            tmp.clear();
        }
        // 如果题没有错，那么这里 size 只能为 2
        assert (result.size() == 2);

        // 统计结果
        System.out.println(result.get(0).size() * result.get(1).size());

    }

    public static void dfs(int u, Map<Integer, HashSet<Integer>> record, HashSet<Integer> tmp) {
        if (tmp.contains(u)) {
            return;
        }
        tmp.add(u);
        HashSet<Integer> subs = record.get(u);
        for (int sub_u : subs) {
            dfs(sub_u, record, tmp);
        }

    }
}
