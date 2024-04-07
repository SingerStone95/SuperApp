package com.singerstone.jojo.tencent2024;

import java.util.Scanner;

public class 后端第五题 {

    /**
     * 红拿到了一个字符矩阵，她可以从任意一个地方出发，希望走 6 步后恰好形成 tencent 字符串。小红想知道，共有多少种不同的行走方案？
     * 注：每一步可以选择上、下、左、右中任意一个方向进行行走。不可行走到矩阵外部。
     * 输入描述
     * 第一行输入两个正整数n，m，代表矩阵的行数和列数。
     * 接下来的n行，每行输入一个长度为m的，仅由小写字母组成的字符串，代表小红拿到的矩阵。
     * 1≤n,m≤1000 输出描述
     * 一个整数，代表最终合法的方案数
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        char[][] input = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = scanner.next().charAt(0);
                input[i][j] = c;
            }
        }
//        char[][] input = new char[][]{
//                {'t', 'e', 'e', 'e'},
//                {'i', 't', 'n', 't'},
//                {'e', 'n', 'e', 'n'},
//                {'c', 'n', 'e', 't'}};


        char[] target = "tencent".toCharArray();
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                dfs(input, i, j, target, 0);
            }
        }

        System.out.println(result);

    }

    static int result = 0;

    private static void dfs(char[][] input, int i, int j, char[] target, int start) {
        if (start >= target.length) {
            return;
        }
        if (i < 0 || i >= input.length || j < 0 || j >= input[0].length) {
            return;
        }
        if (target[start] != input[i][j]) {
            return;
        }
        if (start == target.length - 1) {
            result++;
            return;
        }

        dfs(input, i + 1, j, target, start + 1);
        dfs(input, i, j + 1, target, start + 1);
        dfs(input, i - 1, j, target, start + 1);
        dfs(input, i, j - 1, target, start + 1);
    }


}
