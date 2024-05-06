package com.singerstone.jojo.tencent2024;

import java.util.Scanner;

public class 客户端第五题 {
    /**
     * 有一个大小为n × m的地图，地图上每个格子都是红色或紫色的，每个格子都有一个价值为aj的宝藏，小红只能挖红色格子上的宝藏，小紫只能挖紫色格子上的宝藏。
     * <p>
     * 小红和小紫初始在地图左上角，她们只能同时向下或向右走，她们挖宝有以下限制: 1.有的格子可以跳过不挖。2.对于同一个人，不能连续挖两个相邻的格子。
     * <p>
     * 她们想知道走到地图右下角—共能获得的宝藏的最大价值和是多少。
     * <p>
     * 输入描述第—行输入两个整数n, m() . 接下来n行，每行输入m个整数agq(1≤aj ≤109)表示地图上每个点的价值。接下来n行，每行输入一个长度为'm的只由'R'和'P'组成的字符串表示地图每个格子的颜色，'R'代表格子为红色，'P'代表格子为紫色
     * 1<=n,m<=1e3
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int value[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                value[i][j] = scanner.nextInt();
            }
        }
        char[][] color = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = scanner.next().charAt(0);
                color[i][j] = c;
            }
        }
        System.out.println(maxValue(value, color, 0, 0, ' '));
    }


    // from 0 代表上个位置没拿 ，1 代表拿了 R ，2 代表拿了 P
    public static int maxValue(int[][] value, char[][] color, int i, int j, Character from) {
        int n = value.length;
        int m = value[0].length;
        if (i >= n || j >= m) {
            return 0;
        }
        // 不拿
        int max_0 = Math.max(maxValue(value, color, i + 1, j, ' '), maxValue(value, color, i, j + 1, ' '));
        // 拿
        int max_1 = 0;
        if (from == ' ' || from != color[i][j]) { // 前一个不拿，或者当前和前一个不一样，都可以选择拿
            max_1 = value[i][j] + Math.max(maxValue(value, color, i + 1, j, color[i][j]), maxValue(value, color, i, j + 1, color[i][j]));
        }
        return Math.max(max_0, max_1);
    }
}
