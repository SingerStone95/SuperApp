package com.singerstone.jojo;

public class 岛屿最大面积 {


    public static void main(String[] args) {
        int[][] array = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
        System.out.println(new  岛屿最大面积().maxAreaOfIsland(array));
    }

    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (visited[i][j] || grid[i][j] == 0) {
                    continue;
                }
                result = Math.max(dfs(i, j, grid, visited), result);
            }
        }
        return result;
    }

    int result = 0;

    int dfs(int i, int j, int[][] grid, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || visited[i][j]
                || grid[i][j] == 0) {
            return 0;
        }
        visited[i][j] = true;
        return 1 + dfs(i + 1, j, grid, visited) +
                dfs(i - 1, j, grid, visited) +
                dfs(i, j + 1, grid, visited) +
                dfs(i, j - 1, grid, visited);
    }

}
