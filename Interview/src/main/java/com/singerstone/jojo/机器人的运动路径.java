package com.singerstone.jojo;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class 机器人的运动路径 {

    //https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/solutions/110056/mian-shi-ti-13-ji-qi-ren-de-yun-dong-fan-wei-dfs-b/
    // dfs，bfs都可以做（dfs有个前置数学推导，保证可达解都是联通且不存在不可达的解，否则无法使用dfs，，这个题就当已知条件了） visited + dfs  ,只需要向右和向下dfs即可
    public static void main(String[] args) {
        System.out.println(new 机器人的运动路径().movingCount(16, 8, 4));
    }

    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        movingCount(m, n, k, 0, 0, visited);
        return result;
    }

    int result = 0;

    void movingCount(int m, int n, int k, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        if ((getSum(i) + getSum(j)) <= k) { // 只有满足条件的时候才会继续向下搜索
            result++;
            movingCount(m, n, k, i + 1, j, visited);
//            movingCount(m, n, k, i - 1, j, visited); // 可以减枝
            movingCount(m, n, k, i, j + 1, visited);
//            movingCount(m, n, k, i, j - 1, visited); // 可以减枝
        }

    }

    int getSum(int n) {
        int result = 0;
        while (n > 0) {
            result += n % 10;
            n /= 10;
        }
        return result;
    }


}
