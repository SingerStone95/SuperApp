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
        Map<Integer, Set<Integer>> record = new LinkedHashMap<>();
        for (int i = 0; i < n - 1; i++) {
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            Set<Integer> set_p = record.get(p);
            if (set_p == null) {
                set_p = new HashSet<>();
            }
            set_p.add(q);
            record.put(p, set_p);
        }


    }


}
