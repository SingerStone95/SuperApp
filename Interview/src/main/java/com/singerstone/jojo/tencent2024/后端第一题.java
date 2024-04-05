package com.singerstone.jojo.tencent2024;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 后端第一题 {

    /**
     * 之前一直没有研究过图，这次遇到了，跟着年轻人门一起学习学习 1. 小红拿到了一个无向图，其中一些边被染成了红色。 小红定义一个点是“好点”，当且仅当这个点的所有邻边都是红边。
     * 现在请你求出这个无向图“好点”的数量。 注：如果一个节点没有任何邻边，那么它也是好点。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Map<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            char chr = scanner.next().charAt(0);
            if (chr == 'W') {
                map.put(u, false);
                map.put(v, false);
            } else if (chr == 'R') {
                map.putIfAbsent(u, true);
                map.putIfAbsent(v, true);
            }

        }
        int result = 0;
        for (Boolean b : map.values()) {
            if (b) {
                result++;
            }
        }

        System.out.println(result);
        // 关闭scanner
        scanner.close();
    }
}

