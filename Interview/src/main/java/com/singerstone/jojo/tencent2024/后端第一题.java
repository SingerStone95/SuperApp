package com.singerstone.jojo.tencent2024;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 后端第一题 {

    /**
     * 之前一直没有研究过图，这次遇到了，跟着年轻人门一起学习学习 1. 小红拿到了一个无向图，其中一些边被染成了红色。 小红定义一个点是“好点”，当且仅当这个点的所有邻边都是红边。
     * 现在请你求出这个无向图“好点”的数量。 注：如果一个节点没有任何邻边，那么它也是好点。
     * 输入描述
     * 第一行输入两个正整数n，m，代表节点的数量和边的数量。
     * 接下来的m行，每行输入两个正整数u，v和一个字符chr.代表节点u和节点v有一条边连接。如果chr为＇R＇，代表这条边被染红；W代表未被染色。
     * 1≤n,m≤105 1≤u,v≤n 输出描述
     * 一个整数，代表“好点”的数量。
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

