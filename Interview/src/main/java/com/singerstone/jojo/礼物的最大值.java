package com.singerstone.jojo;

public class 礼物的最大值 {

    public static void main(String[] args) {
        int[][] array = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(new 礼物的最大值().maxValue(array));
    }

    public int maxValue(int[][] grid) {
        // 使用原始数组作为dp数组
        // dp[i][j] 表示到 grid[i][j] 能拿到的最大值
        int m = grid.length;
        int n = grid[0].length;
        // 这里没有赋值 dp[0][0] ,因为 dp[0][0] =  grid[0][0]
        // 赋值第一列
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        // 赋值第一行
        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j - 1];

        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.max(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m-1][n-1];

    }

}
