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
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(new 岛屿数量J2013().numIslands(array));
    }


    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (visited[i][j] || grid[i][j] == '0') {
                    continue;
                }
                // 这块区域还没有归属
                result++;
                dfs(i, j, grid, visited);
            }
        }
        return result;

    }

    int result = 0;

    /**
     * 给所有能访问到的 1 ，添加 visit 标记
     */
    void dfs(int i, int j, char[][] grid, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }
        if (grid[i][j] == '0' || visited[i][j]) { // 因为下面的 dfs 可能会再走到访问过的节点，所以这里判断 visited
            return;
        }
        visited[i][j] = true;
        dfs(i + 1, j, grid, visited);
        dfs(i - 1, j, grid, visited);
        dfs(i, j + 1, grid, visited);
        dfs(i, j - 1, grid, visited);
    }
}
