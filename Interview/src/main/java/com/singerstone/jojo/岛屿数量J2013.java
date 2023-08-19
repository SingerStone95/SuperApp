package com.singerstone.jojo;

public class 岛屿数量J2013 {

    public static void main(String[] args) {
        char[][] array = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        char[][] array2 = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println(new 岛屿数量J2013().numIslands(array2));
    }


    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (visited[i][j] || grid[i][j] == '0') {
                    continue;
                }
                result++;
                dfs(i, j, grid, visited);
            }
        }
        return result;

    }

    int result = 0;

    void dfs(int i, int j, char[][] grid, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        if (grid[i][j] == '1') {
            dfs(i + 1, j, grid, visited);
            dfs(i - 1, j, grid, visited);
            dfs(i, j + 1, grid, visited);
            dfs(i, j - 1, grid, visited);
        }
    }
}
